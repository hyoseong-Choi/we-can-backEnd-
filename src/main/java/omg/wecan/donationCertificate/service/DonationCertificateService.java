package omg.wecan.donationCertificate.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.donationCertificate.dto.request.DonationCertificateCreateRequest;
import omg.wecan.donationCertificate.dto.request.DonationCertificateUpdateRequest;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponse;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponses;
import omg.wecan.donationCertificate.entity.DonationCertificate;
import omg.wecan.donationCertificate.repository.DonationCertificateRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DonationCertificateService {
    private final DonationCertificateRepository donationCertificateRepository;

    public DonationCertificateResponse save(DonationCertificateCreateRequest donationCertificateCreateRequest) {
        DonationCertificate donationCertificate = donationCertificateCreateRequest.toEntity();
        return new DonationCertificateResponse(donationCertificate);
    }

    public DonationCertificateResponses findAll() {
        List<DonationCertificateResponse> certificates = donationCertificateRepository
                .findAll(Sort.by(Sort.Direction.ASC, "created_at"))
                .stream().map(DonationCertificateResponse::new).toList();

        return new DonationCertificateResponses(certificates);
    }

    public DonationCertificate update(Long id, DonationCertificateUpdateRequest donationCertificateUpdateRequest) {
        DonationCertificate donationCertificate = donationCertificateRepository.getById(id);

        donationCertificate.change(donationCertificateUpdateRequest);
        return donationCertificate;
    }

    public void delete(Long id) {
        donationCertificateRepository.deleteById(id);
    }

}
