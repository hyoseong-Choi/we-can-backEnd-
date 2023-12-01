package omg.wecan.user.repository;

import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(final String email);
    Optional<User> findByOauthServerIdAndSocial(final String oauthServerId, final OauthServerType social);
    Optional<User> findByEmailAndName(String email, String name);
}
