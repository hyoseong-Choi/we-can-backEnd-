package omg.wecan.recruit.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import omg.wecan.charity.entity.Charity;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.recruit.dto.AddRecruitInput;
import omg.wecan.user.entity.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@NoArgsConstructor
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charity_id")
    private Charity charity;
    
    @Enumerated(value = EnumType.STRING)
    private ChallengeType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate challengeEndTime;
    private int minPeople;
    private String checkDay;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String coverImageEndpoint;
    @Column(columnDefinition = "TEXT")
    private String contentImgEndpoint;
    private int fine;
    private boolean finished;
    
    public static Recruit createRecruit(User user, Charity charity, AddRecruitInput addRecruitInput) {
        Recruit recruit = new Recruit();
        recruit.writer = user;
        recruit.charity = charity;
        recruit.type = addRecruitInput.getChallengetype();
        recruit.startDate = LocalDate.now();
        recruit.endDate = LocalDate.now().plusDays(ChronoUnit.DAYS.between(LocalDate.now(), addRecruitInput.getChallengeStartDate())-1);
        recruit.challengeEndTime = addRecruitInput.getChallengeEndDate();
        recruit.minPeople = addRecruitInput.getMinPeople();
        recruit.checkDay = addRecruitInput.getCheckDay();
        recruit.paymentType = addRecruitInput.getPaymentType();
        if (addRecruitInput.getContent() != null) {
            recruit.content = addRecruitInput.getContent();
        }
        
        recruit.coverImageEndpoint = addRecruitInput.getCoverImageEndpoint();
        recruit.fine = addRecruitInput.getFine();
        recruit.finished = false;
        
        return recruit;
    }
}
