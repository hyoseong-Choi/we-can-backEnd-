package omg.wecan.shop.repository;

import io.lettuce.core.dynamic.annotation.Param;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.entity.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM item ORDER BY RAND() limit 3",nativeQuery = true)
    List<Item> findRandom();
    
    @Transactional(readOnly = true)
    Page<Item> findByItemType(ItemType itemType, Pageable pageable);

    @Query("SELECT i FROM Item i LEFT JOIN i.userItems ui WHERE ui.id = :itemId")
    Item findItemAndReduceDislikeByItemId(@Param("itemId") Long itemId);

}
