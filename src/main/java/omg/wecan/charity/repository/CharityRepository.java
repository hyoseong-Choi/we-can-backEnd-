package omg.wecan.charity.repository;

import omg.wecan.charity.entity.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepository extends JpaRepository<Charity, Long> {
}
