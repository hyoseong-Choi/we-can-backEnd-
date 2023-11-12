package omg.wecan.recruit.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import omg.wecan.recruit.entity.Heart;

import java.time.LocalDate;

@Data
public class RecruitOutput {
    private String title;
    private String recruitPeriod;
    private boolean isHeart;
    
    @QueryProjection
    public RecruitOutput(String title, LocalDate endDate, LocalDate challengeEndDate, boolean isHeart) {
        this.title = title;
        this.recruitPeriod = endDate.plusDays(1).toString() + " ~ " + challengeEndDate.toString();
        this.isHeart = isHeart;
    }
}
