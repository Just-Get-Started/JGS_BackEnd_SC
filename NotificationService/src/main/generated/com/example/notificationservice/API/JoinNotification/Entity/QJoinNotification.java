package com.example.notificationservice.API.JoinNotification.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJoinNotification is a Querydsl query type for JoinNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJoinNotification extends EntityPathBase<JoinNotification> {

    private static final long serialVersionUID = 1558086032L;

    public static final QJoinNotification joinNotification = new QJoinNotification("joinNotification");

    public final NumberPath<Long> communityId = createNumber("communityId", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final BooleanPath isRead = createBoolean("isRead");

    public final NumberPath<Long> notificationId = createNumber("notificationId", Long.class);

    public final NumberPath<Long> pubMember = createNumber("pubMember", Long.class);

    public QJoinNotification(String variable) {
        super(JoinNotification.class, forVariable(variable));
    }

    public QJoinNotification(Path<? extends JoinNotification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJoinNotification(PathMetadata metadata) {
        super(JoinNotification.class, metadata);
    }

}

