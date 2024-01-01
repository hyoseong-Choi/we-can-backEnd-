package omg.wecan.challenge.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.Enum.ChallengeStateType;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.repository.ChallengeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengeStateService {
    private final ChallengeRepository challengeRepository;
    
    //끝난 모집글 가져와서 피시니 해주고 참여한 애들 챌린지 만들어주고 userchallenge로 보내주고
    @Transactional
    @Scheduled(cron = "00 31 02 * * *")
    public void challengeStateService() {

        //시작 일에 도달한 챌린지 상태 병경
        List<Challenge> activeChallenges = challengeRepository.findByStartDateIs(LocalDate.now());
        for (Challenge challenge : activeChallenges) {
            challenge.setState(ChallengeStateType.Active);
        }

        List<Challenge> completedChallenges = challengeRepository.findByEndDateIs(LocalDate.now().minusDays(1));
        for (Challenge challenge : completedChallenges) {
            challenge.setState(ChallengeStateType.Completed);

        }

    }
}
