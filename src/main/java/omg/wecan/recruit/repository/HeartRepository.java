package omg.wecan.recruit.repository;


import omg.wecan.recruit.entity.Heart;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    @Transactional(readOnly = true)
    Boolean existsByUserAndRecruit(User user, Recruit recruit);
    
    @Transactional(readOnly = true)
    @Query(value = "select h from Heart h join fetch h.user join fetch h.recruit where h.user=:user and h.recruit.finished=false")
    List<Heart> findAllByUser(@Param("user")User user);
    
    @Transactional(readOnly = true)
    Optional<Heart> findByUserAndRecruit(User user, Recruit recruit);
    
    @Transactional(readOnly = true)
    @Query(value = "select h from Heart h join fetch h.user join fetch h.recruit where h.user=:user and h.recruit.finished=false",
            countQuery ="select count(h.id) from Heart h where h.user=:user and h.recruit.finished=false")
    Page<Heart> findByUser(@Param("user")User user, Pageable pageable);
}
