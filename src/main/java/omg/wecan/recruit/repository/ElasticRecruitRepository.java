package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.ElasticRecruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;

public interface ElasticRecruitRepository {
    Page<ElasticRecruit> findByTitleIn(Collection<String> title, Pageable pageable);
    
//    @Query("{\"match\": {\"title\": {\"query\": \"?0\", \"operator\": \"or\"}}}")
//    Page<ElasticRecruit> findByTitle(String title, Pageable pageable);
}