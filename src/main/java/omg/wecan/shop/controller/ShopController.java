package omg.wecan.shop.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.shop.dto.ItemDetailOutput;
import omg.wecan.shop.dto.ItemsOutput;
import omg.wecan.shop.dto.MyItemsOutput;
import omg.wecan.shop.service.ItemService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<ApiResponse<Page<ItemsOutput>>> emoticonFind(@AuthenticationPrincipal User loginUser, @PageableDefault(size = 6) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findEmoticon(pageable)));
    }
    
    @GetMapping("/shop/item")
    public ResponseEntity<ApiResponse<Page<ItemsOutput>>> ItemFind(@AuthenticationPrincipal User loginUser, @PageableDefault(size = 6) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findItem(pageable)));
    }
    
    @GetMapping("/shop/{id}")
    public ResponseEntity<ApiResponse<ItemDetailOutput>> ItemDetail(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(itemService.findItemDetail(id)));
    }
    
    @PostMapping("/shop/{id}/buy")
    public ResponseEntity<ApiResponse<Long>> itemBuy(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(itemService.buyItem(loginUser, id)));
    }
    
    
}
