package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "chattingRoom", fetch = FetchType.LAZY)
    private Challenge challenge;

    public ChattingRoom(Challenge challenge) {
        this.challenge = challenge;
    }
}