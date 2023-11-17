package omg.wecan.auth.Testuser.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.Testuser.entity.TestUser;
import omg.wecan.auth.Testuser.repository.TestUserRepository;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestUserService {
    private final TestUserRepository testUserRepository;
    private final EntityManager em;

    public TestUser findByEmail(String email){
        return testUserRepository.findByEmail(email).orElseThrow(
                ()-> {
                    throw new IllegalArgumentException();
                }
        );
    }

    public TestUser findById(Long id){
        return testUserRepository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException();
        });
    }

    public Optional<TestUser> findByOauthServerIdAndSocial(final String oauthServerId, final String social){
        return testUserRepository.findByOauthServerIdAndSocial(oauthServerId, OauthServerType.fromName(social));
    }

    public TestUser save(TestUser testUser){
        return testUserRepository.saveAndFlush(testUser);
    }

    public TestUser updateRefreshToken(Long userId, String refreshToken){
        TestUser testUser = em.find(TestUser.class, userId);
        testUser.setRefreshToken(refreshToken);
        return testUser;
    }
}
