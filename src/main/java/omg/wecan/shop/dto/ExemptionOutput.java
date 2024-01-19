package omg.wecan.shop.dto;

import lombok.Data;
import omg.wecan.shop.entity.Exemption;

@Data
public class ExemptionOutput {
    private Long id;
    private String certificationString;
    
    public ExemptionOutput(Exemption exemption) {
        this.id = exemption.getId();
        this.certificationString = exemption.getCertificationString();
    }
}
