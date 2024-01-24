package omg.wecan.challenge.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.Enum.ChallengeStateType;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.entity.ChallengeCheck;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.challenge.repository.ChallengeCheckRepository;
import omg.wecan.challenge.repository.ChallengeRepository;
import omg.wecan.challenge.repository.UserChallengeRepository;
import omg.wecan.order.service.SettleService;
import omg.wecan.user.entity.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengeStateService {
    private final SettleService settleService;
    private final ChallengeRepository challengeRepository;
    private final ChallengeCheckRepository challengeCheckRepository;
    private final UserChallengeRepository userChallengeRepository;

    @Transactional
    @Scheduled(cron = "00 00 00 * * *")
    public void challengeStateService() {

        //종료 일에 도달한 챌린지 상태 변경
        List<Challenge> completedChallenges = challengeRepository.findByEndDateIs(LocalDate.now().minusDays(1));
        for (Challenge challenge : completedChallenges) {
            challenge.setState(ChallengeStateType.Completed);

            settleService.settleChallengeCandy(challenge);
        }

        // Active 챌린지에 참여한 User 리스트 가져오기
        List<UserChallenge> userChallenges = userChallengeRepository.findByChallenge_State(ChallengeStateType.Active);

        // 어제 날짜 ChallengeCheck에서 User 리스트 가져오기
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime yesterdayStart = yesterday.atTime(LocalTime.MIN);
        LocalDateTime yesterdayEnd = yesterday.atTime(LocalTime.MAX);
        List<ChallengeCheck> yesterdayChallengeChecks = challengeCheckRepository.findByCheckDateBetween(yesterdayStart, yesterdayEnd);

        for (UserChallenge userChallenge : userChallenges) {
            // User가 오늘 ChallengeCheck에 존재하지 않으면
            if (!isUserPresentInChallengeCheck(userChallenge.getUser(), userChallenge.getChallenge().getId(), yesterdayChallengeChecks)) {
                userChallenge.increaseFailNum();
                userChallengeRepository.save(userChallenge);
            }
        }

    }

    private boolean isUserPresentInChallengeCheck(User user, Long challengeId, List<ChallengeCheck> challengeChecks) {
        for (ChallengeCheck challengeCheck : challengeChecks) {
            if (challengeCheck.getChallenge().getId().equals(challengeId) && challengeCheck.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }
}
