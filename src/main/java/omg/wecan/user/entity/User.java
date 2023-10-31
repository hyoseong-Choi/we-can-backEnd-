package omg.wecan.user.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;
    
    String email;
    String password;
    String name;
    String nickname;
    String imgEndpoint;
    int candy;
    
}