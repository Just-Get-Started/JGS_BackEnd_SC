package com.example.notificationservice.API.TeamInviteNotification.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeamInviteNotification is a Querydsl query type for TeamInviteNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamInviteNotification extends EntityPathBase<TeamInviteNotification> {

    private static final long serialVersionUID = -379638072L;

    public static final QTeamInviteNotification teamInviteNotification = new QTeamInviteNotification("teamInviteNotification");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> inviteDate = createDateTime("inviteDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> inviteId = createNumber("inviteId", Long.class);

    public final BooleanPath isRead = createBoolean("isRead");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath teamName = createString("teamName");

    public QTeamInviteNotification(String variable) {
        super(TeamInviteNotification.class, forVariable(variable));
    }

    public QTeamInviteNotification(Path<? extends TeamInviteNotification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamInviteNotification(PathMetadata metadata) {
        super(TeamInviteNotification.class, metadata);
    }

}

