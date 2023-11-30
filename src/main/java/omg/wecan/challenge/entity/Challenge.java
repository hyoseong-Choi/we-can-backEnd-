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


}