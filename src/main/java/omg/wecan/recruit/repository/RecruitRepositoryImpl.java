package omg.wecan.recruit.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static omg.wecan.recruit.entity.QRecruit.*;
import static org.springframework.util.StringUtils.hasText;

public class RecruitRepositoryImpl implements RecruitRepositoryCustom{
    
    private final JPAQueryFactory queryFactory;
    
    public RecruitRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Recruit> findAllByCond(RecruitFindCond recruitFindCond, Pageable pageable) {
        List<Recruit> results = queryFactory
                .selectFrom(recruit)
                .where(titleContains(recruitFindCond.getTitle()),
                        categoryEq(recruitFindCond.getCategory()),
                        recruit.finished.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        
        queryFactory
                .select(recruit.count())
                .from(recruit)
                .where(titleContains(recruitFindCond.getTitle()),
                        categoryEq(recruitFindCond.getCategory()),
                        recruit.finished.eq(false))
                .fetchOne();
        return new PageImpl<>(results, pageable, results.size());
    }

    private BooleanExpression titleContains(String title) {
        if (hasText(title)) {
            return recruit.title.contains(title);
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
