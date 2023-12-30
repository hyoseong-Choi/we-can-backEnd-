package omg.wecan.user.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.user.entity.User;
import omg.wecan.user.exception.MismatchedPasswordUser;
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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> {
                    throw new IllegalArgumentException();
                }
        );
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "user Id: " + id));
    }

    public Optional<User> findByOauthServerIdAndSocial(final String oauthServerId, final String social) {
        return userRepository.findByOauthServerIdAndSocial(oauthServerId, OauthServerType.fromName(social));
    }

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User updateRefreshToken(Long userId, String refreshToken) {
        User findUser = em.find(User.class, userId);
        findUser.setRefreshToken(refreshToken);
        return findUser;
    }

    public User login(String email, String password) {
        User findUser = userRepository.findByEmail(email).orElseGet(() -> {
            throw new NoSuchEmailUser();
        });

        if (!findUser.getPassword().equals(password)) {
            throw new MismatchedPasswordUser();
        }

        return findUser;
    }

    public void deleteUser(User user) {
        // 유저의 챌린지, 작성글, 후기 삭제 추가 필요

        userRepository.delete(user);
    }

    public void addCandy(Long userId, long candyCnt){
        User user = findById(userId);
        long currentCandy = user.getCandy();
        user.setCandy(currentCandy + candyCnt);
    }
}
