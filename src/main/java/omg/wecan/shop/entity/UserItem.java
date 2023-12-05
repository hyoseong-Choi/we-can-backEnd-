package omg.wecan.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.user.entity.User;

@Entity
@NoArgsConstructor
@Getter
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
}
