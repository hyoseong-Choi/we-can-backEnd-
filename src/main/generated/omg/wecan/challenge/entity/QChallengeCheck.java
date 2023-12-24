package omg.wecan.challenge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChallengeCheck is a Querydsl query type for ChallengeCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChallengeCheck extends EntityPathBase<ChallengeCheck> {

    private static final long serialVersionUID = -1760132844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChallengeCheck challengeCheck = new QChallengeCheck("challengeCheck");

    public final QChallenge challenge;

    public final ListPath<ChallengeCheckImage, QChallengeCheckImage> challengeCheckImages = this.<ChallengeCheckImage, QChallengeCheckImage>createList("challengeCheckImages", ChallengeCheckImage.class, QChallengeCheckImage.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> checkDate = createDateTime("checkDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> dislike = createNumber("dislike", Integer.class);

    public final ListPath<DislikeCheck, QDislikeCheck> dislikeChecks = this.<DislikeCheck, QDislikeCheck>createList("dislikeChecks", DislikeCheck.class, QDislikeCheck.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final omg.wecan.user.entity.QUser user;

    public QChallengeCheck(String variable) {
        this(ChallengeCheck.class, forVariable(variable), INITS);
    }

    public QChallengeCheck(Path<? extends ChallengeCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChallengeCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChallengeCheck(PathMetadata metadata, PathInits inits) {
        this(ChallengeCheck.class, metadata, inits);
    }

    public QChallengeCheck(Class<? extends ChallengeCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.challenge = inits.isInitialized("challenge") ? new QChallenge(forProperty("challenge"), inits.get("challenge")) : null;
        this.user = inits.isInitialized("user") ? new omg.wecan.user.entity.QUser(forProperty("user")) : null;
    }

}

