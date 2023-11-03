package omg.wecan.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.charity.dto.request.CharityCreateRequest;
import omg.wecan.charity.dto.response.CharityResponse;
import omg.wecan.charity.dto.response.CharityResponses;
import omg.wecan.charity.entity.CharityCategory;
import omg.wecan.charity.service.CharityService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/charities")
@RestController
@RequiredArgsConstructor
public class CharityController {
    private final CharityService charityService;
    @PostMapping
    ResponseEntity<CharityResponse> create(@Valid @RequestBody CharityCreateRequest request){
        CharityResponse response = charityService.save(request);
        return ResponseEntity.created(URI.create("/api/charities/" + response.getId())).body(response);
    }

    @GetMapping()
    ResponseEntity<CharityResponses> findAllByCategory(@RequestParam(name = "category", defaultValue = "ALL") String category){
        CharityCategory charityCategory = CharityCategory.valueOf(category.toUpperCase());
        CharityResponses response = charityService.findAllByCategory(charityCategory);
        return ResponseEntity.ok().body(response);
    }
}

