package omg.wecan.user.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.exception.NoUserWithEmailException;
import omg.wecan.exception.NoUserWithNameAndEmailException;
import omg.wecan.user.dto.*;
import omg.wecan.user.entity.User;
import omg.wecan.user.repository.UserRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserFindPasswordService {
    private final UserRepository userRepository;
    private final MailSender mailSender;
    private final StringRedisTemplate redisTemplate;;
    
    //이메일 주소랑 이름 받아서 검증
    public UserCertificationInput certifyUser(UserCertificationInput userCertificationInput) {
        userRepository.findByEmailAndName(userCertificationInput.getEmail(), userCertificationInput.getName())
                .orElseThrow(() -> new NoUserWithNameAndEmailException("해당 이메일, 이름을 가진 유저가 없습니다"));
        return userCertificationInput;
    }
    
    // 메일 내용을 생성
    public CertificationMailOutput createMail(EmailCertificationInput emailCertificationInput) {
        String certificationNumber = getCertificationNumber();
        redisTemplate.opsForValue().set(emailCertificationInput.getEmail(), certificationNumber);
        
        return new CertificationMailOutput(emailCertificationInput.getEmail(), "WECAN! 사용자 인증 이메일 입니다.",
                "안녕하세요. WECAN! 인증 이메일 입니다.\n인증 번호는 " + certificationNumber + " 입니다."
                        + " 인증번호 입력 란에 인증번호를 입력해 주세요.");
    }
    
    //랜덤함수로 인증번호 만들기
    private String getCertificationNumber() {
        char[] certificationNumberArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        
        String certificationNumber = "";
        
        // 문자 배열 길이의 값을 랜덤으로 6개를 뽑아 구문을 작성함
        int index;
        for (int i = 0; i < 6; i++) {
            index = (int) (certificationNumberArray.length * Math.random());
            certificationNumber += certificationNumberArray[index];
        }
        return certificationNumber;
    }
    
    // 메일보내기
    public void mailSend(CertificationMailOutput certificationMailOutput) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(certificationMailOutput.getEmail());
        message.setSubject(certificationMailOutput.getTitle());
        message.setText(certificationMailOutput.getMessage());
        message.setFrom("gytjd9516@naver.com");
        message.setReplyTo("gytjd9516@naver.com");
        
        mailSender.send(message);
    }
    
    public String validateCertificationNum(ValidateCertificationNumInput validateCertificationNumInput) {
        String certificationNum = redisTemplate.opsForValue().get(validateCertificationNumInput.getEmail());
        if (certificationNum == null) {
            throw new NoSuchElementException("인증 번호가 만료되었습니다.");
        }
        if (!certificationNum.equals(validateCertificationNumInput.getCertificationNum())) {
            throw new NoSuchElementException("인증 번호가 틀렸습니다.");
        }
        redisTemplate.delete(validateCertificationNumInput.getEmail());
        
        return certificationNum;
    }
    
    //비밀번호 변경
    @Transactional
    public void updatePassword(NewPasswordInput newPasswordInput) {
        User user = userRepository.findByEmail(newPasswordInput.getEmail())
                .orElseThrow(() -> new NoUserWithEmailException("해당 이메일을 가진 유저가 없습니다"));
        user.changePassword(newPasswordInput.getNewPassword());
    }
}
