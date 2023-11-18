package omg.wecan.user.repository;

import omg.wecan.user.entity.CertificationMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CertificationMailRepository extends JpaRepository<CertificationMail, Long> {
    Optional<CertificationMail> findByCertificationNum(String certificationNum);
}
