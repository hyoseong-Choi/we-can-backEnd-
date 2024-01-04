package omg.wecan.recruit.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.recruit.dto.RecruitInput;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "elastic_recruit")
@Mapping(mappingPath = "elastic/recruit-mapping.json")
@Setting(settingPath = "elastic/recruit-setting.json")
public class ElasticRecruit {
    @Id
    private Long id;
    private String title;
    private String endDate;
    private String challengeEndTime;
    private String type;
    private String coverImageEndpoit;
    
    public ElasticRecruit(Long id, RecruitInput recruitInput, String imgEndPoint) {
        this.id = id;
        this.title = recruitInput.getTitle();
        this.endDate = recruitInput.getChallengeStartDate().minusDays(1).toString();
        this.challengeEndTime = recruitInput.getChallengeEndDate().toString();
        this.type = recruitInput.getChallengeType();
        this.coverImageEndpoit = imgEndPoint;
    }
    
    public ElasticRecruit(String title) {
        this.title = title;
    }
}