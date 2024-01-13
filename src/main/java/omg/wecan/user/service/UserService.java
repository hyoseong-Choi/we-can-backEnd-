package omg.wecan.user.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.user.dto.request.UpdateUserRequest;
import omg.wecan.user.dto.response.UserProfileResponse;
import omg.wecan.user.entity.User;
import omg.wecan.user.exception.MismatchedPasswordUser;
import omg.wecan.user.exception.NoSuchEmailUser;
import omg.wecan.user.repository.UserRepository;
import omg.wecan.util.FileStore;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final FileStore fileStore;
    private final EntityManager em;
    private final ModelMapper modelMapper;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> {
                    throw new IllegalArgumentException();
                }
        );
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "user Id: " + id));
    }

    public Optional<User> findByOauthServerIdAndSocial(final String oauthServerId, final String social) {
        return userRepository.findByOauthServerIdAndSocial(oauthServerId, OauthServerType.fromName(social));
    }

    public User save(User user) {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());

        if(!findUser.isEmpty())
            throw new CustomException(ErrorCode.USER_EMAIL_DUPLICATED);

        return userRepository.saveAndFlush(user);
    }

    public User updateRefreshToken(Long userId, String refreshToken) {
        User findUser = em.find(User.class, userId);
        findUser.setRefreshToken(refreshToken);
        return findUser;
    }

    public User login(String email, String password) {
        boolean success = true;

        User findUser = userRepository.findByEmail(email).orElseGet(() -> {
            throw new CustomException(ErrorCode.LOGIN_INFO_INVALID);
        });

        if (!findUser.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.LOGIN_INFO_INVALID);
        }

        return findUser;
    }

    public void deleteUser(User user) {
        // 유저의 챌린지, 작성글, 후기 삭제 추가 필요

        userRepository.delete(user);
    }

    public void addCandy(Long userId, long candyCnt){
        User user = getById(userId);
        long currentCandy = user.getCandy();
        user.setCandy(currentCandy + candyCnt);
    }

    public UserProfileResponse updateUserProfile(Long userId, UpdateUserRequest request){
        User user = userRepository.findById(userId).get();

        String currentImgUrl = user.getImgEndPoint();
        String newImgUrl = null;

        if(currentImgUrl != null)
            fileStore.deleteFile(currentImgUrl);

        if(request.getProfileImage() != null) {
            newImgUrl = fileStore.storeFile(request.getProfileImage());
        }

        user.setNickName(request.getNickName());
        user.setImgEndPoint(newImgUrl);

        return modelMapper.map(user, UserProfileResponse.class);
    }

    public UserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId).get();

        return modelMapper.map(user, UserProfileResponse.class);
    }
}
