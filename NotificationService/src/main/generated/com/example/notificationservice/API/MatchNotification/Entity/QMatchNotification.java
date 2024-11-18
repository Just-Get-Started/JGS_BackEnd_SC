package com.example.notificationservice.API.MatchNotification.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMatchNotification is a Querydsl query type for MatchNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchNotification extends EntityPathBase<MatchNotification> {

    private static final long serialVersionUID = 1261477900L;

    public static final QMatchNotification matchNotification = new QMatchNotification("matchNotification");

    public final StringPath applicantTeam = createString("applicantTeam");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final BooleanPath isRead = createBoolean("isRead");

    public final NumberPath<Long> matchNotifiId = createNumber("matchNotifiId", Long.class);

    public final NumberPath<Long> matchPostId = createNumber("matchPostId", Long.class);

    public QMatchNotification(String variable) {
        super(MatchNotification.class, forVariable(variable));
    }

    public QMatchNotification(Path<? extends MatchNotification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMatchNotification(PathMetadata metadata) {
        super(MatchNotification.class, metadata);
    }

}

