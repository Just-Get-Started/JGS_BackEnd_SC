package com.example.communityservice.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunity is a Querydsl query type for Community
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunity extends EntityPathBase<Community> {

    private static final long serialVersionUID = -1288790289L;

    public static final QCommunity community = new QCommunity("community");

    public final NumberPath<Long> communityId = createNumber("communityId", Long.class);

    public final StringPath content = createString("content");

    public final ListPath<Image, QImage> images = this.<Image, QImage>createList("images", Image.class, QImage.class, PathInits.DIRECT2);

    public final BooleanPath recruit = createBoolean("recruit");

    public final DateTimePath<java.time.LocalDateTime> recruitDate = createDateTime("recruitDate", java.time.LocalDateTime.class);

    public final StringPath teamName = createString("teamName");

    public final StringPath title = createString("title");

    public final DatePath<java.time.LocalDate> writeDate = createDate("writeDate", java.time.LocalDate.class);

    public final NumberPath<Long> writer = createNumber("writer", Long.class);

    public QCommunity(String variable) {
        super(Community.class, forVariable(variable));
    }

    public QCommunity(Path<? extends Community> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunity(PathMetadata metadata) {
        super(Community.class, metadata);
    }

}

