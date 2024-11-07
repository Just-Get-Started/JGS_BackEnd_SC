package com.example.conferenceservice.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConference is a Querydsl query type for Conference
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConference extends EntityPathBase<Conference> {

    private static final long serialVersionUID = -1484910591L;

    public static final QConference conference = new QConference("conference");

    public final DatePath<java.time.LocalDate> conferenceDate = createDate("conferenceDate", java.time.LocalDate.class);

    public final StringPath conferenceName = createString("conferenceName");

    public final StringPath content = createString("content");

    public final NumberPath<Long> organizer = createNumber("organizer", Long.class);

    public final StringPath winnerTeam = createString("winnerTeam");

    public QConference(String variable) {
        super(Conference.class, forVariable(variable));
    }

    public QConference(Path<? extends Conference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference(PathMetadata metadata) {
        super(Conference.class, metadata);
    }

}

