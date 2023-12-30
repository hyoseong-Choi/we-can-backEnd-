package omg.wecan.shop.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import omg.wecan.exception.shopException.NoUserItemException;
import omg.wecan.shop.dto.MyItemsOutput;
import omg.wecan.shop.entity.UserItem;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static omg.wecan.exception.customException.ErrorCode.USERITEM_NOT_FOUND;
import static omg.wecan.shop.entity.ItemType.EMOTICON;
import static omg.wecan.shop.entity.ItemType.ITEM;
import static omg.wecan.shop.entity.QItem.item;
import static omg.wecan.shop.entity.QUserItem.userItem;

public class UserItemRepositoryImpl implements UserItemRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    
    public UserItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<MyItemsOutput> findEmoticonByUser(User user, Pageable pageable) {
        List<UserItem> userItemList = queryFactory
                .selectFrom(userItem)
                .join(userItem.item, item).fetchJoin()
                .where(userItem.user.eq(user), item.itemType.eq(EMOTICON))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        List<MyItemsOutput> myItemsOutputList = userItemList.stream()
                .map(userItem -> new MyItemsOutput(userItem.getItem().getId(), userItem.getItem().getName(), userItem.getItem().getImgEndpoint()))
                .collect(Collectors.toList());
        Long count = queryFactory
                .select(userItem.count())
                .from(userItem)
                .join(userItem.item, item)
                .where(userItem.user.eq(user), item.itemType.eq(EMOTICON))
                .fetchOne();
        if (count == null) {
            throw new NoUserItemException(USERITEM_NOT_FOUND);
        }
        return new PageImpl<>(myItemsOutputList, pageable, count);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<MyItemsOutput> findItemByUser(User user, Pageable pageable){
        List<UserItem> userItemList = queryFactory
                .selectFrom(userItem)
                .join(userItem.item, item).fetchJoin()
                .where(userItem.user.eq(user), item.itemType.eq(ITEM))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        List<MyItemsOutput> myItemsOutputList = userItemList.stream()
                .map(userItem -> new MyItemsOutput(userItem.getItem().getId(), userItem.getItem().getName(), userItem.getItem().getImgEndpoint()))
                .collect(Collectors.toList());
        Long count = queryFactory
                .select(userItem.count())
                .from(userItem)
                .join(userItem.item, item)
                .where(userItem.user.eq(user), item.itemType.eq(ITEM))
                .fetchOne();
        if (count == null) {
            throw new NoUserItemException(USERITEM_NOT_FOUND);
        }
        return new PageImpl<>(myItemsOutputList, pageable, count);
    }
}
