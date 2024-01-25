package omg.wecan.shop.repository;

import omg.wecan.shop.entity.Emoticon;
import omg.wecan.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmoticonRepository extends JpaRepository<Emoticon, Long> {

    Emoticon findByItem(Item item);
}