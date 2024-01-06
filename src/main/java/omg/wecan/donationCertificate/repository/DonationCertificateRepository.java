package omg.wecan.donationCertificate.repository;

import omg.wecan.donationCertificate.entity.DonationCertificate;
import omg.wecan.donationCertificate.exception.NoSuchDonationCertificate;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationCertificateRepository extends JpaRepository<DonationCertificate, Long> {
    Page<DonationCertificate> findAll(Pageable pageable);
    default DonationCertificate getById(Long id){
        return this.findById(id).orElseThrow(() -> new CustomException(ErrorCode.DONATIONCERTIFICATE_NOT_FOUND, "DonationCertificate Id: " + id));
    }

}
