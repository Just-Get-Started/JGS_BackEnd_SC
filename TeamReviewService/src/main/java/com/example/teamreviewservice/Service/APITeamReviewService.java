package com.example.teamreviewservice.Service;

import com.example.teamreviewservice.DTO.FillReviewDTO;
import com.example.teamreviewservice.DTO.OpenFeignDTO.MatchDTO;
import com.example.teamreviewservice.Entity.TeamReview;
import com.example.teamreviewservice.Exception.BusinessLogicException;
import com.example.teamreviewservice.Exception.TeamReviewExceptionType;
import com.example.teamreviewservice.Repository.TeamReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class APITeamReviewService {

    private final TeamReviewRepository teamReviewRepository;
    private final OpenFeignService openFeignService;

    @Transactional(rollbackFor = Exception.class)
    public void fillReview(Long memberId, FillReviewDTO fillReviewDTO){
        MatchDTO match = openFeignService.findMatchById(fillReviewDTO.matchId());

        TeamReview teamReview;
        //A팀과 같은 이름이라면 B팀에 대한 리뷰 작성임
        if(match.teamA().equals(fillReviewDTO.teamName())){
            openFeignService.validateLeaderAuthority(match.teamA(), String.valueOf(memberId));
            teamReview = makeTeamReview(match.teamB(), fillReviewDTO, memberId);
        } else {
            openFeignService.validateLeaderAuthority(match.teamB(), String.valueOf(memberId));
            teamReview = makeTeamReview(match.teamA(), fillReviewDTO, memberId);
        }

        try{
            teamReviewRepository.save(teamReview);
        } catch (Exception e){
            log.warn("Team Review Save Failed : {}", e.getMessage());
            throw new BusinessLogicException(TeamReviewExceptionType.TEAM_REVIEW_SAVE_ERROR);
        }
    }

    private TeamReview makeTeamReview(String teamName, FillReviewDTO fillReviewDTO, Long memberId){
        return TeamReview.builder()
                .teamName(teamName)
                .content(fillReviewDTO.content())
                .rating(fillReviewDTO.rating())
                .writer(memberId)
                .build();
    }

}
