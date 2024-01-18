package omg.wecan.shop.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.shop.dto.ItemDetailOutput;
import omg.wecan.shop.dto.ItemInput;
import omg.wecan.shop.dto.ItemsOutput;
import omg.wecan.shop.dto.MyItemsOutput;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.service.ItemService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShopController {
    private final ItemService itemService;
    
    @GetMapping("/shop/home")
    public ResponseEntity<ApiResponse<List<ItemsOutput>>> itemFindThree() {
        return ResponseEntity.ok(ApiResponse.success(itemService.findThreeItem()));
    }
    
    @GetMapping("/shop/me/emoticon")
    public ResponseEntity<ApiResponse<Page<MyItemsOutput>>> myEmoticonFind(@AuthenticationPrincipal User loginUser, @PageableDefault(size = 3) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findMyEmoticon(loginUser, pageable)));
    }
    
    @GetMapping("/shop/me/item")
    public ResponseEntity<ApiResponse<Page<MyItemsOutput>>> myItemFind(@AuthenticationPrincipal User loginUser, @PageableDefault(size = 3) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findMyItem(loginUser, pageable)));
    }
    
    @GetMapping("/shop/emoticon")
    public ResponseEntity<ApiResponse<Page<ItemsOutput>>> emoticonFind(@PageableDefault(size = 6) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findEmoticon(pageable)));
    }
    
    @GetMapping("/shop/item")
    public ResponseEntity<ApiResponse<Page<ItemsOutput>>> ItemFind(@PageableDefault(size = 6) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findItem(pageable)));
    }
    
    @GetMapping("/shop/{id}")
    public ResponseEntity<ApiResponse<ItemDetailOutput>> ItemDetail(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findItemDetail(id)));
    }
    
    @PostMapping("/shop/{id}/buy")
    public ResponseEntity<ApiResponse<Long>> itemBuy(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(itemService.buyItem(loginUser, id)));
    }
    
    @PostMapping("/item")
    public Item itemadd(@ModelAttribute ItemInput itemInput) {
        return itemService.addItem(itemInput);
    }
    
    
}
