package omg.wecan.recruit.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.challenge.repository.ChallengeRepository;
import omg.wecan.challenge.repository.UserChallengeRepository;
import omg.wecan.chatting.dto.ChatRoom;
import omg.wecan.chatting.service.ChatService;
import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.repository.ParticipateRepository;
import omg.wecan.recruit.repository.RecruitRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RecruitToChallengeService {
    private final RecruitRepository recruitRepository;
    private final ParticipateRepository participateRepository;
    private final ChallengeRepository challengeRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final ChatService chatService;
    
    //끝난 모집글 가져와서 피시니 해주고 참여한 애들 챌린지 만들어주고 userchallenge로 보내주고
    @Transactional
    @Scheduled(cron = "1 0 0 * * *")
    public void recruitToChallenge() {
        List<Recruit> finishedRecruits = recruitRepository.findByEndDateIs(LocalDate.now().minusDays(1));

        for (Recruit recruit : finishedRecruits) {
            recruit.changeFinished();
            List<Participate> participatesByRecruit = participateRepository.findByRecruit(recruit);
            Challenge newChallenge = challengeRepository.save(Challenge.createChallenge(recruit, participatesByRecruit.size()));
            ChatRoom chattingRoom = chatService.createChatRoom(newChallenge.getId());
            newChallenge.setChattingRoomId(chattingRoom.getRoomId());
            challengeRepository.save(newChallenge);

            for (Participate participate : participatesByRecruit) {
                userChallengeRepository.save(UserChallenge.createUserChallenge(participate, newChallenge));
            }
        }
    }
}
