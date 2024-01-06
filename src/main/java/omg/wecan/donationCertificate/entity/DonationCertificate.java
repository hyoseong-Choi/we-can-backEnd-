package omg.wecan.donationCertificate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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
    private String title;

    @Column
    private String explanation;

    @Column(name = "img_endpoint", nullable = false)
    private String imgEndpoint;

    public DonationCertificate() {

    }

    public void change(String newTitle, String newExplanation, String newImgUrl){
        if(newTitle != null)
            this.title = newTitle;

        if(newExplanation != null)
            this.explanation = newExplanation;

        if(newImgUrl != null)
            this.imgEndpoint = newImgUrl;
    }
}
