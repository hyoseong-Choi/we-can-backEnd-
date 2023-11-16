package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.Recruit;


@Data
public class RecruitOutput {
    private String title;
    private String recruitPeriod;
    private String category;
    private boolean isHeart;
    
    public RecruitOutput(Recruit recruit, boolean isHeart) {
        this.title = recruit.getTitle();
        this.recruitPeriod = recruit.getEndDate().plusDays(1) + " ~ " + recruit.getChallengeEndTime().toString();
        this.category = recruit.getType().toString().toLowerCase();
        this.isHeart = isHeart;
    }
}
