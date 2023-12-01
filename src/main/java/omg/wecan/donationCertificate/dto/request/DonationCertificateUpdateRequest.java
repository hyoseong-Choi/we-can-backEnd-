package omg.wecan.donationCertificate.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationCertificateUpdateRequest {

    private String title;
    
    private String explanation;

    private String imgEndpoint;
}
