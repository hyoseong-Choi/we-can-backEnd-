package omg.wecan.charity.repository;

import omg.wecan.charity.entity.CharityCategory;
import omg.wecan.charity.entity.Charity;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharityRepository extends JpaRepository<Charity, Long> {
    default Charity getById(Long id){
        return this.findById(id).orElseThrow(() -> new CustomException(ErrorCode.CHARITY_NOT_FOUND, "Charity Id: " + id));
    }

    List<Charity> findAllByCategory(CharityCategory category);

    List<Charity> findAllByCategoryAndNameIsLike(CharityCategory category, String name);

    List<Charity> findAllByExplanationLike(String explanation);
    
    Optional<Charity> findByName(String name);
}