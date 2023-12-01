package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.Enum.ChallengeType;

@Data
public class RecruitFindCond {
    private String title;
    private ChallengeType category;
}
