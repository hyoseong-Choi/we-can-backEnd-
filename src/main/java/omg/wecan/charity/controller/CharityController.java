package omg.wecan.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.charity.dto.request.CharityCreateRequest;
import omg.wecan.charity.dto.request.CharityUpdateRequest;
import omg.wecan.charity.dto.response.CharityResponse;
import omg.wecan.charity.dto.response.CharityResponses;
import omg.wecan.charity.entity.CharityCategory;
import omg.wecan.charity.service.CharityService;
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
    ResponseEntity<CharityResponse> create(@Valid @RequestBody CharityCreateRequest request){
        CharityResponse response = charityService.save(request);
        return ResponseEntity.created(URI.create("/charities/" + response.getId())).body(response);
    }

    @GetMapping()
    ResponseEntity<CharityResponses> findAll(@RequestParam(name = "category", defaultValue = "ALL") String category,
                                             @RequestParam(name = "explanation", required = false) String explanation){

        CharityCategory charityCategory = CharityCategory.valueOf(category.toUpperCase());
        CharityResponses response;

        if(explanation == null)
            response = charityService.findAllByCategory(charityCategory);
        else
            response = charityService.findAllByCategoryAndName(charityCategory, explanation);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<CharityResponse> findById(@PathVariable Long id){
        CharityResponse response = charityService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    //admin 권한 추가 필요
    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CharityUpdateRequest request){
        charityService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    //admin 권한 추가 필요
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        charityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

