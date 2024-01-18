package omg.wecan.shop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemInput {
    private String name;
    private String producer;
    private int price;
    private String itemType;
    private String explanation;
    private MultipartFile coverImage;
}
