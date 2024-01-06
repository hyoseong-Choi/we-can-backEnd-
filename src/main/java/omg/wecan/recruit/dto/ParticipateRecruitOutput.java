package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.Participate;

@Data
public class ParticipateRecruitOutput {
    private Long id;
    private String title;
    private String challengePeriod;
    private String coverImage;
    private boolean isLeader;

    public ParticipateRecruitOutput(Participate participate) {
        this.id = participate.getRecruit().getId();
        this.title = participate.getRecruit().getTitle();
        this.challengePeriod = participate.getRecruit().getEndDate().plusDays(1) + " ~ " + participate.getRecruit().getChallengeEndTime();

        this.coverImage = participate.getRecruit().getCoverImageEndpoint();

        this.isLeader = participate.isLeader();
    }
}
