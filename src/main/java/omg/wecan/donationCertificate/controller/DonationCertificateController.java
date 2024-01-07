package omg.wecan.donationCertificate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.donationCertificate.dto.request.DonationCertificateCreateRequest;
import omg.wecan.donationCertificate.dto.request.DonationCertificateUpdateRequest;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponse;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponses;
import omg.wecan.donationCertificate.entity.DonationCertificate;
import omg.wecan.donationCertificate.service.DonationCertificateService;
import omg.wecan.util.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DonationCertificateController {
    private final DonationCertificateService donationCertificateService;
    private final ModelMapper modelMapper;

    @PostMapping("/donationCertificates")
    public ResponseEntity<ApiResponse<DonationCertificateResponse>> create(@Valid @ModelAttribute DonationCertificateCreateRequest request) {
        DonationCertificateResponse innerResponse = donationCertificateService.save(request);
        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    @GetMapping("/donationCertificates")
    public ResponseEntity<ApiResponse<DonationCertificateResponses>> find(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        DonationCertificateResponses innerResponse = donationCertificateService.find(page, size);
        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    @PatchMapping("/donationCertificates/{id}")
    public ResponseEntity<ApiResponse<DonationCertificateResponse>> update(@PathVariable Long id, @ModelAttribute DonationCertificateUpdateRequest request) {
        DonationCertificate updated = donationCertificateService.update(id, request);
        DonationCertificateResponse innerResponse = new DonationCertificateResponse(updated);

        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    @DeleteMapping("/donationCertificates/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        donationCertificateService.delete(id);
        return ResponseEntity.ok().body(ApiResponse.success(null));
    }
}
