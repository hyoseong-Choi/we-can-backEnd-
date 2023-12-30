package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.Participate;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.util.Base64;

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
        try {
            this.coverImage = Base64.getEncoder().encodeToString(new UrlResource("file:" + participate.getRecruit().getCoverImageEndpoint()).getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.isLeader = participate.isLeader();
    }
}
