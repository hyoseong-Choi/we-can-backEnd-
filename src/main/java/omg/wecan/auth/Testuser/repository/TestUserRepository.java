package omg.wecan.auth.Testuser.repository;

import omg.wecan.auth.Testuser.entity.TestUser;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestUserRepository extends JpaRepository<TestUser, Long> {
    Optional<TestUser> findByEmail(final String email);

    Optional<TestUser> findByOauthServerIdAndSocial(final String oauthServerId, final OauthServerType social);
}
