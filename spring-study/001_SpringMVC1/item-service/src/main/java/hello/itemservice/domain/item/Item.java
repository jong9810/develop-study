package hello.itemservice.domain.item;

import lombok.Data;

@Data // 여러 어노테이션이 같이 있어서 위험할 수 있다.
//@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
