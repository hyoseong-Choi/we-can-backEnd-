package omg.wecan.shop.dto;

import lombok.Data;
import omg.wecan.shop.entity.Item;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.util.Base64;

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
        try {
            this.img = Base64.getEncoder().encodeToString(new UrlResource("file:" + item.getImgEndpoint()).getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
