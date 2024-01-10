package omg.wecan.shop.dto;

import lombok.Data;

@Data
public class MyItemsOutput {
    private Long id;
    private String name;
    private String img;
    
    public MyItemsOutput(Long id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
        
    }
}
