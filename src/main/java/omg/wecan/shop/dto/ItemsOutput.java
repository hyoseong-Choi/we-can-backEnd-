package omg.wecan.shop.dto;

import lombok.Data;
import omg.wecan.shop.entity.Item;

@Data
public class ItemsOutput {
    private Long id;
    private String name;
    private int price;
    private String img;
    
    public ItemsOutput(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.img = item.getImgEndpoint();
    }
}
