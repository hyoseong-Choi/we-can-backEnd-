package omg.wecan.challenge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserChallenge is a Querydsl query type for UserChallenge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserChallenge extends EntityPathBase<UserChallenge> {

    private static final long serialVersionUID = 1213034569L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserChallenge userChallenge = new QUserChallenge("userChallenge");

    public final QChallenge challenge;

    public final NumberPath<Integer> failNum = createNumber("failNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath leader = createBoolean("leader");

    public final omg.wecan.user.entity.QUser user;

    public QUserChallenge(String variable) {
        this(UserChallenge.class, forVariable(variable), INITS);
    }

    public QUserChallenge(Path<? extends UserChallenge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserChallenge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserChallenge(PathMetadata metadata, PathInits inits) {
        this(UserChallenge.class, metadata, inits);
    }

    public QUserChallenge(Class<? extends UserChallenge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.challenge = inits.isInitialized("challenge") ? new QChallenge(forProperty("challenge"), inits.get("challenge")) : null;
        this.user = inits.isInitialized("user") ? new omg.wecan.user.entity.QUser(forProperty("user")) : null;
    }

}

