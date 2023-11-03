package omg.wecan.charity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import omg.wecan.global.entity.BaseEntity;

@Table(name = "charitys")
@Entity
@Builder
@Getter
@AllArgsConstructor
public class Charity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private CharityCategory category;

    @Column
    private String explanation;

    @Column(name = "img_endpoint")
    private String imgEndpoint;

    public Charity() {

    }
}
