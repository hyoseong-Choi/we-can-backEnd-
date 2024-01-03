package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.ElasticRecruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRecruitRepository extends ElasticsearchRepository<ElasticRecruit, Long> {
    Page<ElasticRecruit> findByTitle(String title, Pageable pageable);
}
