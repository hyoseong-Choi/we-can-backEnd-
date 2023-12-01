package omg.wecan.recruit.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruit is a Querydsl query type for Recruit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruit extends EntityPathBase<Recruit> {

    private static final long serialVersionUID = 723142516L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruit recruit = new QRecruit("recruit");

    public final omg.wecan.global.entity.QBaseEntity _super = new omg.wecan.global.entity.QBaseEntity(this);

    public final DatePath<java.time.LocalDate> challengeEndTime = createDate("challengeEndTime", java.time.LocalDate.class);

    public final omg.wecan.charity.entity.QCharity charity;

    public final StringPath checkDay = createString("checkDay");

    public final StringPath content = createString("content");

    public final StringPath contentImgEndpoint = createString("contentImgEndpoint");

    public final StringPath coverImageEndpoint = createString("coverImageEndpoint");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> fine = createNumber("fine", Integer.class);

    public final BooleanPath finished = createBoolean("finished");

    public final NumberPath<Integer> heartNum = createNumber("heartNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> minPeople = createNumber("minPeople", Integer.class);

    public final EnumPath<omg.wecan.recruit.Enum.PaymentType> paymentType = createEnum("paymentType", omg.wecan.recruit.Enum.PaymentType.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final EnumPath<omg.wecan.recruit.Enum.ChallengeType> type = createEnum("type", omg.wecan.recruit.Enum.ChallengeType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final omg.wecan.user.entity.QUser writer;

    public QRecruit(String variable) {
        this(Recruit.class, forVariable(variable), INITS);
    }

    public QRecruit(Path<? extends Recruit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruit(PathMetadata metadata, PathInits inits) {
        this(Recruit.class, metadata, inits);
    }

    public QRecruit(Class<? extends Recruit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charity = inits.isInitialized("charity") ? new omg.wecan.charity.entity.QCharity(forProperty("charity")) : null;
        this.writer = inits.isInitialized("writer") ? new omg.wecan.user.entity.QUser(forProperty("writer")) : null;
    }

}

