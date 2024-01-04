package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.Heart;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.util.Base64;

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
        try {
            this.coverImage = Base64.getEncoder().encodeToString(new UrlResource("file:" + heart.getRecruit().getCoverImageEndpoint()).getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.isHeart = true;
    }
}
