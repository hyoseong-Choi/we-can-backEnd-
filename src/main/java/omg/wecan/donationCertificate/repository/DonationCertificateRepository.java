package omg.wecan.donationCertificate.repository;

import omg.wecan.donationCertificate.entity.DonationCertificate;
import omg.wecan.donationCertificate.exception.NoSuchDonationCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationCertificateRepository extends JpaRepository<DonationCertificate, Long> {
    default DonationCertificate getById(Long id){
        return this.findById(id).orElseThrow(NoSuchDonationCertificate::new);
    }

}
