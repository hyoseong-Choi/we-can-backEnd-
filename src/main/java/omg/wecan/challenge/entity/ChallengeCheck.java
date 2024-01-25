package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChallengeCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int dislike;

    @Column(name = "checkDate", nullable = false, updatable = false)
    private LocalDateTime checkDate;


    @OneToMany(mappedBy = "challengeCheck", cascade = CascadeType.ALL)
    private List<ChallengeCheckImage> challengeCheckImages;

    @OneToMany(mappedBy = "challengeCheck", cascade = CascadeType.ALL)
    private List<DislikeCheck> dislikeChecks;

    @PrePersist
    protected void onCreate() {
        if (checkDate == null) {
            checkDate = LocalDateTime.now();
        }
    }
    public ChallengeCheck(User user, Challenge challenge) {
        this.user = user;
        this.challenge = challenge;
        this.checkDate = LocalDateTime.now();
        this.dislike = 0;
    }

    public void increaseDislike() {
        this.dislike++;
    }


}