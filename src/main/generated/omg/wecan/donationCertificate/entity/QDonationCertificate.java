package omg.wecan.donationCertificate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDonationCertificate is a Querydsl query type for DonationCertificate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDonationCertificate extends EntityPathBase<DonationCertificate> {

    private static final long serialVersionUID = 442989716L;

    public static final QDonationCertificate donationCertificate = new QDonationCertificate("donationCertificate");

    public final omg.wecan.global.entity.QBaseEntity _super = new omg.wecan.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgEndpoint = createString("imgEndpoint");

    public final StringPath tile = createString("tile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDonationCertificate(String variable) {
        super(DonationCertificate.class, forVariable(variable));
    }

    public QDonationCertificate(Path<? extends DonationCertificate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDonationCertificate(PathMetadata metadata) {
        super(DonationCertificate.class, metadata);
    }

}

