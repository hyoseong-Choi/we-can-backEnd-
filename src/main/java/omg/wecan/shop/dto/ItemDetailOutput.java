package omg.wecan.shop.dto;

import lombok.Data;
import omg.wecan.shop.entity.Item;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.util.Base64;


@Data
public class ItemDetailOutput {
    private Long id;
    private String name;
    private String producer;
    private int price;
    private String explanation;
    private String img;
    
    public ItemDetailOutput(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.producer = item.getProducer();
        this.price = item.getPrice();
        this.explanation = item.getExplanation();
        try {
            this.img = Base64.getEncoder().encodeToString(new UrlResource("file:" + item.getImgEndpoint()).getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
