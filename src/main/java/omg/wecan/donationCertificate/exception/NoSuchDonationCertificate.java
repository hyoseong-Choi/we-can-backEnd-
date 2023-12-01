package omg.wecan.donationCertificate.exception;

public class NoSuchDonationCertificate extends RuntimeException{
    public NoSuchDonationCertificate() {
        super("해당 기부증서가 존재하지 않습니다.");
    }
}
