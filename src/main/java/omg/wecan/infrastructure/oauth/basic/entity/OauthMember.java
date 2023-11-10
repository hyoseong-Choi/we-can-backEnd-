package omg.wecan.infrastructure.oauth.basic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import omg.wecan.auth.Testuser.entity.ROLE;
import omg.wecan.auth.Testuser.entity.TestUser;
import omg.wecan.infrastructure.oauth.basic.domain.OauthId;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "oauth_member",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "oauth_id_unique",
                        columnNames = {
                                "oauth_server_id",
                                "oauth_server"
                        }
                ),
        }
)
public class OauthMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;
    private String nickname;
    private String profileImageUrl;

    public Long id() {
        return id;
    }

    public OauthId oauthId() {
        return oauthId;
    }

    public String nickname() {
        return nickname;
    }

    public String profileImageUrl() {
        return profileImageUrl;
    }

    public TestUser toUser(){
        return new TestUser(null, nickname, nickname, profileImageUrl, oauthId.getOauthServerId(),oauthId.getOauthServerType(), ROLE.USER);
    }
}
