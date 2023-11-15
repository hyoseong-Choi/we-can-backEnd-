package omg.wecan.recruit.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static omg.wecan.recruit.entity.QRecruit.*;
import static org.springframework.util.StringUtils.hasText;

public class RecruitRepositoryImpl implements RecruitRepositoryCustom{
    
    private final JPAQueryFactory queryFactory;
    
    public RecruitRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    
    @Override
    public Page<Recruit> findAllByCond(RecruitFindCond recruitFindCond, Pageable pageable) {
        List<Recruit> results = queryFactory
                .selectFrom(recruit)
                .where(titleEq(recruitFindCond.getTitle()),
                        categoryEq(recruitFindCond.getCategory()),
                        recruit.finished.eq(false))
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }

    @Override
    public List<Recruit> findAllByCond(RecruitFindCond recruitFindCond) {
        return queryFactory
                .selectFrom(recruit)
                .where(titleEq(recruitFindCond.getTitle()),
                        categoryEq(recruitFindCond.getCategory()),
                        recruit.finished.eq(false))
                .fetch();
    }

    private BooleanExpression titleEq(String title) {
        if (hasText(title)) {
            return recruit.title.eq(title);
        }
        return null;
    }

    private BooleanExpression categoryEq(ChallengeType category) {
        if (category != null) {
            return recruit.type.eq(category);
        }
        return null;
    }
}
