package omg.wecan.donationCertificate.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import omg.wecan.donationCertificate.entity.DonationCertificate;
import omg.wecan.util.FileStore;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DonationCertificateCreateRequest {
    @NotNull
    private String title;
    
    private String explanation;

    private MultipartFile coverImage;

    public DonationCertificate toEntity(String imgUrl) {
        return DonationCertificate.builder()
                .title(this.title)
                .imgEndpoint(imgUrl)
                .explanation(this.explanation)
                .build();
    }
}
