package omg.wecan.shop.repository;

import omg.wecan.shop.entity.Exemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ExemptionRepository extends JpaRepository<Exemption, Long> {
    @Transactional
    void deleteByCertificationString (String certificationString);

    Optional<Exemption> findByCertificationString(String certificationString);
}
