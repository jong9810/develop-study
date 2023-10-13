package jpabook.jpashop.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    // Repository 에서 Controller에 의존관계가 생기면 안된다(양방향 의존관계가 되기 때문).
    // new 연산자를 사용하여 JPQL의 결과를 Dto로 즉시 변환한다.
    // 화면에 딱 맞춘 API 스펙(따로 패키지를 만들어서 빼는 게 좋음, 유지보수성).
    // Repository에 있으면 사람들이 모르고 사용할 수도 있기 때문에 유지보수성이 낮아진다.
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.simplequery.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderSimpleQueryDto.class
        ).getResultList();
    }

}
