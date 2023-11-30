package omg.wecan.donationCertificate.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import omg.wecan.donationCertificate.dto.request.DonationCertificateUpdateRequest;
import omg.wecan.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class DonationCertificate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tile;

    @Column
    private String explanation;

    @Column(name = "img_endpoint", nullable = false)
    private String imgEndpoint;

    public DonationCertificate() {

    }

    public void change(DonationCertificateUpdateRequest donationCertificateUpdateRequest){
        String newTitle = donationCertificateUpdateRequest.getTitle();
        String newExplanation = donationCertificateUpdateRequest.getExplanation();
        String newImgEndpoint = donationCertificateUpdateRequest.getImgEndpoint();

        if(newTitle != null)
            this.tile = newTitle;

        if(newExplanation != null)
            this.explanation = newExplanation;

        if(newImgEndpoint != null)
            this.imgEndpoint = newImgEndpoint;
    }
}
