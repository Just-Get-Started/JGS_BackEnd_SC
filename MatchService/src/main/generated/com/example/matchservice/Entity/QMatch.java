package com.example.matchservice.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMatch is a Querydsl query type for Match
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatch extends EntityPathBase<Match> {

    private static final long serialVersionUID = -1198055641L;

    public static final QMatch match = new QMatch("match");

    public final DateTimePath<java.time.LocalDateTime> matchDate = createDateTime("matchDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> matchId = createNumber("matchId", Long.class);

    public final NumberPath<Long> referee = createNumber("referee", Long.class);

    public final StringPath teamA = createString("teamA");

    public final NumberPath<Integer> teamAScore = createNumber("teamAScore", Integer.class);

    public final StringPath teamB = createString("teamB");

    public final NumberPath<Integer> teamBScore = createNumber("teamBScore", Integer.class);

    public QMatch(String variable) {
        super(Match.class, forVariable(variable));
    }

    public QMatch(Path<? extends Match> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMatch(PathMetadata metadata) {
        super(Match.class, metadata);
    }

}

