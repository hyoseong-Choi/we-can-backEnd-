package omg.wecan.donationCertificate.dto.response;

import omg.wecan.donationCertificate.entity.DonationCertificate;

public class DonationCertificateResponse {
    private Long id;

    private String title;

    private String explanation;

    private String imgEndpoint;

    public DonationCertificateResponse(DonationCertificate donationCertificate){
        this.id = donationCertificate.getId();
        this.title = donationCertificate.getTile();
        this.explanation = donationCertificate.getExplanation();
        this.imgEndpoint = donationCertificate.getImgEndpoint();
    }
}
