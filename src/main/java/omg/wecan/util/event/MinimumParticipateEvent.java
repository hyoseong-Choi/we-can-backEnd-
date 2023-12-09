package omg.wecan.util.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import omg.wecan.recruit.entity.Participate;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MinimumParticipateEvent {
    private final List<Participate> participateList;
    private final String recruitTitle;
}
