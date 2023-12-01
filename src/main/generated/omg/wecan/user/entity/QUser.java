package omg.wecan.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 970418404L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Long> candy = createNumber("candy", Long.class);

    public final StringPath email = createString("email");

    public final StringPath imgEndPoint = createString("imgEndPoint");

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final StringPath oauthServerId = createString("oauthServerId");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<ROLE> role = createEnum("role", ROLE.class);

    public final EnumPath<omg.wecan.infrastructure.oauth.basic.domain.OauthServerType> social = createEnum("social", omg.wecan.infrastructure.oauth.basic.domain.OauthServerType.class);

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

