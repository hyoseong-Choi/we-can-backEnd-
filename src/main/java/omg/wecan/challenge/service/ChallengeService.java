package omg.wecan.challenge.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.Enum.ChallengeStateType;
import omg.wecan.challenge.dto.*;
import omg.wecan.challenge.entity.*;
import omg.wecan.challenge.repository.*;
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
    private final DislikeCheckRepository dislikeCheckRepository;

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
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND, "challengeId: "+challengeCheckInputDto.getChallengeId()));
        ChallengeCheck challengeCheck = new ChallengeCheck(user, challenge);
        ChallengeCheck newChallengeCheck = challengeCheckRepository.save(challengeCheck);


        List<String> imageUrlList = new ArrayList<>();
        for(MultipartFile multipartFile : challengeCheckInputDto.getImages()) {
            String value = saveImage(multipartFile, challengeCheck);
            imageUrlList.add(value);
        }

        ChallengeCheckImageDto challengeCheckImageDto = new ChallengeCheckImageDto(newChallengeCheck, imageUrlList, 0);


        return new ChallengeCheckResultDto(newChallengeCheck, challengeCheckImageDto);

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

    public ChallengeCheckResultDto dislikeChallengeCheck(User user, Long challengeCheckId) {
        ChallengeCheck dislikedChallengeCheck = challengeCheckRepository.findById(challengeCheckId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_CHECK_NOT_FOUND, "challengeId: "+challengeCheckId));

        //이미 싫어요 누른 유저 체크 추가 필요
        dislikedChallengeCheck.increaseDislike();
        dislikedChallengeCheck = challengeCheckRepository.save(dislikedChallengeCheck);

        DislikeCheck dislikeCheck = new DislikeCheck(user, dislikedChallengeCheck);
        dislikeCheckRepository.save(dislikeCheck);

        List<ChallengeCheckImage> images = challengeCheckImageRepository.findByChallengeCheck_IdAndUserUserId(challengeCheckId, dislikedChallengeCheck.getUser().getUserId());

        List<String> imageUrlList = images.stream()
                .map(ChallengeCheckImage::getImageUrl)
                .collect(Collectors.toList());

        ChallengeCheckImageDto challengeCheckImages = new ChallengeCheckImageDto(dislikedChallengeCheck, imageUrlList, dislikedChallengeCheck.getDislike());

        // dislike 수가 challenge의 peopleNum의 40%를 넘는지 확인
        int peopleNum = dislikedChallengeCheck.getChallenge().getPeopleNum();
        int dislikeThreshold = (int) Math.round(peopleNum * 0.4);

        if (dislikedChallengeCheck.getDislike() == dislikeThreshold) {
            UserChallenge userChallenge = userChallengeRepository.findByUserAndChallenge(dislikedChallengeCheck.getUser(), dislikedChallengeCheck.getChallenge());
            if (userChallenge != null) {
                userChallenge.increaseFailNum();
                userChallengeRepository.save(userChallenge);
            }
        }

        return new ChallengeCheckResultDto(dislikedChallengeCheck, challengeCheckImages);


    }

}
