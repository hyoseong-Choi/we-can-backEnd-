package omg.wecan.donationCertificate.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.donationCertificate.dto.request.DonationCertificateCreateRequest;
import omg.wecan.donationCertificate.dto.request.DonationCertificateUpdateRequest;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponse;
import omg.wecan.donationCertificate.dto.response.DonationCertificateResponses;
import omg.wecan.donationCertificate.entity.DonationCertificate;
import omg.wecan.donationCertificate.repository.DonationCertificateRepository;
import omg.wecan.util.FileStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DonationCertificateService {
    private final DonationCertificateRepository donationCertificateRepository;
    private final FileStore fileStore;
    private final String basicImgUrl = "";

    public DonationCertificateResponse save(DonationCertificateCreateRequest donationCertificateCreateRequest) {
        String imgUrl = null;
        if(donationCertificateCreateRequest.getCoverImage() != null)
            imgUrl = fileStore.storeFile(donationCertificateCreateRequest.getCoverImage());

        DonationCertificate donationCertificate = donationCertificateCreateRequest.toEntity(imgUrl);
        donationCertificateRepository.save(donationCertificate);
        return new DonationCertificateResponse(donationCertificate);
    }

    public DonationCertificateResponses find(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<DonationCertificate> retPage = donationCertificateRepository.findAll(pageable);
        List<DonationCertificateResponse> certificates =
                retPage.stream().map(DonationCertificateResponse::new).toList();

        return new DonationCertificateResponses(certificates);
    }

    public DonationCertificate update(Long id, DonationCertificateUpdateRequest donationCertificateUpdateRequest) {
        DonationCertificate donationCertificate = donationCertificateRepository.getById(id);

        String currentImgUrl = donationCertificate.getImgEndpoint();
        String newImgUrl = null;

        if(donationCertificateUpdateRequest.getCoverImage() != null) {
            if(currentImgUrl != null)
                fileStore.deleteFile(currentImgUrl);
            newImgUrl = fileStore.storeFile(donationCertificateUpdateRequest.getCoverImage());
        }

        donationCertificate.change(donationCertificate.getTitle()
                , donationCertificate.getExplanation()
                , newImgUrl);

        return donationCertificate;
    }

    public void delete(Long id) {
        DonationCertificate donationCertificate = donationCertificateRepository.getById(id);

        String imgUrl = donationCertificate.getImgEndpoint();
        if(basicImgUrl.compareTo(imgUrl) != 0 && imgUrl != null)
            fileStore.deleteFile(imgUrl);

        donationCertificateRepository.deleteById(id);
    }
}
