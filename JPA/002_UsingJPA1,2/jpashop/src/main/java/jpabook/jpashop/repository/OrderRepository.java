package jpabook.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.QMember;
import jpabook.jpashop.domain.QOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public OrderRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 전체 or 검색 조회
    // 동적 쿼리1 : 실무에서는 안 씀.
    public List<Order> findAllByString(OrderSearch orderSearch) {

        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }


        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);// 최대 1000건

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
    }

    /**
     * JPA Criteria : JPA 표준 스펙. 이것도 실무에서는 사용 안함.
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }

    // 회원 이름 검색 또 다른 방법 : Querydsl로 동적 쿼리 처리
    // Querydsl은 SQL(JPQL)과 모양이 유사하면서 자바코드로 동적 쿼리를 편리하게 생성할 수 있다.
    // 직관적인 문법, 컴파일 시점에 빠른 문법 오류 발견, 코드 자동 완성, 코드 재사용(이것은 자바다), JPQL new 명령어와는 비교가 안될 정도로 깔끔한 DTO 변환 조회를 지원한다.
    // Querydsl은 JPQL을 코드로 만드는 빌더 역할을 할 뿐이다. 따라서 JPQL을 잘 이해하면 금방 배울 수 있다.
    // Querydsl은 JPA로 애플리케이션을 개발할 때 선택이 아닌 필수라 생각한다.
    // QOrder 같이 생성된 파일들은 github에 업로드하지 않도록 하자.
    public List<Order> findAll(OrderSearch orderSearch) {
        return query
                .select(QOrder.order)
                .from(QOrder.order)
                .join(QOrder.order.member, QMember.member)
                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    private static BooleanExpression nameLike(String memberName) {
        if (!StringUtils.hasText(memberName)) {
            return null;
        }
        return QMember.member.name.like(memberName);
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null) {
            return null;
        }
        return QOrder.order.status.eq(statusCond);
    }

    // fetch join 사용하기
    // Order, Member, Delivery 를 한 번의 쿼리로 다 가져온다.
    // 이미 조회된 상태이기 때문에 지연 로딩이 되지 않고, Proxy 값도 아닌 실제 값을 채워서 가져온다.
    // 실무에서 성능 최적화할 때 굉장히 자주 사용되기 때문에 100% 이해하는 것을 추천한다.
    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class)
                .getResultList();
    }

    // Hibernate6 이상에서는 join시 중복 데이터가 출력되지 않도록 변경되었다.
    // distinct : db 쿼리에서는 한 행의 데이터가 완전히 같지 않기 때문에 그대로 4행이 출력되지만, JPA에서 같은 값을 중복 제거해준다(Hibernate 6 미만에서 사용).
    // db에도 distinct 키워드를 보내주고, 엔티티가 중복인 경우에 중복을 제거하고 컬렉션에 담아준다.

    // 컬렉션 페치 조인(1대 다 페치 조인)
    // db 쿼리를 엄청나게 줄여주긴 하지만 '페이징 처리가 불가능'하다!
    // 엄밀히 말하면 페이징 처리가 가능하긴 하지만 하지 않는 것이 좋다.
    // 왜냐하면 컬렉션 페치 조인을 하면 중복 데이터가 생기기 때문에 페이징 처리를 해서 데이터를 가져오게 되면 우리가 원하는 결과가 나오지 않을 수 있다.
    // 따라서 일단 모든 결과를 가져와서 중복을 제거한 후에 메모리에서 페이징 처리를 하게 된다(매우 위험).

    // 참고 : 컬렉션 페치 조인은 1개만 사용할 수 있다. 컬렉션 둘 이상에 페치 조인을 사용하면 안된다(데이터가 부정합하게 조회될 수 있기 때문).
    // 1대 다대 다 : 1 * n * m -> 데이터가 엄청나게 뻥튀기 된다.
    public List<Order> findAllWithItem() {
        return em.createQuery(
          "select distinct o from Order o" +
                  " join fetch o.member m" +
                  " join fetch o.delivery d" +
                  " join fetch o.orderItems oi" +
                  " join fetch oi.item i", Order.class)
                .setFirstResult(1)
                .setMaxResults(100)
                .getResultList();
    }

    // 컬렉션 페치 페이징 처리
    // 1) xToOne 관계를 모두 페치 조인한다(toOne 관계는 row 수를 증가시키지 않으므로 페이징 쿼리에 영향을 주지 않음).
    // 2) 컬렉션(OneToMany)은 지연 로딩으로 조회한다.
    // 3) 지연 로딩 성능 최적화를 위해 hibernate.default_batch_fetch_size, @BatchSize를 적용한다.
    //    hibernate.default_batch_fetch_size : 글로벌 설정
    //    @BatchSize : 개별 최적화
    //    이 옵션을 사용하면 컬렉션이나, 프록시 객체를 한꺼번에 설정한 size만큼 where in 쿼리(Hibernate6 미만)나 array_contains(Hibernate6 이상)로 조회한다.
    //    1 + N + M 개의 쿼리 -> 1 + 1 + 1 개 쿼리로 바뀜
    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
        return em.createQuery(
                        // 아래 코드로 해도 되지만 네트워크를 더 많이 탄다(쿼리 1개 -> 쿼리 3개, toOne 관계는 fetch join을 하자).
                        //"select o from Order o", Order.class)
                        "select o from Order o" +
                                " join fetch o.member m" +
                                " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    // V3.1 장점
    // 1) 쿼리 호출 수가 1 + N에서 1 + 1로 최적화된다.
    // 2) 조인보다 DB 데이터 전송량이 최적화된다.
    // 3) 페치 조인 방식과 비교해서 쿼리 호출 수가 약간 증가하지만, DB 데이터 전송량이 줄어든다.
    // 4) 컬렉션 페치 조인은 페이징이 불가능하지만 이 방법은 페이징이 가능하다(가장 큰 장점!).

    // 결론
    // ToOne 관계는 페치 조인으로 쿼리 수를 줄이고, 나머지(컬렉션, OneToMany)는 hibernate.default_batch_fetch_batch_size 로 최적화하자!!

    // 참고 : default_batch_fetch_size의 미니멈은 따로 없고, 맥시멈은 1000개로 하는 게 좋다(in에 1000개 데이터가 들어가면 오류를 일으키는 db가 있기 때문).
    // 애플리케이션에 따라 100 ~ 1000 사이에 적당한 수를 선택하는 것을 추천한다.
    // 높은 수로 설정할 경우 시간은 줄어들지만 db, 애플리케이션(WAS)에 순간적으로 부하가 증가할 수 있기 때문에 적당한 수로 설정하는 게 좋다.
    // 메모리 사용량은 default_batch_fetch_size가 크든 작든 상관없이 동일하다.

}
