package omg.wecan.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.shop.dto.ItemInput;

@Entity
@NoArgsConstructor
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String producer;
    private int price;
    @Enumerated(value = EnumType.STRING)
    private ItemType itemType;
    private String explanation;
    private String imgEndpoint;
    
    public Item(ItemInput itemInput, String imgEndpoint) {
        this.name = itemInput.getName();
        this.producer = itemInput.getProducer();
        this.price = itemInput.getPrice();
        this.itemType = ItemType.from(itemInput.getItemType());
        this.explanation = itemInput.getExplanation();
        this.imgEndpoint = imgEndpoint;
    }
}
