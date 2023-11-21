package omg.wecan.user.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import omg.wecan.global.entity.BaseEntity;

@Entity
@NoArgsConstructor
public class CertificationMail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificationMail_id")
    private Long id;

    String certificationNum;
    public CertificationMail(String certificationNum) {
        this.certificationNum = certificationNum;
    }
}
