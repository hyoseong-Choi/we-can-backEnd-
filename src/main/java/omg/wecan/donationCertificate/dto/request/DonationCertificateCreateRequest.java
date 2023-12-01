package omg.wecan.donationCertificate.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import omg.wecan.donationCertificate.entity.DonationCertificate;

@Getter
@Setter
public class DonationCertificateCreateRequest {

    @NotNull
    private String title;
    
    private String explanation;

    private String imgEndpoint;

    public DonationCertificate toEntity() {
        return DonationCertificate.builder()
                .tile(this.title)
                .explanation(this.explanation)
                .imgEndpoint(this.imgEndpoint)
                .build();
    }
}
