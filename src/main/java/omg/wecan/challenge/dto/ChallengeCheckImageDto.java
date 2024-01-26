package omg.wecan.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.entity.ChallengeCheck;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckImageDto {
    private Long challengeCheckId;
    private Long userId;
    private String nickName;
    private String checkDate;
    private List<String> checkImages;
    private int dislike;

    public ChallengeCheckImageDto(ChallengeCheck challengeCheck, List<String> checkImages, int dislike) {
        this.challengeCheckId = challengeCheck.getId();
        this.userId = challengeCheck.getUser().getUserId();
        this.nickName = challengeCheck.getUser().getNickName();
        this.checkDate = challengeCheck.getCheckDate().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        );
        this.checkImages= checkImages;
        this.dislike= dislike;
    }
}