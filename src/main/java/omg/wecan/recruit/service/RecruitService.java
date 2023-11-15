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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public Long deleteRecruit(Long id) {
        recruitRepository.deleteById(id);
        return id;
    }
    
    public RecruitDetailOutput findRecruitDetail(Long id) {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        Recruit recruit = recruitRepository.findById(id).get();
        if (heartRepository.findByUserAndRecruit(user, recruit).orElse(null) == null) {
            return new RecruitDetailOutput(recruit, false);
        }
        return new RecruitDetailOutput(recruit, true);
    }
    //디테일 참여, 댓글 해야함
    public List<RecruitOutput> findThreeRecruit() {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        List<Recruit> recruits = recruitRepository.findTop3ByOrderByHeartNumDesc();
        List<RecruitOutput> recruitOutputs = new ArrayList<>();
        for (Recruit recruit : recruits) {
            if (heartRepository.findByUserAndRecruit(user, recruit).orElse(null) == null) {
                RecruitOutput recruitOutput = new RecruitOutput(recruit, false);
                recruitOutputs.add(recruitOutput);
                continue;
            }
            RecruitOutput recruitOutput = new RecruitOutput(recruit, true);
            recruitOutputs.add(recruitOutput);
        }
        return recruitOutputs;
    }
    
    public Page<RecruitOutput> findRecruit(RecruitFindCond recruitFindCond, Pageable pageable) {
        User user = userRepository.findById(5L).get();//토큰으로 찾은 유저로 수정
        //모집글 중에 찜한거 있으면 표시해주기 위해 heart 가져와서 그중에 유저가 찜한 recruit 있는지 확인.
        //유저가 찜한 모집글이 없으면 전부 false로 리턴
        List<Heart> heartsByUser = heartRepository.findAllByUser(user);
        if (heartsByUser.isEmpty()) {
            return recruitRepository.findAllByCond(recruitFindCond, pageable)
                    .map(recruit -> new RecruitOutput(recruit, false));
        }
        //유저가 찜한 모집글이 있으면 그것만 true, 나머지 false로 리턴
        List<RecruitOutput> recruitOutputs = new LinkedList<>();
        List<Recruit> recruits = recruitRepository.findAllByCond(recruitFindCond);
        for (Recruit recruit : recruits) {
            recruitOutputs.add(getRecruitOutputByHeart(recruit, heartsByUser));
        }
        return new PageImpl<>(recruitOutputs, pageable, recruits.size());
    }
    
    private RecruitOutput getRecruitOutputByHeart(Recruit recruit, List<Heart> heartsByUser) {
        for (Heart heart : heartsByUser) {
            if (heart.getRecruit().equals(recruit)) {
                return new RecruitOutput(recruit, true);
            }
        }
        return new RecruitOutput(recruit, false);
    }
    
    public Participate addParticipate(AddParticipateInput addParticipateInput) {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        Recruit recruit = recruitRepository.findById(addParticipateInput.getRecruitId()).get();
        Participate participate = Participate.createParticipate(user, recruit);
        return participateRepository.save(participate);
    }

    public Long deleteParticipate(Long participateId) {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        participateRepository.deleteById(participateId);
        return participateId;
    }
    
    @Transactional
    public Heart addHeart(AddHeartInput addHeartInput) {
        User user = userRepository.findById(5L).get();//토큰으로 찾은 유저로 수정
        Recruit recruit = recruitRepository.findById(addHeartInput.getRecruitId()).get();
        Heart heart = Heart.createHeart(user, recruit);
        recruit.addHeart();
        return heartRepository.save(heart);
    }
    @Transactional
    public Long deleteHeart(Long heartId) {
        User user = userRepository.findById(5L).get();//토큰으로 찾은 유저로 수정
        Heart heart = heartRepository.findById(heartId).get();
        Recruit recruit = recruitRepository.findById(heart.getRecruit().getId()).get();
        recruit.subHeart();
        heartRepository.delete(heart);
        return heartId;
    }
}
