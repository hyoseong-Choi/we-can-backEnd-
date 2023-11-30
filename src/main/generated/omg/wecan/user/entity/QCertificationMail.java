package omg.wecan.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCertificationMail is a Querydsl query type for CertificationMail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCertificationMail extends EntityPathBase<CertificationMail> {

    private static final long serialVersionUID = 1973957912L;

    public static final QCertificationMail certificationMail = new QCertificationMail("certificationMail");

    public final omg.wecan.global.entity.QBaseEntity _super = new omg.wecan.global.entity.QBaseEntity(this);

    public final StringPath certificationNum = createString("certificationNum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCertificationMail(String variable) {
        super(CertificationMail.class, forVariable(variable));
    }

    public QCertificationMail(Path<? extends CertificationMail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCertificationMail(PathMetadata metadata) {
        super(CertificationMail.class, metadata);
    }

}

