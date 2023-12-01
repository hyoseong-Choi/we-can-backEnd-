package omg.wecan.challenge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChallenge is a Querydsl query type for Challenge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChallenge extends EntityPathBase<Challenge> {

    private static final long serialVersionUID = 955680852L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChallenge challenge = new QChallenge("challenge");

    public final EnumPath<omg.wecan.recruit.Enum.ChallengeType> challengeType = createEnum("challengeType", omg.wecan.recruit.Enum.ChallengeType.class);

    public final StringPath charityName = createString("charityName");

    public final QChattingRoom chattingRoom;

    public final StringPath checkDay = createString("checkDay");

    public final StringPath coverImageEndpoint = createString("coverImageEndpoint");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> finePerOnce = createNumber("finePerOnce", Integer.class);

    public final BooleanPath finished = createBoolean("finished");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<omg.wecan.recruit.Enum.PaymentType> paymentType = createEnum("paymentType", omg.wecan.recruit.Enum.PaymentType.class);

    public final NumberPath<Integer> peopleNum = createNumber("peopleNum", Integer.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final ListPath<UserChallenge, QUserChallenge> userChallenge = this.<UserChallenge, QUserChallenge>createList("userChallenge", UserChallenge.class, QUserChallenge.class, PathInits.DIRECT2);

    public QChallenge(String variable) {
        this(Challenge.class, forVariable(variable), INITS);
    }

    public QChallenge(Path<? extends Challenge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChallenge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChallenge(PathMetadata metadata, PathInits inits) {
        this(Challenge.class, metadata, inits);
    }

    public QChallenge(Class<? extends Challenge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chattingRoom = inits.isInitialized("chattingRoom") ? new QChattingRoom(forProperty("chattingRoom"), inits.get("chattingRoom")) : null;
    }

}

