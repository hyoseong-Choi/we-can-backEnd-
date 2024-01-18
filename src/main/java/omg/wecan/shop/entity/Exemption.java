package omg.wecan.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Exemption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private UserItem userItem;

    private String certificationString;

    public Exemption(UserItem userItem, String certificationString) {
        this.userItem = userItem;
        this.certificationString = certificationString;
    }
}
