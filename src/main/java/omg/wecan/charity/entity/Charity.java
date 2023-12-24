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
        if(name != null)
            this.name = name;
        if(category != null)
            this.category = category;
        if(explanation != null)
            this.explanation = explanation;
        if(imgEndpoint != null)
            this.imgEndpoint = imgEndpoint;
        if(pageEndpoint != null)
            this.pageEndpoint = pageEndpoint;
    }
    
    public Charity(String charityName) {
        this.name = charityName;
    }
}