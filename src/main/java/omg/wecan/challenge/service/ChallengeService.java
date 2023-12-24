package omg.wecan.challenge.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.Enum.ChallengeStateType;
import omg.wecan.challenge.dto.ChallengeCheckInputDto;
import omg.wecan.challenge.dto.ChallengeCheckResultDto;
import omg.wecan.challenge.dto.ChallengeDto;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.entity.ChallengeCheck;
import omg.wecan.challenge.entity.ChallengeCheckImage;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.challenge.repository.ChallengeCheckImageRepository;
import omg.wecan.challenge.repository.ChallengeCheckRepository;
import omg.wecan.challenge.repository.ChallengeRepository;
import omg.wecan.challenge.repository.UserChallengeRepository;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private static String bucketName = "wecanbucket";

    private final AmazonS3Client amazonS3Client;
    private final ChallengeRepository challengeRepository;
    private final ChallengeCheckRepository challengeCheckRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final ChallengeCheckImageRepository challengeCheckImageRepository;

    // 유저의 참여 중인 챌린지 조회
    public List<ChallengeDto> getActiveChallengesByUser(Long userId) {
        List<UserChallenge> userChallenges = userChallengeRepository.findByUserUserId(userId);
        List<Challenge> activeChallenges = userChallenges.stream()
                .filter(userChallenge -> userChallenge.getChallenge().getState().equals(ChallengeStateType.Active))
                .map(UserChallenge::getChallenge)
                .collect(Collectors.toList());

        return activeChallenges.stream()
                .map(ChallengeDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ChallengeDto> getUpcomingChallengesByUser(Long userId) {
        List<UserChallenge> userChallenges = userChallengeRepository.findByUserUserId(userId);
        List<Challenge> upcomingChallenges = userChallenges.stream()
                .filter(userChallenge -> userChallenge.getChallenge().getState().equals(ChallengeStateType.Upcoming))
                .map(UserChallenge::getChallenge)
                .collect(Collectors.toList());

        return upcomingChallenges.stream()
                .map(ChallengeDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ChallengeDto> getCompletedChallengesByUser(Long userId) {
        List<UserChallenge> userChallenges = userChallengeRepository.findByUserUserId(userId);
        List<Challenge> completedChallenges = userChallenges.stream()
                .filter(userChallenge -> userChallenge.getChallenge().getState().equals(ChallengeStateType.Completed))
                .map(UserChallenge::getChallenge)
                .collect(Collectors.toList());

        return completedChallenges.stream()
                .map(ChallengeDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ChallengeCheckResultDto saveChallengeCheck(User user, ChallengeCheckInputDto challengeCheckInputDto) {
        Challenge challenge = challengeRepository.findById(challengeCheckInputDto.getChallengeId())
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND, "reviewId: "+challengeCheckInputDto.getChallengeId()));
        ChallengeCheck challengeCheck = new ChallengeCheck(user, challenge);
        ChallengeCheck newChallengeCheck = challengeCheckRepository.save(challengeCheck);


        List<String> imageUrlList = new ArrayList<>();
        for(MultipartFile multipartFile : challengeCheckInputDto.getImages()) {
            String value = saveImage(multipartFile, challengeCheck);
            imageUrlList.add(value);
        }

        return new ChallengeCheckResultDto(newChallengeCheck, imageUrlList);

    }


    @Transactional
    public String saveImage(MultipartFile multipartFile, ChallengeCheck challengeCheck) {
        String originalName = multipartFile.getOriginalFilename();
        ChallengeCheckImage challengeCheckImage = new ChallengeCheckImage(challengeCheck, originalName);
        String filename = challengeCheckImage.getStoredName();

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, filename, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();
            challengeCheckImage.setImageUrl(accessUrl);
        } catch(IOException e) {

        }

        challengeCheckImageRepository.save(challengeCheckImage);

        return challengeCheckImage.getImageUrl();
    }


//    public List<ChallengeDetailsDto> getChallengeInfo(Integer selectedMonth) {
//
//    }
}
