package omg.wecan.recruit.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruitComment is a Querydsl query type for RecruitComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitComment extends EntityPathBase<RecruitComment> {

    private static final long serialVersionUID = 620672075L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruitComment recruitComment = new QRecruitComment("recruitComment");

    public final omg.wecan.global.entity.QBaseEntity _super = new omg.wecan.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> depth = createNumber("depth", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentCommentId = createNumber("parentCommentId", Long.class);

    public final QRecruit recruit;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final omg.wecan.user.entity.QUser user;

    public QRecruitComment(String variable) {
        this(RecruitComment.class, forVariable(variable), INITS);
    }

    public QRecruitComment(Path<? extends RecruitComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruitComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruitComment(PathMetadata metadata, PathInits inits) {
        this(RecruitComment.class, metadata, inits);
    }

    public QRecruitComment(Class<? extends RecruitComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recruit = inits.isInitialized("recruit") ? new QRecruit(forProperty("recruit"), inits.get("recruit")) : null;
        this.user = inits.isInitialized("user") ? new omg.wecan.user.entity.QUser(forProperty("user")) : null;
    }

}

