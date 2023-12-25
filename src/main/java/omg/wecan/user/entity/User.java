package omg.wecan.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import omg.wecan.challenge.entity.ChallengeCheck;
import omg.wecan.challenge.entity.ChallengeCheckImage;
import omg.wecan.challenge.entity.DislikeCheck;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.global.entity.BaseEntity;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.review.entity.Review;

import java.util.List;
import java.util.regex.Pattern;

@Entity
@Getter
public class User extends BaseEntity {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String phone;
    private String imgEndPoint;
    private Long candy;
    @Column(name = "oauth_server_id")
    private String oauthServerId;
    @Enumerated(EnumType.STRING)
    private OauthServerType social;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    String refreshToken;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeCheck> challengeChecks;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserChallenge> userChallenges;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DislikeCheck> dislikeChecks;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChallengeCheckImage> challengeCheckImages;

    public User() {

    }
    public User(String email, String password, String name, String nickName, String phone, String imgEndPoint, String oauthServerId, OauthServerType social, ROLE role){
        this.candy = 0L;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.imgEndPoint = imgEndPoint;
        this.oauthServerId = oauthServerId;
        this.social = social;
        this.role = role;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
