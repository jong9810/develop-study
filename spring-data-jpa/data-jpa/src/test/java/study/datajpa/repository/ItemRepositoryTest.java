package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;

    @Test
    void save() {
        // 식별자가 null이나 0이 아닌 경우 save 메서드에서 isNew()를 하면 false가 반환된다.
        // 그렇기 때문에 em.merge()가 호출된다(비효율적).
        // 데이터 저장은 em.persist(), 데이터 수정은 변경 감지를 사용해야 한다(em.merge()는 사용하면 안됨).
    	Item item = new Item("A");
        itemRepository.save(item);
    }

}