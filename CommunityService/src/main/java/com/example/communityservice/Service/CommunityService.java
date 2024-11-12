package com.example.communityservice.Service;

import com.example.communityservice.DTO.Response.CommunityDTO;
import com.example.communityservice.DTO.Response.CommunityInfoDTO;
import com.example.communityservice.DTO.Response.PagingResponseDTO;
import com.example.communityservice.Entity.Community;
import com.example.communityservice.Exception.BusinessLogicException;
import com.example.communityservice.Exception.ExceptionTypes.CommunityExceptionType;
import com.example.communityservice.Repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    @Transactional(readOnly = true)
    public PagingResponseDTO<CommunityDTO> findAll(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CommunityDTO> communityPage = communityRepository.searchPagedCommunities(keyword, pageable);

        List<CommunityDTO> communityDTOList = communityPage.getContent().stream().toList();

        return PagingResponseDTO.of(communityPage, communityDTOList);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "communityInfoCache", key = "'community/' + #communityId",
            cacheManager = "cacheManager")
    public CommunityInfoDTO findById(Long communityId){
        Optional<CommunityInfoDTO> optionalCommunityInfoDTO = communityRepository.findByIdCustom(communityId);
        if(optionalCommunityInfoDTO.isPresent()){
            return optionalCommunityInfoDTO.get();
        } else{
            throw new BusinessLogicException(CommunityExceptionType.COMMUNITY_NOT_FOUND);
        }
    }
}
