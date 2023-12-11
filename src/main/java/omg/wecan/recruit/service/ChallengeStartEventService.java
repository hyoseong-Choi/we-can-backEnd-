package omg.wecan.recruit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.repository.ParticipateRepository;
import omg.wecan.recruit.repository.RecruitRepository;
import omg.wecan.util.event.ChallengeStartEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChallengeStartEventService {
    private final RecruitRepository recruitRepository;
    private final ParticipateRepository participateRepository;
    private final ApplicationEventPublisher eventPublisher;
    
    @Transactional
    @Scheduled(cron = "0 23 14 * * *")
    public void recruitToChallenge() {
        List<Recruit> recruitList = recruitRepository.findByEndDateIs(LocalDate.now());
        for (Recruit recruit : recruitList) {
            List<Participate> participatesByRecruit = participateRepository.findUserByRecruit(recruit);
            log.info("퍼블전 Thread Id : {}", Thread.currentThread().getId());
            eventPublisher.publishEvent(new ChallengeStartEvent(participatesByRecruit, recruit.getTitle()));
        }
    }
}
