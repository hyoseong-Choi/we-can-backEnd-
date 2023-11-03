package omg.wecan.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.charity.dto.request.CharityCreateRequest;
import omg.wecan.charity.dto.response.CharityResponse;
import omg.wecan.charity.dto.response.CharityResponses;
import omg.wecan.charity.service.CharityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/charity")
@RestController
@RequiredArgsConstructor
public class CharityController {
    private final CharityService charityService;
    @PostMapping
    ResponseEntity<CharityResponse> create(@Valid @RequestBody CharityCreateRequest request){
        CharityResponse response = charityService.save(request);
        return ResponseEntity.created(URI.create("/api/charity/" + response.getId())).body(response);
    }

    @GetMapping
    ResponseEntity<CharityResponses> findAll(){
        CharityResponses response = charityService.findAll();
        return ResponseEntity.ok().body(response);
    }
}

