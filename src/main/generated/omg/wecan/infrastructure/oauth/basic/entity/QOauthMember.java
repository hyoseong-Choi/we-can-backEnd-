package omg.wecan.infrastructure.oauth.basic.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOauthMember is a Querydsl query type for OauthMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOauthMember extends EntityPathBase<OauthMember> {

    private static final long serialVersionUID = 1381826423L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOauthMember oauthMember = new QOauthMember("oauthMember");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final omg.wecan.infrastructure.oauth.basic.domain.QOauthId oauthId;

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public QOauthMember(String variable) {
        this(OauthMember.class, forVariable(variable), INITS);
    }

    public QOauthMember(Path<? extends OauthMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOauthMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOauthMember(PathMetadata metadata, PathInits inits) {
        this(OauthMember.class, metadata, inits);
    }

    public QOauthMember(Class<? extends OauthMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.oauthId = inits.isInitialized("oauthId") ? new omg.wecan.infrastructure.oauth.basic.domain.QOauthId(forProperty("oauthId")) : null;
    }

}

