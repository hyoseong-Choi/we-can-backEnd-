package omg.wecan.oauth.repository;

import omg.wecan.oauth.domain.OauthId;
import omg.wecan.oauth.entity.OauthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long>{
    Optional<OauthMember> findByOauthId(OauthId oauthId);
}
