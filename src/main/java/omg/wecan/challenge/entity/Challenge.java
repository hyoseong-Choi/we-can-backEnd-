package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.recruit.entity.Recruit;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ChattingRoom chattingRoom;
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE)
    private List<UserChallenge> userChallenge;
    @Enumerated(value = EnumType.STRING)
    private ChallengeType challengeType;
    private LocalDate startDate;
    private LocalDate endDate;
    private int peopleNum;
    private String checkDay;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    private String charityName;
    @Column(columnDefinition = "TEXT")
    private String coverImageEndpoint;
    private boolean finished;
    private int finePerOnce;
  
    public static Challenge createChallenge(Recruit recruit, int peopleNum) {
        Challenge challenge = new Challenge();
        challenge.title = recruit.getTitle();
        challenge.chattingRoom = new ChattingRoom(challenge);
        challenge.challengeType = recruit.getType();
        challenge.startDate = recruit.getEndDate().plusDays(1);
        challenge.endDate = recruit.getChallengeEndTime();
        challenge.peopleNum = peopleNum;
        challenge.checkDay = recruit.getCheckDay();
        challenge.paymentType = recruit.getPaymentType();
        challenge.charityName = initCharityName(recruit);
        challenge.coverImageEndpoint = recruit.getCoverImageEndpoint();
        challenge.finished = false;
        challenge.finePerOnce = recruit.getFine();
        return challenge;
    }
    
    private static String initCharityName(Recruit recruit) {
        if (recruit.getCharity() == null) {
            return recruit.getCharityNotInDb();
        }
        return recruit.getCharity().getName();
    }
}