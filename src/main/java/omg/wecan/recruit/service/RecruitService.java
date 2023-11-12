package omg.wecan.recruit.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.charity.entity.Charity;
import omg.wecan.charity.repository.CharityRepository;
import omg.wecan.recruit.dto.*;
import omg.wecan.recruit.entity.Heart;
import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.repository.HeartRepository;
import omg.wecan.recruit.repository.ParticipateRepository;
import omg.wecan.recruit.repository.RecruitRepository;
import omg.wecan.user.entity.User;
import omg.wecan.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;
    private final CharityRepository charityRepository;
    private final ParticipateRepository participateRepository;
    private final HeartRepository heartRepository;
    
    public Recruit addRecruit(RecruitInput recruitInput) {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        Charity charity = charityRepository.findByName(recruitInput.getCharityName());
        Recruit recruit = Recruit.createRecruit(user, charity, recruitInput);
        recruit = recruitRepository.save(recruit);
        participateRepository.save(Participate.createLeaderParticipate(user, recruit));
        return recruit;
    }
    
    @Transactional
    public Recruit updateRecruit(RecruitInput recruitInput) {
        Recruit recruit = recruitRepository.findById(recruitInput.getId()).get();
        Charity charity = charityRepository.findByName(recruitInput.getCharityName());
        recruit.changeRecruit(charity, recruitInput);
        return recruit;
    }
    
    @Transactional
    public Long deleteRecruit(Long id) {
        recruitRepository.deleteById(id);
        return id;
    }
    
    public RecruitDetailOutput findRecruitDetail(Long id) {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        Recruit recruit = recruitRepository.findById(id).get();
        if (heartRepository.findByUserAndRecruit(user, recruit).orElse(null) == null) {
            return  new RecruitDetailOutput(recruit, false);
        }
        return new RecruitDetailOutput(recruit, true);
    }
    
    public List<RecruitOutput> findThreeRecruit() {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        List<Recruit> recruits = recruitRepository.findTop3ByOrderByHeartNumDesc();
        List<RecruitOutput> recruitOutputs = new ArrayList<>();
        for (Recruit recruit : recruits) {
            if (heartRepository.findByUserAndRecruit(user, recruit).orElse(null) == null) {
                RecruitOutput recruitOutput = new RecruitOutput(recruit.getTitle(), recruit.getEndDate(), recruit.getChallengeEndTime(), false);
                recruitOutputs.add(recruitOutput);
                continue;
            }
            RecruitOutput recruitOutput = new RecruitOutput(recruit.getTitle(), recruit.getEndDate(), recruit.getChallengeEndTime(), true);
            recruitOutputs.add(recruitOutput);
        }
        return recruitOutputs;
    }
    
    public List<RecruitOutput> findRecentRecruit() {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        //페이징 해야됨
    }
    
    public Participate addParticipate(AddParticipateInput addParticipateInput) {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        Recruit recruit = recruitRepository.findById(addParticipateInput.getRecruitId()).get();
        Participate participate = Participate.createParticipate(user, recruit);
        return participateRepository.save(participate);
    }
    
    @Transactional
    public Heart addHeart(AddHeartInput addHeartInput) {
        User user = userRepository.findById(5L).get();//토큰으로 찾은 유저로 수정
        Recruit recruit = recruitRepository.findById(addHeartInput.getRecruitId()).get();
        Heart heart = Heart.createHeart(user, recruit);
        recruit.addHeart();
        return heartRepository.save(heart);
    }
}
