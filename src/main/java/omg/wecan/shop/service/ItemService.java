package omg.wecan.shop.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.shopException.LackOfCandyException;
import omg.wecan.shop.dto.ItemDetailOutput;
import omg.wecan.shop.dto.ItemInput;
import omg.wecan.shop.dto.ItemsOutput;
import omg.wecan.shop.dto.MyItemsOutput;
import omg.wecan.shop.entity.Exemption;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.entity.ItemType;
import omg.wecan.shop.entity.UserItem;
import omg.wecan.shop.repository.ExemptionRepository;
import omg.wecan.shop.repository.ItemRepository;
import omg.wecan.shop.repository.UserItemRepository;
import omg.wecan.user.entity.User;
import omg.wecan.util.FileStore;
import omg.wecan.util.event.BuyItemEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static omg.wecan.exception.customException.ErrorCode.ITEM_NOT_FOUND;
import static omg.wecan.exception.customException.ErrorCode.REJECT_PAYMENT;
import static omg.wecan.shop.entity.UserItem.createUserItemEmoticon;
import static omg.wecan.shop.entity.UserItem.createUserItemItem;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ExemptionRepository exemptionRepository;
    private final FileStore fileStore;
    
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

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new CustomException(ITEM_NOT_FOUND)
        );
    }
    
    @Transactional
    public Long buyItem(User loginUser, Long id) {
        Item item = itemRepository.findById(id).get();
        if (loginUser.getCandy() >= item.getPrice()) {
            loginUser.minusCandy(item.getPrice());
            if (item.getItemType().equals(ItemType.ITEM)) {
                UserItem userItem = createUserItemItem(loginUser, item);
                exemptionRepository.save(new Exemption(userItem, RandomStringUtils.randomAlphabetic(20)));
                userItemRepository.save(userItem);
                eventPublisher.publishEvent(new BuyItemEvent(loginUser, item));
                return userItem.getId();
            }
            UserItem userItem = userItemRepository.save(createUserItemEmoticon(loginUser, item));
            eventPublisher.publishEvent(new BuyItemEvent(loginUser, item));
            return userItem.getId();
        }
        throw new LackOfCandyException(REJECT_PAYMENT);
    }

    @Transactional
    public Long buyItemV2(User loginUser, Long id) {
        Item item = getItemById(id);
        UserItem userItem = userItemRepository.save(createUserItemEmoticon(loginUser, item));
        return userItem.getId();
    }
    
    public Item addItem(ItemInput itemInput) {
        String imgEndpoint = fileStore.storeFile(itemInput.getCoverImage());
        return itemRepository.save(new Item(itemInput, imgEndpoint));
    }
    
    
    public void useExemption(String certificationString) {
        exemptionRepository.deleteByCertificationString(certificationString);
    }
}
