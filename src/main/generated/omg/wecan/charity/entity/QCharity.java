package omg.wecan.charity.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCharity is a Querydsl query type for Charity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCharity extends EntityPathBase<Charity> {

    private static final long serialVersionUID = 866093108L;

    public static final QCharity charity = new QCharity("charity");

    public final omg.wecan.global.entity.QBaseEntity _super = new omg.wecan.global.entity.QBaseEntity(this);

    public final EnumPath<CharityCategory> category = createEnum("category", CharityCategory.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgEndpoint = createString("imgEndpoint");

    public final StringPath name = createString("name");

    public final StringPath pageEndpoint = createString("pageEndpoint");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCharity(String variable) {
        super(Charity.class, forVariable(variable));
    }

    public QCharity(Path<? extends Charity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCharity(PathMetadata metadata) {
        super(Charity.class, metadata);
    }

}

