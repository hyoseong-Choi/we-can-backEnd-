package omg.wecan.donationCertificate.dto.response;

import lombok.Getter;
import omg.wecan.donationCertificate.entity.DonationCertificate;

import java.util.List;

@Getter
public class DonationCertificateResponses {
    private List<DonationCertificateResponse> responses;

    public DonationCertificateResponses(List<DonationCertificateResponse> responses) {
        this.responses = responses;
    }
}
