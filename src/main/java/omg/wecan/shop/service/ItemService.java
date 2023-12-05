package omg.wecan.shop.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.shop.dto.ItemDetailOutput;
import omg.wecan.shop.dto.ItemsOutput;
import omg.wecan.shop.dto.MyItemsOutput;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.entity.ItemType;
import omg.wecan.shop.repository.ItemRepository;
import omg.wecan.shop.repository.UserItemRepository;
import omg.wecan.user.entity.User;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;
    
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
    
    public ItemDetailOutput findItemDetail(Long id) throws IOException {
        Item item = itemRepository.findById(id).get();
        return new ItemDetailOutput(item, Base64.getEncoder().encodeToString(new UrlResource("file:" + item.getImgEndpoint()).getContentAsByteArray()));
    }
}
