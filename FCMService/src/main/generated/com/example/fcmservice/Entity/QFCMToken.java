package com.example.fcmservice.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFCMToken is a Querydsl query type for FCMToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFCMToken extends EntityPathBase<FCMToken> {

    private static final long serialVersionUID = 1283300924L;

    public static final QFCMToken fCMToken = new QFCMToken("fCMToken");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath fcmToken = createString("fcmToken");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> tokenId = createNumber("tokenId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QFCMToken(String variable) {
        super(FCMToken.class, forVariable(variable));
    }

    public QFCMToken(Path<? extends FCMToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFCMToken(PathMetadata metadata) {
        super(FCMToken.class, metadata);
    }

}

