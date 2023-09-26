package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "/items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {

        // 실제 설계할 때는 setter를 다 없애고 Book 클래스에 생성자 메서드로 처리하는 것이 더 좋은 설계이다.
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        // 실무에서는 캐스팅해서 사용하는 것은 좋지 않다.
        // 예제를 간소화하기 위해 캐스팅한 것.
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {

        // id 값은 다른 사람이 조작하지 못하도록 프론트와 백에서 권한이 있는 사용자인지 체크하는 로직이 필요하다.
        // 좀 복잡한 방법이지만 update할 객체 자체를 session에 담아두고 풀어내는 방법도 있다(요즘 session 객체는 잘 안 쓰임).

        // DB에 한 번 들어갔다가 나온(식별자가 있는) 엔티티를 준영속 엔티티라고 한다.
        // JPA가 식별할 수 있는 ID를 가지고 있지만 영속성 컨텍스트가 더는 관리하지 않는 엔티티.
        // JPA가 관리하지 않기 때문에 변경 감지가 되지 않는다.

        // 컨트롤러에서 어설프게 엔티티를 생성하지 말자(유지보수성)!
        // 트랜잭션이 있는 서비스 계층에 식별자와 변경할 데이터를 명확하게 전달하자(파라미터 or Dto)!
        // 트랜잭션이 있는 서비스 계층에서 영속 상태의 엔티티를 조회하고, 엔티티의 데이터를 직접 변경하자(변경 감지로 업데이트)!
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }

    // 변경 감지(Dirty Checking)와 병합(Merge) (매우매우 중요!!)
    // 변경 감지는 식별자로 영속성 컨텍스트나 DB에서 엔티티를 가져와서 setter로 값을 변경하면 된다(merge 같이 따로 메서드가 존재하지 않음).
    // 엔티티를 변경할 때는 항상 변경 감지를 사용하자!

    // 병합 동작 방식
    // 1) 준영속 엔티티의 식별자 값으로 영속 엔티티를 조회한다.
    // 2) 영속 엔티티의 값을 준영속 엔티티의 값으로 모두 교체(병합)한다.
    // 3) 트랜잭션 커밋 시점에 변경 감지 기능이 동작해서 데이터베이스에 update sql이 실행된다.
    // 주의: 변경 감지를 사용하면 원하는 속성만 선택해서 변경할 수 있지만, 병합을 사용하면 모든 속성이 변경된다.
    // 병합시 값이 없으면 null로 업데이트할 위험도 있다(병합은 모든 필드를 교체하기 때문).
    // 병합은 사용하면 안된다고 생각하는 게 편하다.
}
