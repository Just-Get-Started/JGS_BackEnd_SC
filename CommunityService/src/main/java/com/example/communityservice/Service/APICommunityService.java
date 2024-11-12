package com.example.communityservice.Service;

import com.example.communityservice.DTO.Request.CreateCommunityDTO;
import com.example.communityservice.DTO.Request.UpdateCommunityDTO;
import com.example.communityservice.Entity.Community;
import com.example.communityservice.Exception.BusinessLogicException;
import com.example.communityservice.Exception.ExceptionTypes.CommunityExceptionType;
import com.example.communityservice.Repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class APICommunityService {

    private final CommunityRepository communityRepository;
    private final APIImageService apiImageService;
    private final OpenFeignService openFeignService;

    @Transactional(rollbackFor = Exception.class)
    public void createCommunity(Long memberId, CreateCommunityDTO createCommunityDTO) {
        Community community;

        if (createCommunityDTO.recruit()) {
            community = createTeamRecruitmentCommunityPost(createCommunityDTO, memberId);
        } else {
            community = createNonTeamRecruitmentCommunityPost(createCommunityDTO, memberId);
        }

        try {
            communityRepository.save(community);
            apiImageService.linkImagesToCommunity(community.getContent(),community);
        } catch (Exception e) {
            log.warn("Create community failed : {}", e.getMessage());
            throw new BusinessLogicException(CommunityExceptionType.COMMUNITY_SAVE_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "communityInfoCache", key = "'community/' + #updateCommunityDTO.communityId", cacheManager = "cacheManager")
    public void updateCommunityPost(Long memberId, UpdateCommunityDTO updateCommunityDTO){
        Community community = findCommunityById(updateCommunityDTO.communityId());

        validateMemberAuthority(community.getWriter(), memberId);

        community.updateContentAndTitle(updateCommunityDTO.content(), updateCommunityDTO.title());
        apiImageService.linkImagesToCommunity(community.getContent(), community);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "communityInfoCache", key = "'community/' + #communityId", cacheManager = "cacheManager")
    public void deleteCommunityPost(Long memberId, Long communityId){
        Community community = findCommunityById(communityId);

        validateMemberAuthority(community.getWriter(), memberId);

        communityRepository.delete(community);
    }

    private void validateMemberAuthority(Long writerId, Long memberId){
        if(!Objects.equals(writerId, memberId)){
            log.warn("Not Allow Authority - Community");
            throw new BusinessLogicException(CommunityExceptionType.MEMBER_INVALID_AUTHORITY);
        }
    }

    private Community findCommunityById(Long communityId){
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new BusinessLogicException(CommunityExceptionType.COMMUNITY_NOT_FOUND));
    }

    private Community createTeamRecruitmentCommunityPost(CreateCommunityDTO dto, Long memberId){
        if(dto.recruitDate() == null || dto.teamName().isBlank()){
            throw new BusinessLogicException(CommunityExceptionType.COMMUNITY_SAVE_ERROR);
        }

        openFeignService.validateLeaderAuthority(dto.teamName(), String.valueOf(memberId));

        return Community.builder()
                .content(dto.content())
                .title(dto.title())
                .recruit(true)
                .recruitDate(dto.recruitDate())
                .teamName(dto.teamName())
                .writer(memberId)
                .writeDate(LocalDate.now())
                .build();
    }

    private Community createNonTeamRecruitmentCommunityPost(CreateCommunityDTO dto, Long memberId){
        return Community.builder()
                .content(dto.content())
                .title(dto.title())
                .recruit(false)
                .writeDate(LocalDate.now())
                .teamName(null)
                .writer(memberId)
                .recruitDate(null)
                .build();
    }
}
