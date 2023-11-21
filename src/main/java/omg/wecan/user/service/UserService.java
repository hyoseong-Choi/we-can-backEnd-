package omg.wecan.user.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.user.entity.User;
import omg.wecan.user.exception.NoSuchEmailUser;
import omg.wecan.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager em;

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                ()-> {
                    throw new IllegalArgumentException();
                }
        );
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException();
        });
    }

    public Optional<User> findByOauthServerIdAndSocial(final String oauthServerId, final String social){
        return userRepository.findByOauthServerIdAndSocial(oauthServerId, OauthServerType.fromName(social));
    }

    public User save(User user){
        return userRepository.saveAndFlush(user);
    }

    public User updateRefreshToken(Long userId, String refreshToken){
        User findUser = em.find(User.class, userId);
        findUser.setRefreshToken(refreshToken);
        return findUser;
    }

    public User login(String email, String password) {
        User findUser = userRepository.findByEmail(email).orElseGet(()->{
            throw new NoSuchEmailUser();
        });

        if(!findUser.getPassword().equals(password)){
            throw new NoSuchEmailUser();
        }

        return findUser;
    }
}
