package omg.wecan.recruit.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static omg.wecan.recruit.entity.QHeart.heart;
import static omg.wecan.recruit.entity.QRecruit.*;

public class RecruitRepositoryImpl implements RecruitRepositoryCustom{
    
    private final JPAQueryFactory queryFactory;
    
    public RecruitRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    
    @Override
    public Page<Recruit> findAllByCond(RecruitFindCond recruitFindCond, Pageable pageable) {
        QueryResults<Recruit> results = queryFactory
                .selectFrom(recruit)
                .where(tltleEq(recruitFindCond.getTitle()),
                        categoryEq(recruitFindCond.getCategory()))
                .fetch();
    }
    
    private BooleanExpression categoryEq(ChallengeType category) {
        return null;
    }
    
    private BooleanExpression tltleEq(String title) {
    
    }
}
