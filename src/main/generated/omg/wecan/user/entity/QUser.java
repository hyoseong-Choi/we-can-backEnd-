package omg.wecan.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 970418404L;

    public static final QUser user = new QUser("user");

    public final omg.wecan.global.entity.QBaseEntity _super = new omg.wecan.global.entity.QBaseEntity(this);

    public final NumberPath<Long> candy = createNumber("candy", Long.class);

    public final ListPath<omg.wecan.challenge.entity.ChallengeCheckImage, omg.wecan.challenge.entity.QChallengeCheckImage> challengeCheckImages = this.<omg.wecan.challenge.entity.ChallengeCheckImage, omg.wecan.challenge.entity.QChallengeCheckImage>createList("challengeCheckImages", omg.wecan.challenge.entity.ChallengeCheckImage.class, omg.wecan.challenge.entity.QChallengeCheckImage.class, PathInits.DIRECT2);

    public final ListPath<omg.wecan.challenge.entity.ChallengeCheck, omg.wecan.challenge.entity.QChallengeCheck> challengeChecks = this.<omg.wecan.challenge.entity.ChallengeCheck, omg.wecan.challenge.entity.QChallengeCheck>createList("challengeChecks", omg.wecan.challenge.entity.ChallengeCheck.class, omg.wecan.challenge.entity.QChallengeCheck.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<omg.wecan.challenge.entity.DislikeCheck, omg.wecan.challenge.entity.QDislikeCheck> dislikeChecks = this.<omg.wecan.challenge.entity.DislikeCheck, omg.wecan.challenge.entity.QDislikeCheck>createList("dislikeChecks", omg.wecan.challenge.entity.DislikeCheck.class, omg.wecan.challenge.entity.QDislikeCheck.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath imgEndPoint = createString("imgEndPoint");

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final StringPath oauthServerId = createString("oauthServerId");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<omg.wecan.review.entity.Review, omg.wecan.review.entity.QReview> reviews = this.<omg.wecan.review.entity.Review, omg.wecan.review.entity.QReview>createList("reviews", omg.wecan.review.entity.Review.class, omg.wecan.review.entity.QReview.class, PathInits.DIRECT2);

    public final EnumPath<ROLE> role = createEnum("role", ROLE.class);

    public final EnumPath<omg.wecan.infrastructure.oauth.basic.domain.OauthServerType> social = createEnum("social", omg.wecan.infrastructure.oauth.basic.domain.OauthServerType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<omg.wecan.challenge.entity.UserChallenge, omg.wecan.challenge.entity.QUserChallenge> userChallenges = this.<omg.wecan.challenge.entity.UserChallenge, omg.wecan.challenge.entity.QUserChallenge>createList("userChallenges", omg.wecan.challenge.entity.UserChallenge.class, omg.wecan.challenge.entity.QUserChallenge.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

