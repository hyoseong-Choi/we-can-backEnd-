package omg.wecan.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.charity.dto.request.CharityCreateRequest;
import omg.wecan.charity.dto.request.CharityUpdateRequest;
import omg.wecan.charity.dto.response.CharityResponse;
import omg.wecan.charity.dto.response.CharityResponses;
import omg.wecan.charity.entity.CharityCategory;
import omg.wecan.charity.service.CharityService;
import omg.wecan.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/charities")
@RestController
@RequiredArgsConstructor
public class CharityController {
    private final CharityService charityService;

    //권한 추가 필요
    @PostMapping
    ResponseEntity<ApiResponse<CharityResponse>> create(@Valid @RequestBody CharityCreateRequest request){
        CharityResponse innerResponse = charityService.save(request);
        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    @GetMapping()
    ResponseEntity<ApiResponse<CharityResponses>> findAll(@RequestParam(name = "category", defaultValue = "ALL") String category,
                                             @RequestParam(name = "name", required = false) String name){

        CharityCategory charityCategory = CharityCategory.valueOf(category.toUpperCase());
        CharityResponses innerResponse;

        if(name == null)
            innerResponse = charityService.findAllByCategory(charityCategory);
        else
            innerResponse = charityService.findAllByCategoryAndName(charityCategory, name);

        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<CharityResponse>> findById(@PathVariable Long id){
        CharityResponse innerResponse = charityService.findById(id);

        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    //admin 권한 추가 필요
    @PatchMapping("/{id}")
    ResponseEntity<ApiResponse<CharityResponse>> update(@PathVariable Long id, @RequestBody CharityUpdateRequest request){
        CharityResponse innerResponse = charityService.update(id, request);

        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    //admin 권한 추가 필요
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        charityService.deleteById(id);
        return ResponseEntity.ok().body(ApiResponse.success(null));
    }
}

