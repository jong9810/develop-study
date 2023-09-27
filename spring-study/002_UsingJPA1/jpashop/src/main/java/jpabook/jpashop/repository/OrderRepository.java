package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jpabook.jpashop.api.OrderApiController;
import jpabook.jpashop.api.OrderSimpleApiController;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

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
}
