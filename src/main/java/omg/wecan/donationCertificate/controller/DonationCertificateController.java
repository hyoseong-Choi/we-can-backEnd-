package omg.wecan.donationCertificate.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.donationCertificate.dto.request.DonationCertificateCreateRequest;
import omg.wecan.donationCertificate.dto.request.DonationCertificateUpdateRequest;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponse;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponses;
import omg.wecan.donationCertificate.entity.DonationCertificate;
import omg.wecan.donationCertificate.service.DonationCertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DonationCertificateController {
    private final DonationCertificateService donationCertificateService;

    @PostMapping("/donationCertificates")
    public ResponseEntity<DonationCertificateResponse> create(@RequestBody DonationCertificateCreateRequest request) {
        DonationCertificateResponse saved = donationCertificateService.save(request);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/donationCertificates")
    public ResponseEntity<DonationCertificateResponses> findAll() {
        DonationCertificateResponses response = donationCertificateService.findAll();
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DonationCertificateResponse> create(@PathVariable Long id, @RequestBody DonationCertificateUpdateRequest request) {
        DonationCertificate updated = donationCertificateService.update(id, request);
        DonationCertificateResponse response = new DonationCertificateResponse(updated);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        donationCertificateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
