package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {
    @Transactional(readOnly = true)
    Integer countByRecruit(Recruit recruit);
    
    @Transactional(readOnly = true)
    Boolean existsByUserAndRecruit(User user, Recruit recruit);
    
    @Transactional
    void deleteByUserAndRecruit(User user, Recruit recruit);
    
    @Transactional(readOnly = true)
    List<Participate> findByRecruit(Recruit recruit);
    
    @Transactional(readOnly = true)
    @Query("select p from Participate p join fetch p.user where p.recruit=:recruit")
    List<Participate> findUserByRecruit(@Param("recruit")Recruit recruit);
    
    @Transactional(readOnly = true)
    @Query(value = "select p from Participate p join fetch p.user join fetch p.recruit where p.user=:user and p.recruit.finished=false",
            countQuery ="select count(p.id) from Participate p where p.user=:user and p.recruit.finished=false")
    Page<Participate> findByUser(@Param("user")User user, Pageable pageable);
}
