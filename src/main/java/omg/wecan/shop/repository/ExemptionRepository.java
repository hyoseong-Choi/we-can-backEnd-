package omg.wecan.shop.repository;

import omg.wecan.shop.entity.Exemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemptionRepository extends JpaRepository<Exemption, Long> {

}
