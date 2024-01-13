package omg.wecan.recruit.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        //List<Recruit> results =
        JPAQuery<Recruit> query = queryFactory
                .selectFrom(recruit)
                .where(titleContains(recruitFindCond.getTitle()),
                        categoryEq(recruitFindCond.getCategory()),
                        recruit.finished.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(recruit.getType(),
                    recruit.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(order.getProperty())));
        }
        List<Recruit> results = query.orderBy().fetch();
        Long l = queryFactory
                .select(recruit.count())
                .from(recruit)
                .where(titleContains(recruitFindCond.getTitle()),
                        categoryEq(recruitFindCond.getCategory()),
                        recruit.finished.eq(false))
                .fetchOne();
        return new PageImpl<>(results, pageable, l);
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
