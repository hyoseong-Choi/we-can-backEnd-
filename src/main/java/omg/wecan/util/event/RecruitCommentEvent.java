package omg.wecan.util.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import omg.wecan.user.entity.User;

@Getter
@RequiredArgsConstructor
public class RecruitCommentEvent {
    private final User user;
    private final String content;
}
