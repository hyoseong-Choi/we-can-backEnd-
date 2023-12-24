package omg.wecan.challenge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChallengeCheckImage is a Querydsl query type for ChallengeCheckImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChallengeCheckImage extends EntityPathBase<ChallengeCheckImage> {

    private static final long serialVersionUID = -1372928857L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChallengeCheckImage challengeCheckImage = new QChallengeCheckImage("challengeCheckImage");

    public final QChallengeCheck challengeCheck;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath originName = createString("originName");

    public final StringPath storedName = createString("storedName");

    public final omg.wecan.user.entity.QUser user;

    public QChallengeCheckImage(String variable) {
        this(ChallengeCheckImage.class, forVariable(variable), INITS);
    }

    public QChallengeCheckImage(Path<? extends ChallengeCheckImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChallengeCheckImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChallengeCheckImage(PathMetadata metadata, PathInits inits) {
        this(ChallengeCheckImage.class, metadata, inits);
    }

    public QChallengeCheckImage(Class<? extends ChallengeCheckImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.challengeCheck = inits.isInitialized("challengeCheck") ? new QChallengeCheck(forProperty("challengeCheck"), inits.get("challengeCheck")) : null;
        this.user = inits.isInitialized("user") ? new omg.wecan.user.entity.QUser(forProperty("user")) : null;
    }

}

