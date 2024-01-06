package omg.wecan.donationCertificate.dto.response;

import lombok.Getter;
import omg.wecan.donationCertificate.entity.DonationCertificate;

@Getter
public class DonationCertificateResponse {
    private Long id;

    private String title;

    private String explanation;

    private String imgEndpoint;

    public DonationCertificateResponse(DonationCertificate donationCertificate){
        this.id = donationCertificate.getId();
        this.title = donationCertificate.getTitle();
        this.explanation = donationCertificate.getExplanation();
        this.imgEndpoint = donationCertificate.getImgEndpoint();
    }
}
