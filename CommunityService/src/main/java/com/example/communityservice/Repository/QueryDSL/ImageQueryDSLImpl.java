package com.example.communityservice.Repository.QueryDSL;

import com.example.communityservice.Entity.Image;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.communityservice.Entity.QImage.image;

@RequiredArgsConstructor
public class ImageQueryDSLImpl implements ImageQueryDSL {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Image> findByCommunityId(Long communityId){
        return queryFactory
                .selectFrom(image)
                .where(image.community.communityId.eq(communityId))
                .fetch();
    }

    @Override
    public List<String> findByCommunityIsNull(){
        return queryFactory
                .select(image.imageName)
                .from(image)
                .where(image.community.isNull())
                .fetch();
    }

    @Override
    public Image findByImageUrl(String imageUrl){
        return queryFactory
                .selectFrom(image)
                .where(image.imageUrl.eq(imageUrl))
                .fetchOne();
    }

    @Override
    public void deleteImagesWhereCommunityIdIsNull() {
        queryFactory
                .delete(image)
                .where(image.community.communityId.isNull())
                .execute();
    }

}
