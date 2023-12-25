package omg.wecan.challenge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDislikeCheck is a Querydsl query type for DislikeCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDislikeCheck extends EntityPathBase<DislikeCheck> {

    private static final long serialVersionUID = -1754216110L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDislikeCheck dislikeCheck = new QDislikeCheck("dislikeCheck");

    public final QChallengeCheck challengeCheck;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final omg.wecan.user.entity.QUser user;

    public QDislikeCheck(String variable) {
        this(DislikeCheck.class, forVariable(variable), INITS);
    }

    public QDislikeCheck(Path<? extends DislikeCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDislikeCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDislikeCheck(PathMetadata metadata, PathInits inits) {
        this(DislikeCheck.class, metadata, inits);
    }

    public QDislikeCheck(Class<? extends DislikeCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.challengeCheck = inits.isInitialized("challengeCheck") ? new QChallengeCheck(forProperty("challengeCheck"), inits.get("challengeCheck")) : null;
        this.user = inits.isInitialized("user") ? new omg.wecan.user.entity.QUser(forProperty("user")) : null;
    }

}

