package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import omg.wecan.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
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

    @Column(name = "checkDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime checkDate;

    @OneToMany(mappedBy = "challengeCheck", cascade = CascadeType.ALL)
    private List<ChallengeCheckImage> challengeCheckImages;

    @PrePersist
    protected void onCreate() {
        checkDate = LocalDateTime.now();
    }

    public ChallengeCheck(User user, Challenge challenge) {
        this.user = user;
        this.challenge = challenge;
        this.dislike = 0;
    }


}