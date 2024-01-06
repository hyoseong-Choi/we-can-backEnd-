package omg.wecan.donationCertificate.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DonationCertificateUpdateRequest {

    private String title;
    
    private String explanation;

    private MultipartFile coverImage;
}
