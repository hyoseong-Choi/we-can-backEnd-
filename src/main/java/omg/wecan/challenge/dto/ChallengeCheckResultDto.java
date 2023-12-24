package omg.wecan.challenge.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.entity.ChallengeCheck;
import omg.wecan.review.entity.Review;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckResultDto {
    private Long challengeCheckId;
    private Long challengeId;
    private Long userId;
    private String nickName;
    private LocalDateTime checkDate;
    private List<String> checkImages;
    private int dislike;

    public ChallengeCheckResultDto(ChallengeCheck challengeCheck, List<String> checkImages) {
        this.challengeCheckId = challengeCheck.getId();
        this.challengeId = challengeCheck.getChallenge().getId();
        this.userId = challengeCheck.getUser().getUserId();
        this.nickName = challengeCheck.getUser().getNickName();
        this.checkDate = challengeCheck.getCheckDate();
        this.checkImages= checkImages;
        this.dislike= 0;
    }
}