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
    private Long id;
    
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String imgEndpoint;
    private int candy;
    
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}