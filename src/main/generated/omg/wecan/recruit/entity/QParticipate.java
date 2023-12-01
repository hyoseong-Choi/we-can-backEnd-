package omg.wecan.recruit.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParticipate is a Querydsl query type for Participate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParticipate extends EntityPathBase<Participate> {

    private static final long serialVersionUID = -60250860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParticipate participate = new QParticipate("participate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath leader = createBoolean("leader");

    public final BooleanPath payment = createBoolean("payment");

    public final QRecruit recruit;

    public final omg.wecan.user.entity.QUser user;

    public QParticipate(String variable) {
        this(Participate.class, forVariable(variable), INITS);
    }

    public QParticipate(Path<? extends Participate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParticipate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParticipate(PathMetadata metadata, PathInits inits) {
        this(Participate.class, metadata, inits);
    }

    public QParticipate(Class<? extends Participate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recruit = inits.isInitialized("recruit") ? new QRecruit(forProperty("recruit"), inits.get("recruit")) : null;
        this.user = inits.isInitialized("user") ? new omg.wecan.user.entity.QUser(forProperty("user")) : null;
    }

}

