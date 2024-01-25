package omg.wecan.challenge.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.Enum.ChallengeStateType;
import omg.wecan.challenge.dto.*;
import omg.wecan.challenge.dto.input.ChallengeCheckExemptionDto;
import omg.wecan.challenge.dto.input.ChallengeCheckDto;
import omg.wecan.challenge.dto.input.CheckDislikeExemptionDto;
import omg.wecan.challenge.dto.output.*;
import omg.wecan.challenge.entity.*;
import omg.wecan.challenge.repository.*;
import omg.wecan.chatting.entity.ChattingRoom;
import omg.wecan.chatting.repository.ChattingRoomRepository;
import omg.wecan.chatting.service.ChatService;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.shop.entity.Exemption;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.repository.ExemptionRepository;
import omg.wecan.shop.repository.ItemRepository;
import omg.wecan.shop.repository.UserItemRepository;
import omg.wecan.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    private final ChattingRoomRepository chattingRoomRepository;
    private final ExemptionRepository exemptionRepository;
    private final UserItemRepository userItemRepository;
    private final ItemRepository itemRepository;
    private final ChatService chatService;

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

    public UserChallengeDto getUserChallengeByUserAndChallengeId(User user, Long challengeId){
        UserChallenge userChallenge = userChallengeRepository.findByChallengeIdAndUser(challengeId, user)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND,
                                "challengeId: " + challengeId + " " + "userId: " + user.getUserId())
        );

        UserChallengeDto userChallengeDto = userChallenge.toDto();
        return userChallengeDto;
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

    public ChallengeCheckResultDto saveChallengeCheck(User user, ChallengeCheckDto challengeCheckInputDto) {
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


        return new ChallengeCheckResultDto(newChallengeCheck.getId(), challengeCheckImageDto);

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

        DislikeCheck existingDislikeCheck = dislikeCheckRepository.findByUserAndChallengeCheckId(user, challengeCheckId);
        if (existingDislikeCheck != null) {
            throw new CustomException(ErrorCode.ALREADY_DISLIKED, "User has already disliked this challengeCheck");
        }

        dislikedChallengeCheck.increaseDislike();
        dislikedChallengeCheck = challengeCheckRepository.save(dislikedChallengeCheck);

        DislikeCheck dislikeCheck = new DislikeCheck(user, dislikedChallengeCheck);
        dislikeCheckRepository.save(dislikeCheck);

        List<String> imageUrlList = getChallengeCheckImages(challengeCheckId, dislikedChallengeCheck.getUser());

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

        return new ChallengeCheckResultDto(challengeCheckId, challengeCheckImages);


    }

    public ChallengeCheckRoomDto getChallengeCheckRoomInfo(Long challengeId, LocalDate checkDate) {

        List<ChallengeCheck> challengeChecks = challengeCheckRepository.findByChallengeId(challengeId)
                .stream()
                .filter(challengeCheck -> challengeCheck.getCheckDate().toLocalDate().isEqual(checkDate))
                .collect(Collectors.toList());

        if (challengeChecks.isEmpty()) {
            return new ChallengeCheckRoomDto(challengeId, null);
        }

        List<ChallengeCheckImageDto> challengeCheckInfoByUser = challengeChecks.stream()
                .map(challengeCheck -> {
                    List<String > challengeCheckImages = getChallengeCheckImages(challengeCheck.getId(), challengeCheck.getUser());
                    return new ChallengeCheckImageDto(challengeCheck, challengeCheckImages,challengeCheck.getDislike());
                })
                .collect(Collectors.toList());

        return new ChallengeCheckRoomDto(challengeId, challengeCheckInfoByUser);
    }

    private List<String> getChallengeCheckImages(Long challengeCheckId, User user) {
        List<ChallengeCheckImage> imageUrlList = challengeCheckImageRepository.findByChallengeCheck_IdAndUser(challengeCheckId, user);

        return imageUrlList.stream()
                .map(ChallengeCheckImage::getImageUrl)
                .collect(Collectors.toList());

    }

    public ChallengeInfoDto getChallengeInfo(User user, Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND, "challengeId: "+challengeId));

        UserChallenge userChallenge = userChallengeRepository.findByChallengeIdAndUser(challengeId, user)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        int failNum = userChallenge.getFailNum();
        int totalCheckNum = challenge.getTotalCheckNum();
        int successRate = 0;

        if (totalCheckNum > 0) {
            successRate = (int) (((double) (totalCheckNum - failNum) / totalCheckNum) * 100);
        }

        Optional<ChattingRoom> optionalChattingRoom = chattingRoomRepository.findFirstByChallengeId(challengeId);
        Long chattingRoomId = optionalChattingRoom.map(ChattingRoom::getId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHATTING_ROOM_NOT_FOUND, "challengeId: " + challengeId));

        return new ChallengeInfoDto(challenge, successRate, chattingRoomId, chatService.getChatList(chattingRoomId));
    }

    public ChallengeCheckResultDto challengeCheckExemption(User user, ChallengeCheckExemptionDto challengeCheckExemptionDto) {

        Exemption exemptionToDelete = exemptionRepository.findByCertificationString(challengeCheckExemptionDto.getExemptionString())
                .orElseThrow(() -> new CustomException(ErrorCode.USERITEM_NOT_FOUND));
        exemptionRepository.delete(exemptionToDelete);
        userItemRepository.delete(exemptionToDelete.getUserItem());

        Challenge checkChallenge = challengeRepository.findById(challengeCheckExemptionDto.getChallengeId())
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND));

        ChallengeCheck challengeCheck = new ChallengeCheck(user, checkChallenge);

        challengeCheckRepository.save(challengeCheck);

        ChallengeCheckImage challengeCheckImage = new ChallengeCheckImage();
        challengeCheckImageRepository.save(challengeCheckImage.imageSave(challengeCheck, exemptionToDelete.getUserItem().getItem().getImgEndpoint()));

        ChallengeCheckImageDto challengeCheckImageDto = new ChallengeCheckImageDto(challengeCheck, Arrays.asList(challengeCheckImage.getImageUrl()), 0);

        return new ChallengeCheckResultDto(challengeCheck.getId(), challengeCheckImageDto);
    }


    public ChallengeCheckResultDto dislikeExemption(User user, CheckDislikeExemptionDto checkDislikeExemptionDto) {
        Item useItem = itemRepository.findItemAndReduceDislikeByItemId(checkDislikeExemptionDto.getItemId());

        LocalDate currentDate = LocalDate.now();
        UserChallenge userChallenge = userChallengeRepository.findByUserAndChallengeId(user, checkDislikeExemptionDto.getChallengeId());

        ChallengeCheck exemptionChallengeCheck = challengeCheckRepository.findByChallengeIdAndUser(checkDislikeExemptionDto.getChallengeId(), user)
                .filter(challengeCheck -> challengeCheck.getCheckDate().toLocalDate().isEqual(currentDate))
                .orElseThrow(() -> new CustomException(ErrorCode.USERITEM_NOT_FOUND));

        int beforeDislikeNum = exemptionChallengeCheck.getDislike();
        int peopleNum = exemptionChallengeCheck.getChallenge().getPeopleNum();
        int dislikeThreshold = (int) Math.round(peopleNum * 0.4);

        exemptionChallengeCheck.setDislike(exemptionChallengeCheck.getDislike() - useItem.getReduceDislike());
        challengeCheckRepository.save(exemptionChallengeCheck);

        if (beforeDislikeNum >= dislikeThreshold && exemptionChallengeCheck.getDislike() < dislikeThreshold) {
            if (userChallenge.getFailNum() != 0) {
                userChallenge.decreaseFailNum();
                userChallengeRepository.save(userChallenge);
            }
        }

        ChallengeCheckImage challengeCheckImage = challengeCheckImageRepository.save(new ChallengeCheckImage().imageSave(exemptionChallengeCheck, useItem.getImgEndpoint()));
        userItemRepository.deleteById(checkDislikeExemptionDto.getItemId());

        ChallengeCheckImageDto challengeCheckImageDto = new ChallengeCheckImageDto(exemptionChallengeCheck, Arrays.asList(challengeCheckImage.getImageUrl()), exemptionChallengeCheck.getDislike());

        return new ChallengeCheckResultDto(checkDislikeExemptionDto.getChallengeId(), challengeCheckImageDto);
    }

}
