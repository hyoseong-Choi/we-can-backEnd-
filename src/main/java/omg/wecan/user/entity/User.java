package omg.wecan.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
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
    
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}