package omg.wecan.chatting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.recruit.entity.Participate;
import omg.wecan.user.entity.User;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class ChattingRoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatting_room_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_room_id", referencedColumnName = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "chattingRoomUser", cascade = CascadeType.REMOVE)
    private List<Chatting> chattings;

    public static ChattingRoomUser create(User user, ChattingRoom chattingRoom) {
        ChattingRoomUser chattingRoomUser = new ChattingRoomUser();
        chattingRoomUser.user = user;
        chattingRoomUser.chattingRoom = chattingRoom;
        return chattingRoomUser;
    }

    public static ChattingRoomUser autoCreate(Participate participate, ChattingRoom chattingRoom) {
        ChattingRoomUser chattingRoomUser = new ChattingRoomUser();
        chattingRoomUser.user = participate.getUser();
        chattingRoomUser.chattingRoom = chattingRoom;
        return chattingRoomUser;
    }

}
