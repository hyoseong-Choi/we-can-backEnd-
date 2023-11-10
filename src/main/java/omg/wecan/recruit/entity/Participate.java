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
    Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    Recruit recruit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    
    boolean leader;
    boolean payment;
}
