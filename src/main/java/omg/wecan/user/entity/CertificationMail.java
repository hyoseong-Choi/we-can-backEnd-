package omg.wecan.user.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
@RedisHash(timeToLive = 300)
@NoArgsConstructor
public class CertificationMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Indexed
    private String certificationNum;
    public CertificationMail(String certificationNum) {
        this.certificationNum = certificationNum;
    }
}
