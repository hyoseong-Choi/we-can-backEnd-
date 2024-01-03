package omg.wecan.recruit.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.time.LocalDateTime;

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
    private LocalDateTime endDate;
    private LocalDateTime challengeEndTime;
    private String type;
    private String coverImageEndpoit;
}
