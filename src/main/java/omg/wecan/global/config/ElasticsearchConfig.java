package omg.wecan.global.config;

import omg.wecan.recruit.repository.ElasticRecruitRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackageClasses = ElasticRecruitRepository.class)
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("3.37.61.48:9200")
                .withConnectTimeout(30000)
                .withSocketTimeout(30000)
                .build();
    }
}
