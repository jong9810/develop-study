package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // updateItem() 메소드는 변경 감지를 사용한 준영속성 엔티티 변경 방법이다.
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 엔티티의 값을 변경할 때 이렇게 하나하나 setter로 값을 넣어주는 것보다는,
        // 엔티티 안에서 의미있는 메소드로 묶어서 한 번에 해주는 게 더 좋다(유지보수성).
        // findItem.change(price, name, stockQuantity); // Book or Item 엔티티 내에 정의

        // 업데이트 하는 파라미터가 많으면 UpdateItemDto 같은 걸 만들어서 풀어내도 된다.

        Item findItem = itemRepository.findOne(itemId); // 영속성 엔티티(변경 감지 가능)
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
