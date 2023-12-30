package omg.wecan.shop.dto;

import lombok.Data;
import omg.wecan.shop.entity.Item;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;

@Data
public class MyItemsOutput {
    private Long id;
    private String name;
    private String img;
    
    public MyItemsOutput(Long id, String name, String img)  {
        this.id = id;
        this.name = name;
        try {
            this.img = Base64.getEncoder().encodeToString(new UrlResource("file:" + img).getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
