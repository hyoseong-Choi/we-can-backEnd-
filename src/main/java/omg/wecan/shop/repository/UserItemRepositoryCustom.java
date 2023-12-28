package omg.wecan.shop.repository;

import omg.wecan.exception.shopException.NoUserItemException;
import omg.wecan.shop.dto.MyItemsOutput;
import omg.wecan.shop.entity.Item;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserItemRepositoryCustom {
    Page<MyItemsOutput> findEmoticonByUser(User user, Pageable pageable);
    Page<MyItemsOutput> findItemByUser(User user, Pageable pageable);
}
