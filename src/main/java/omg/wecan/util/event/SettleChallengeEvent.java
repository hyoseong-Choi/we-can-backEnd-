package omg.wecan.util.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.user.entity.User;

@Getter
@RequiredArgsConstructor
public class SettleChallengeEvent {
    private final User user;
    private final Challenge challenge;
    private final Long refundCandy;
}
