package omg.wecan.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationMailOutput {
 
    private String email;
    private String title;
    private String message;

}
