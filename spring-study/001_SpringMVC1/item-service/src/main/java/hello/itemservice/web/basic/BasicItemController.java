package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam Integer price,
            @RequestParam Integer quantity,
            Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute(item);

        return "basic/item";
    }

//    @PostMapping("/add") // 개인적으로는 이 방법이 가장 명확하고 깔끔한 거 같음.
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);
        // @ModelAttribute("item")가 "item"이라는 이름으로 Model 객체에 자동으로 추가해준다.
        //model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    // @ModelAttribute의 name 속성을 생략하면 클래스명의 첫 글자만 소문자로 바꿔서 Model 객체에 추가해준다.
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);

        return "basic/item";
    }

    @PostMapping("/add")
    // @ModelAttribute 자체를 생략할 수도 있다.
    public String addItemV4(Item item) {

        itemRepository.save(item);

        return "basic/item";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
