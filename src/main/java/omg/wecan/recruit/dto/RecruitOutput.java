package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.ElasticRecruit;
import omg.wecan.recruit.entity.Recruit;
import java.time.LocalDate;


@Data
public class RecruitOutput {
    private Long id;
    private String title;
    private String challengePeriod;
    private String category;
    private String coverImage;
    private boolean isHeart;

    public RecruitOutput(Recruit recruit, boolean isHeart) {
        this.id = recruit.getId();
        this.title = recruit.getTitle();
        this.challengePeriod = recruit.getEndDate().plusDays(1) + " ~ " + recruit.getChallengeEndTime().toString();
        this.category = recruit.getType().toString().toLowerCase();

        this.coverImage = recruit.getCoverImageEndpoint();

        this.isHeart = isHeart;
    }

    public RecruitOutput(ElasticRecruit recruit, boolean isHeart) {
        this.id = recruit.getId();
        this.title = recruit.getTitle();
        this.challengePeriod = LocalDate.parse(recruit.getEndDate()).plusDays(1) + " ~ " + LocalDate.parse(recruit.getChallengeEndTime());
        this.category = recruit.getType().toLowerCase();

        this.coverImage = recruit.getCoverImageEndpoint();

        this.isHeart = isHeart;
    }
}
