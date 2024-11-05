package com.example.teammemberservice.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeamMember is a Querydsl query type for TeamMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamMember extends EntityPathBase<TeamMember> {

    private static final long serialVersionUID = 139100289L;

    public static final QTeamMember teamMember = new QTeamMember("teamMember");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final EnumPath<TeamMemberRole> role = createEnum("role", TeamMemberRole.class);

    public final NumberPath<Long> teamMemberId = createNumber("teamMemberId", Long.class);

    public final StringPath teamName = createString("teamName");

    public QTeamMember(String variable) {
        super(TeamMember.class, forVariable(variable));
    }

    public QTeamMember(Path<? extends TeamMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamMember(PathMetadata metadata) {
        super(TeamMember.class, metadata);
    }

}

