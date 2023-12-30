package omg.wecan.shop.repository;

import omg.wecan.shop.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserItemRepository extends JpaRepository<UserItem, Long>, UserItemRepositoryCustom {

}
