package omg.wecan.charity.entity;

import jakarta.persistence.*;
import omg.wecan.global.entity.BaseEntity;

@Table(name = "charitys")
@Entity
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
}
