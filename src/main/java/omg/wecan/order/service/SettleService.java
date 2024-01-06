package omg.wecan.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserService;
import omg.wecan.util.event.SettleChallengeEvent;
import org.hibernate.Hibernate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SettleService {
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public void settleChallengeCandy(Challenge challenge) {
        PaymentType paymentType = challenge.getPaymentType();

        if(paymentType.equals(PaymentType.TEAM))
            settleTeamType(challenge);
        else if(paymentType.equals(PaymentType.PERSONAL))
            settlePersonalType(challenge);
        else{
            throw new IllegalArgumentException();
        }
    }

    private void settleTeamType(Challenge challenge){
        final int totalCheckNum = challenge.getTotalCheckNum();

        List<UserChallenge> userChallenges = challenge.getUserChallenge();

        for (UserChallenge userChallenge : userChallenges) {
            if(userChallenge.isPayed() == false)
                continue;

            User user = userChallenge.getUser();
            int failNum = userChallenge.getFailNum();
            int refundCandy = 0;

            double successRate = 1 - (double) failNum / totalCheckNum;
            log.info("successRate : {}", successRate);

            if(successRate >= 0.8){
                refundCandy = challenge.getDonationCandy();
            }

            userService.addCandy(user.getUserId(), refundCandy);
            eventPublisher.publishEvent(new SettleChallengeEvent(user, challenge, (long) refundCandy));
        }
    }

    private void settlePersonalType(Challenge challenge) {
        final int previousDonationCandy = challenge.getDonationCandy();

        List<UserChallenge> userChallenges = challenge.getUserChallenge();

        for (UserChallenge userChallenge : userChallenges) {
            if(userChallenge.isPayed() == false)
                continue;

            User user = userChallenge.getUser();
            Hibernate.initialize(user);

            int failNum = userChallenge.getFailNum();
            int refundCandy = Math.max(0, previousDonationCandy - challenge.getFinePerOnce() * failNum);

            log.info("refundCandy : {}", refundCandy);

            userService.addCandy(user.getUserId(), refundCandy);
            eventPublisher.publishEvent(new SettleChallengeEvent(user, challenge, (long) refundCandy));
        }
    }
}
