package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.charity.entity.Charity;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.entity.RecruitComment;
import omg.wecan.user.entity.User;

import java.time.LocalDate;
import java.util.List;

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
    private Long participatePeople;
    private String checkDay;
    private PaymentType paymentType;
    private String content;
    private String coverImageEndpoint;
    private int fine;
    private boolean finished;
    private boolean isParticipate;
    private boolean isHeart;
    private List<RecruitComment> recruitComments;
    
    public RecruitDetailOutput(Recruit recruit, Long participatePeople, boolean isParticipate,
                               boolean isHeart,List<RecruitComment> recruitComments
                               ) {
        this.id = recruit.getId();
        this.writer = recruit.getWriter().getNickname();
        this.charityName = recruit.getCharity().getName();
        this.title = recruit.getTitle();
        this.type = recruit.getType();
        this.challengeStartTime = recruit.getEndDate().plusDays(1);
        this.challengeEndTime = recruit.getChallengeEndTime();
        this.minPeople = recruit.getMinPeople();
        this.participatePeople = participatePeople;
        this.checkDay = recruit.getCheckDay();
        this.paymentType = recruit.getPaymentType();
        this.content = recruit.getContent();
        this.coverImageEndpoint = recruit.getCoverImageEndpoint();
        this.fine = recruit.getFine();
        this.finished = recruit.isFinished();
        this.isHeart = isHeart;
        this.isParticipate = isParticipate;
    }
}
