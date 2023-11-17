package omg.wecan.infrastructure.oauth.basic.repository;

import omg.wecan.infrastructure.oauth.basic.domain.OauthId;
import omg.wecan.infrastructure.oauth.basic.entity.OauthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long>{
    Optional<OauthMember> findByOauthId(OauthId oauthId);
}
