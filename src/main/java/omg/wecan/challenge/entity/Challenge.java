package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import omg.wecan.challenge.Enum.ChallengeStateType;
import omg.wecan.chatting.entity.ChattingRoom;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.review.entity.Review;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static omg.wecan.challenge.Enum.ChallengeStateType.Active;

@Entity
@Data
@NoArgsConstructor
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;
    private String title;
    private String chattingRoomId;
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
    @Enumerated(value = EnumType.STRING)
    private ChallengeStateType state;
    private int finePerOnce;
    private int donationCandy;
    private int totalCheckNum;
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<ChallengeCheck> challengeChecks;
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToOne(mappedBy = "challenge", cascade = CascadeType.ALL)
    private ChattingRoom chattingRoom;

    public static Challenge createChallenge(Recruit recruit, int peopleNum) {
        Challenge challenge = new Challenge();
        challenge.title = recruit.getTitle();
        challenge.challengeType = recruit.getType();
        challenge.startDate = recruit.getEndDate().plusDays(1);
        challenge.endDate = recruit.getChallengeEndTime();
        challenge.peopleNum = peopleNum;
        challenge.checkDay = recruit.getCheckDay();
        challenge.paymentType = recruit.getPaymentType();
        challenge.charityName = initCharityName(recruit);
        challenge.coverImageEndpoint = recruit.getCoverImageEndpoint();
        challenge.state = Active;
        challenge.finePerOnce = recruit.getFine();
        challenge.totalCheckNum = countCheckDays(challenge.checkDay, challenge.startDate, challenge.endDate);
        challenge.donationCandy = challenge.totalCheckNum * challenge.finePerOnce;

        return challenge;
    }

    private static int countCheckDays(String checkDay, LocalDate startDate, LocalDate endDate) {
        int count = 0;
        DayOfWeek[] daysOfWeek = DayOfWeek.values();

        for (char day : checkDay.toCharArray()) {
            DayOfWeek targetDay = getDayOfWeek(day);
            if (targetDay != null) {
                for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                    if (date.getDayOfWeek() == targetDay) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static DayOfWeek getDayOfWeek(char day) {
        switch (day) {
            case '월':
                return DayOfWeek.MONDAY;
            case '화':
                return DayOfWeek.TUESDAY;
            case '수':
                return DayOfWeek.WEDNESDAY;
            case '목':
                return DayOfWeek.THURSDAY;
            case '금':
                return DayOfWeek.FRIDAY;
            case '토':
                return DayOfWeek.SATURDAY;
            case '일':
                return DayOfWeek.SUNDAY;
            default:
                return null;
        }
    }
    
    private static String initCharityName(Recruit recruit) {
        if (recruit.getCharity() == null) {
            return recruit.getCharityNotInDb();
        }
        return recruit.getCharity().getName();
    }

}