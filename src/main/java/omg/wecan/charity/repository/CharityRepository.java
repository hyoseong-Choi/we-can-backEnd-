package omg.wecan.charity.repository;

import omg.wecan.charity.entity.CharityCategory;
import omg.wecan.charity.exception.NoSuchCharityException;
import omg.wecan.charity.entity.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharityRepository extends JpaRepository<Charity, Long> {
    default Charity getById(Long id){
        return this.findById(id).orElseThrow(NoSuchCharityException::new);
    }

    List<Charity> findAllByCategory(CharityCategory category);

    List<Charity> findAllByCategoryAndNameIsLike(CharityCategory category, String name);

    List<Charity> findAllByExplanationLike(String explanation);
}