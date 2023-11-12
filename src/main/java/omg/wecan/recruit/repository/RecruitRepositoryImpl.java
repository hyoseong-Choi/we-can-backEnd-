package omg.wecan.recruit.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import omg.wecan.recruit.dto.QRecruitOutput;
import omg.wecan.recruit.dto.RecruitOutput;

import java.util.List;

import static omg.wecan.recruit.entity.QHeart.heart;
import static omg.wecan.recruit.entity.QRecruit.*;

public class RecruitRepositoryImpl implements RecruitRepositoryCustom{
    
    private final JPAQueryFactory queryFactory;
    
    public RecruitRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    
}
