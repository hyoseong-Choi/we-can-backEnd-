package omg.wecan.chatting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.user.entity.User;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatting_room_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "challenge_id", referencedColumnName = "challenge_id")
    private Challenge challenge;

    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.REMOVE)
    private List<ChattingRoomUser> chattingRoomUsers;

    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.REMOVE)
    private List<Chatting> chattings;

    private long userCount;

    public static ChattingRoom create(Challenge challenge) {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.challenge = challenge;
        chattingRoom.userCount = 0;

        return chattingRoom;
    }

    public void upUserCount(){
        this.userCount++;
    }
    public void downUserCount(){
        this.userCount--;
    }


}
