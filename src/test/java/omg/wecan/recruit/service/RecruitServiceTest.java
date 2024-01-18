package omg.wecan.recruit.service;

import omg.wecan.charity.entity.Charity;
import omg.wecan.charity.repository.CharityRepository;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.recruit.dto.RecruitDetailOutput;
import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.dto.RecruitInput;
import omg.wecan.recruit.dto.RecruitOutput;
import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.repository.HeartRepository;
import omg.wecan.recruit.repository.ParticipateRepository;
import omg.wecan.recruit.repository.RecruitCommentRepository;
import omg.wecan.recruit.repository.RecruitRepository;
import omg.wecan.user.entity.User;
import omg.wecan.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RecruitServiceTest {
    @Autowired
    private RecruitService recruitService;
    @Autowired
    private RecruitRepository recruitRepository;
    @Autowired
    private CharityRepository charityRepository;
    @Autowired
    private ParticipateRepository participateRepository;
    @Autowired
    private HeartRepository heartRepository;
    @Autowired
    private RecruitCommentRepository recruitCommentRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @Transactional
    void addRecruit() {
        User user = userRepository.findById(1L).get();
        RecruitInput recruitInput = new RecruitInput();
        recruitInput.setCharityName("푸른아시아");
        recruitInput.setTitle("제목");
        recruitInput.setChallengeType("etc");
        recruitInput.setChallengeStartDate(LocalDate.now().plusDays(7));
        recruitInput.setChallengeEndDate(LocalDate.now().plusDays(14));
        recruitInput.setMinPeople(5);
        recruitInput.setCheckDay("수");
        recruitInput.setPaymentType("team");
        recruitInput.setContent("내용");
        recruitInput.setFine(30);
        Recruit recruit;
        Participate participate;
        
        Optional<Charity> optionalCharityByName = charityRepository.findByName(recruitInput.getCharityName());
        String imgEndPoint = "사진주소";
        if (optionalCharityByName.isEmpty()) {
            recruit = Recruit.createRecruitByCharityNotInDb(user, recruitInput, imgEndPoint);
            recruit = recruitRepository.save(recruit);
            participate = participateRepository.save(Participate.createLeaderParticipate(user, recruit));
        } else {
            recruit = Recruit.createRecruit(user, optionalCharityByName.get(), recruitInput, imgEndPoint);
            recruit = recruitRepository.save(recruit);
            participate = participateRepository.save(Participate.createLeaderParticipate(user, recruit));
        }
        
        assertThat(recruit.getCharityNotInDb()).isEqualTo(null);
        assertThat(recruit.getCharity().getName()).isEqualTo("푸른아시아");
        assertThat(recruit.getTitle()).isEqualTo("제목");
        assertThat(recruit.getType()).isEqualTo(ChallengeType.ETC);
        assertThat(recruit.getStartDate()).isEqualTo(LocalDate.now());
        assertThat(recruit.getEndDate()).isEqualTo(LocalDate.now().plusDays(6));
        assertThat(recruit.getChallengeEndTime()).isEqualTo(LocalDate.now().plusDays(14));
        assertThat(recruit.getMinPeople()).isEqualTo(5);
        assertThat(recruit.getCheckDay()).isEqualTo("수");
        assertThat(recruit.getPaymentType()).isEqualTo(PaymentType.TEAM);
        assertThat(recruit.getContent()).isEqualTo("내용");
        assertThat(recruit.getFine()).isEqualTo(30);
        assertThat(participate.isLeader()).isEqualTo(true);
    }
    
    @Test
    @Transactional
    void updateRecruit() {
        RecruitInput recruitInput = new RecruitInput();
        recruitInput.setId(1L);
        recruitInput.setCharityName("푸른아시아");
        recruitInput.setTitle("제목");
        recruitInput.setChallengeType("etc");
        recruitInput.setChallengeStartDate(LocalDate.now().plusDays(7));
        recruitInput.setChallengeEndDate(LocalDate.now().plusDays(14));
        recruitInput.setMinPeople(5);
        recruitInput.setCheckDay("수");
        recruitInput.setPaymentType("team");
        recruitInput.setContent("내용");
        recruitInput.setFine(30);
        
        String imgEndPoint = "사진주소";
        
        Recruit recruit = recruitRepository.findById(recruitInput.getId()).get();
        Optional<Charity> optionalCharityByName = charityRepository.findByName(recruitInput.getCharityName());
        if (optionalCharityByName.isEmpty()) {
            recruit.changeRecruitByCharityNotInDb(recruitInput, imgEndPoint);
        } else {
            Charity charity = charityRepository.findByName(recruitInput.getCharityName()).get();
            recruit.changeRecruit(charity, recruitInput, imgEndPoint);
        }
        
        assertThat(recruit.getCharityNotInDb()).isEqualTo(null);
        assertThat(recruit.getCharity().getName()).isEqualTo("푸른아시아");
        assertThat(recruit.getType()).isEqualTo(ChallengeType.ETC);
        assertThat(recruit.getStartDate()).isEqualTo(LocalDate.now());
        assertThat(recruit.getEndDate()).isEqualTo(LocalDate.now().plusDays(6));
        assertThat(recruit.getChallengeEndTime()).isEqualTo(LocalDate.now().plusDays(14));
        assertThat(recruit.getMinPeople()).isEqualTo(5);
        assertThat(recruit.getCheckDay()).isEqualTo("수");
        assertThat(recruit.getPaymentType()).isEqualTo(PaymentType.TEAM);
        assertThat(recruit.getContent()).isEqualTo("내용");
        assertThat(recruit.getFine()).isEqualTo(30);
    }
    
    @Test
    @Transactional
    void deleteRecruit() {
        recruitRepository.deleteById(1L);
        Optional<Recruit> recruit = recruitRepository.findById(1L);
        assertThat(recruit).isEmpty();
    }
    
    @Test
    @Transactional
    void findRecruitDetail() {
        User user = userRepository.findById(1L).get();
//        User user = null;
        RecruitDetailOutput recruitDetail = recruitService.findRecruitDetail(user, 1L);
        assertThat(recruitDetail.getId()).isEqualTo(1L);
        assertThat(recruitDetail.isParticipate()).isTrue();
        assertThat(recruitDetail.isHeart()).isFalse();
    }
    
    @Test
    @Transactional
    void findThreeRecruit() {
//        User user = userRepository.findById(1L).get();
        User user = null;
        
        List<RecruitOutput> threeRecruit = recruitService.findThreeRecruit(user);
        RecruitOutput recruitOutput1 = threeRecruit.get(0);
        RecruitOutput recruitOutput2 = threeRecruit.get(1);
        RecruitOutput recruitOutput3 = threeRecruit.get(2);
        assertThat(recruitOutput1.getId()).isEqualTo(5);
        assertThat(recruitOutput2.getId()).isEqualTo(1);
        assertThat(recruitOutput3.getId()).isEqualTo(7);
        assertThat(recruitOutput1.isHeart()).isEqualTo(false);
        assertThat(recruitOutput2.isHeart()).isEqualTo(false);
        assertThat(recruitOutput3.isHeart()).isEqualTo(false);
        
    }
    
    @Test
    @Transactional
    void findRecruit() {
        User user = null;
        RecruitFindCond recruitFindCond = new RecruitFindCond();
//        recruitFindCond.setTitle();
        recruitFindCond.setCategory(ChallengeType.ETC);
        PageRequest pageRequest = PageRequest.of(0, 12);
        
        Page<RecruitOutput> recruit = recruitService.findRecruit(user, recruitFindCond, pageRequest);
        assertThat(recruit.getNumberOfElements()).isEqualTo(2);
    }
    
    @Test
    @Transactional
    void addRecruitComment() {
    }
    
    @Test
    @Transactional
    void addParticipate() {
    }
    
    @Test
    @Transactional
    void deleteParticipate() {
    }
    
    @Test
    @Transactional
    void addHeart() {
    }
    
    @Test
    @Transactional
    void deleteHeart() {
    }
    
    @Test
    @Transactional
    void findParticipateRecruit() {
    }
    
    @Test
    @Transactional
    void findHeartRecruit() {
    }
}