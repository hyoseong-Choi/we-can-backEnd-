package omg.wecan.recruit.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.charity.entity.Charity;
import omg.wecan.exception.recruitException.InvalidChallengeDateException;
import omg.wecan.global.entity.BaseEntity;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.recruit.dto.RecruitInput;
import omg.wecan.user.entity.User;

import java.time.LocalDate;
import java.util.List;

import static omg.wecan.exception.customException.ErrorCode.RECRUIT_DATE_INVALID;

@Entity
@Getter
@NoArgsConstructor
public class Recruit extends BaseEntity {
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
    private String title;
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
    private int heartNum;
    private String charityNotInDb;
    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<Participate> participate;
    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<Heart> heart;
    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<RecruitComment> recruitComment;
    
    public static Recruit createRecruit(User user, Charity charity, RecruitInput recruitInput, String coverImageEndpoint) {
        Recruit recruit = new Recruit();
        recruit.writer = user;
        recruit.charity = charity;
        recruit.title = recruitInput.getTitle();
        recruit.type = ChallengeType.from(recruitInput.getChallengeType());
        recruit.startDate = LocalDate.now();
        recruit.endDate = recruitInput.getChallengeStartDate().minusDays(1);
        if (recruit.endDate.isBefore(LocalDate.now())) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        recruit.challengeEndTime = recruitInput.getChallengeEndDate();
        if (recruit.challengeEndTime.isBefore(LocalDate.now().plusDays(7))) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        recruit.minPeople = recruitInput.getMinPeople();
        recruit.checkDay = recruitInput.getCheckDay();
        recruit.paymentType = PaymentType.from(recruitInput.getPaymentType());
        if (recruitInput.getContent() != null) {
            recruit.content = recruitInput.getContent();
        }
        
        recruit.coverImageEndpoint = coverImageEndpoint;
        recruit.fine = recruitInput.getFine();
        recruit.finished = false;
        recruit.heartNum = 0;
        return recruit;
    }
    
    public static Recruit createRecruitByCharityNotInDb(User user, RecruitInput recruitInput, String coverImageEndpoint) {
        Recruit recruit = new Recruit();
        recruit.writer = user;
        recruit.charityNotInDb = recruitInput.getCharityName();
        recruit.title = recruitInput.getTitle();
        recruit.type = ChallengeType.from(recruitInput.getChallengeType());
        recruit.startDate = LocalDate.now();
        recruit.endDate = recruitInput.getChallengeStartDate().minusDays(1);
        if (recruit.endDate.isBefore(LocalDate.now())) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        recruit.challengeEndTime = recruitInput.getChallengeEndDate();
        if (recruit.challengeEndTime.isBefore(LocalDate.now().plusDays(7))) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        recruit.minPeople = recruitInput.getMinPeople();
        recruit.checkDay = recruitInput.getCheckDay();
        recruit.paymentType = PaymentType.from(recruitInput.getPaymentType());
        if (recruitInput.getContent() != null) {
            recruit.content = recruitInput.getContent();
        }
        
        recruit.coverImageEndpoint = coverImageEndpoint;
        recruit.fine = recruitInput.getFine();
        recruit.finished = false;
        recruit.heartNum = 0;
        return recruit;
    }
    
    public void changeRecruit(Charity charity, RecruitInput recruitInput, String coverImageEndpoint) {
        this.charity = charity;
        this.type = ChallengeType.from(recruitInput.getChallengeType());
        this.startDate = LocalDate.now();
        this.endDate = recruitInput.getChallengeStartDate().minusDays(1);
        if (this.endDate.isBefore(LocalDate.now())) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        this.challengeEndTime = recruitInput.getChallengeEndDate();
        if (this.challengeEndTime.isBefore(LocalDate.now().plusDays(7))) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        this.minPeople = recruitInput.getMinPeople();
        this.checkDay = recruitInput.getCheckDay();
        this.paymentType = PaymentType.from(recruitInput.getPaymentType());
        if (recruitInput.getContent() != null) {
            this.content = recruitInput.getContent();
        }
        this.coverImageEndpoint = coverImageEndpoint;
        this.fine = recruitInput.getFine();
        this.finished = false;
    }
    
    public void changeRecruitByCharityNotInDb(RecruitInput recruitInput, String coverImageEndpoint) {
        this.charityNotInDb = recruitInput.getCharityName();
        this.type = ChallengeType.from(recruitInput.getChallengeType());
        this.startDate = LocalDate.now();
        this.endDate = recruitInput.getChallengeStartDate().minusDays(1);
        if (this.endDate.isBefore(LocalDate.now())) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        this.challengeEndTime = recruitInput.getChallengeEndDate();
        if (this.challengeEndTime.isBefore(LocalDate.now().plusDays(7))) {
            throw new InvalidChallengeDateException(RECRUIT_DATE_INVALID);
        }
        this.minPeople = recruitInput.getMinPeople();
        this.checkDay = recruitInput.getCheckDay();
        this.paymentType = PaymentType.from(recruitInput.getPaymentType());
        if (recruitInput.getContent() != null) {
            this.content = recruitInput.getContent();
        }
        this.coverImageEndpoint = coverImageEndpoint;
        this.fine = recruitInput.getFine();
        this.finished = false;
    }
    
    public void changeFinished() {
        this.finished = true;
    }
    
    public void addHeart() {
        this.heartNum++;
    }
    public void subHeart() {
        this.heartNum--;
    }
}
