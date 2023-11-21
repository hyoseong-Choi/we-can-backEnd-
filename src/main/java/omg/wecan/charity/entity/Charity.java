package omg.wecan.charity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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
    
    @Column(name = "page_endpoint")
    private String pageEndpoint;

    public Charity() {
    
    }

    public void change(String name, CharityCategory category, String explanation, String imgEndpoint, String pageEndpoint){
        this.name = name;
        this.category = category;
        this.explanation = explanation;
        this.imgEndpoint = imgEndpoint;
        this.pageEndpoint = pageEndpoint;
    }
}