package omg.wecan.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    
}
