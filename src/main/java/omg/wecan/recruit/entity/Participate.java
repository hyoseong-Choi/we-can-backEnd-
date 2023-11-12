package omg.wecan.recruit.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import omg.wecan.user.entity.User;

@Entity
@NoArgsConstructor
public class Participate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participate_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    private boolean leader;
    private boolean payment;
    
    public static Participate createLeaderParticipate(User user, Recruit recruit) {
        Participate participate = new Participate();
        participate.user = user;
        participate.recruit = recruit;
        participate.leader = true;
        return participate;
    }
    
    public static Participate createParticipate(User user, Recruit recruit) {
        Participate participate = new Participate();
        participate.user = user;
        participate.recruit = recruit;
        participate.leader = false;
        return participate;
    }
}
