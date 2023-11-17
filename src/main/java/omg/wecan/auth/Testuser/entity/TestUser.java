package omg.wecan.auth.Testuser.entity;

import jakarta.persistence.*;
import lombok.Getter;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;

@Entity
@Getter
public class TestUser {
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
    @Column(nullable = false, name = "oauth_server_id")
    private String oauthServerId;
    @Enumerated(EnumType.STRING)
    private OauthServerType social;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    String refreshToken;

    public TestUser() {

    }
    public TestUser(String email, String name, String nickName, String imgEndPoint, String oauthServerId, OauthServerType social, ROLE role){
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.imgEndPoint = imgEndPoint;
        this.oauthServerId = oauthServerId;
        this.social = social;
        this.role = role;
    }



    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
