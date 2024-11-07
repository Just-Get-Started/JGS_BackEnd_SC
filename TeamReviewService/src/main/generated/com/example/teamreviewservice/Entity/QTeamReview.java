package com.example.teamreviewservice.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeamReview is a Querydsl query type for TeamReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamReview extends EntityPathBase<TeamReview> {

    private static final long serialVersionUID = -1943555519L;

    public static final QTeamReview teamReview = new QTeamReview("teamReview");

    public final StringPath content = createString("content");

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    public final StringPath teamName = createString("teamName");

    public final NumberPath<Long> teamReviewId = createNumber("teamReviewId", Long.class);

    public final NumberPath<Long> writer = createNumber("writer", Long.class);

    public QTeamReview(String variable) {
        super(TeamReview.class, forVariable(variable));
    }

    public QTeamReview(Path<? extends TeamReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamReview(PathMetadata metadata) {
        super(TeamReview.class, metadata);
    }

}

