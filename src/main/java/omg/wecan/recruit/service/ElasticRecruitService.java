package omg.wecan.recruit.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.dto.RecruitInput;
import omg.wecan.recruit.dto.RecruitOutput;
import omg.wecan.recruit.entity.ElasticRecruit;
import omg.wecan.recruit.repository.ElasticRecruitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticRecruitService {
    private final ElasticRecruitRepository elasticRecruitRepository;
    
    @Async
    public void addRecruit(Long id, RecruitInput recruitInput, String imgEndPoint) {
        elasticRecruitRepository.save(new ElasticRecruit(id, recruitInput, imgEndPoint));
    }
    
    public Page<RecruitOutput> findRecruit(RecruitFindCond recruitFindCond, Pageable pageable) {
        return elasticRecruitRepository.findByTitle(recruitFindCond.getTitle(), pageable).map(elasticRecruit -> new RecruitOutput(elasticRecruit, false));
        
    }
    
    public Page<RecruitOutput> findRecruit2(RecruitFindCond recruitFindCond, Pageable pageable) {
        return elasticRecruitRepository.searchSimilar(new ElasticRecruit(recruitFindCond.getTitle()), new String[]{"title"}, pageable)
                .map(elasticRecruit -> new RecruitOutput(elasticRecruit, false));
        
    }
}