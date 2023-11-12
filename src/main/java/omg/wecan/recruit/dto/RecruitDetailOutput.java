package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.charity.entity.Charity;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;

import java.time.LocalDate;

@Data
public class RecruitDetailOutput {
    private Long id;
    private String writer;
    private String charityName;
    private String title;
    private ChallengeType type;
    private LocalDate challengeStartTime;
    private LocalDate challengeEndTime;
    private int minPeople;
    private String checkDay;
    private PaymentType paymentType;
    private String content;
    private String coverImageEndpoint;
    private int fine;
    private boolean finished;
    private boolean isHeart;
    
    public RecruitDetailOutput(Recruit recruit, boolean isHeart) {
        this.id = recruit.getId();
        this.writer = recruit.getWriter().getNickname();
        this.charityName = recruit.getCharity().getName();
        this.title = recruit.getTitle();
        this.type = recruit.getType();
        this.challengeStartTime = recruit.getEndDate().plusDays(1);
        this.challengeEndTime = recruit.getChallengeEndTime();
        this.minPeople = recruit.getMinPeople();
        this.checkDay = recruit.getCheckDay();
        this.paymentType = recruit.getPaymentType();
        this.content = recruit.getContent();
        this.coverImageEndpoint = recruit.getCoverImageEndpoint();
        this.fine = recruit.getFine();
        this.finished = recruit.isFinished();
        this.isHeart = isHeart;
    }
}
