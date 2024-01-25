package omg.wecan.infrastructure.oauth.basic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import omg.wecan.user.entity.ROLE;
import omg.wecan.infrastructure.oauth.basic.domain.OauthId;
import omg.wecan.user.entity.User;

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
    private String email;
    private String phone;
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

    public User toUser(){
        return new User(email, null,nickname, nickname, phone, profileImageUrl, oauthId.getOauthServerId(),oauthId.getOauthServerType(), ROLE.USER);
    }
}
