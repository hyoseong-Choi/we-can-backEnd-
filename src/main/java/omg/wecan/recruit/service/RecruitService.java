package omg.wecan.recruit.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.charity.entity.Charity;
import omg.wecan.charity.repository.CharityRepository;
import omg.wecan.recruit.dto.AddRecruitInput;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.repository.RecruitRepository;
import omg.wecan.user.entity.User;
import omg.wecan.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;
    private final CharityRepository charityRepository;
    
    public void addRecruit(AddRecruitInput addRecruitInput) {
        User user = userRepository.findById(1L).get();//토큰으로 찾은 유저로 수정
        Charity charity = charityRepository.findByName(addRecruitInput.getCharityName());
        
        Recruit recruit = Recruit.createRecruit(user, charity, addRecruitInput);
        
        recruitRepository.save(recruit);
    }
}
