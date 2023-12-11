package omg.wecan.shop.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.exception.shopException.LackOfCandyException;
import omg.wecan.shop.dto.ItemDetailOutput;
import omg.wecan.shop.dto.ItemsOutput;
import omg.wecan.shop.dto.MyItemsOutput;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.entity.ItemType;
import omg.wecan.shop.entity.UserItem;
import omg.wecan.shop.repository.ItemRepository;
import omg.wecan.shop.repository.UserItemRepository;
import omg.wecan.user.entity.User;
import omg.wecan.util.event.BuyItemEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static omg.wecan.exception.customException.ErrorCode.REJECT_PAYMENT;
import static omg.wecan.shop.entity.UserItem.createUserItem;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;
    private final ApplicationEventPublisher eventPublisher;
    
    public List<ItemsOutput> findThreeItem() {
        return itemRepository.findRandom().stream().map(ItemsOutput::new).collect(Collectors.toList());
    }
    
    public Page<MyItemsOutput> findMyEmoticon(User loginUser, Pageable pageable) {
        return userItemRepository.findEmoticonByUser(loginUser, pageable);
    }
    
    public Page<MyItemsOutput> findMyItem(User loginUser, Pageable pageable) {
        return userItemRepository.findItemByUser(loginUser, pageable);
    }
    
    public Page<ItemsOutput> findEmoticon(Pageable pageable) {
        return itemRepository.findByItemType(ItemType.EMOTICON, pageable).map(ItemsOutput::new);
    }
    
    public Page<ItemsOutput> findItem(Pageable pageable) {
        return itemRepository.findByItemType(ItemType.ITEM, pageable).map(ItemsOutput::new);
    }
    
    public ItemDetailOutput findItemDetail(Long id) {
        Item item = itemRepository.findById(id).get();
        return new ItemDetailOutput(item);
    }
    
    @Transactional
    public Long buyItem(User loginUser, Long id) {
        Item item = itemRepository.findById(id).get();
        if (loginUser.getCandy() >= item.getPrice()) {
            loginUser.minusCandy(item.getPrice());
            UserItem userItem = userItemRepository.save(createUserItem(loginUser, item));
            eventPublisher.publishEvent(new BuyItemEvent(loginUser, item));
            return userItem.getId();
        }
        throw new LackOfCandyException(REJECT_PAYMENT);
    }
}
