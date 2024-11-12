package com.example.communityservice.Repository.QueryDSL;

import com.example.communityservice.Entity.Image;

import java.util.List;

public interface ImageQueryDSL {

    List<Image> findByCommunityId(Long communityId);

    List<String> findByCommunityIsNull();

    Image findByImageUrl(String imageUrl);

    void deleteImagesWhereCommunityIdIsNull();
}
