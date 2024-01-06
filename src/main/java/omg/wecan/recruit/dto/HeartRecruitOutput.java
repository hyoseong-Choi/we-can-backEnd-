package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.Heart;

@Data
public class HeartRecruitOutput {
    private Long id;
    private String title;
    private String challengePeriod;
    private String coverImage;
    private boolean isHeart;

    public HeartRecruitOutput(Heart heart) {
        this.id = heart.getRecruit().getId();
        this.title = heart.getRecruit().getTitle();
        this.challengePeriod = heart.getRecruit().getEndDate().plusDays(1) + " ~ " + heart.getRecruit().getChallengeEndTime();

        this.coverImage = heart.getRecruit().getCoverImageEndpoint();

        this.isHeart = true;
    }
}
