package com.example.matchservice.Service;

import com.example.matchservice.DTO.EnterScoreDTO;
import com.example.matchservice.DTO.OpenFeignDTO.TeamDTO;
import com.example.matchservice.DTO.PagingResponseDTO;
import com.example.matchservice.Entity.Match;
import com.example.matchservice.Exception.BusinessLogicException;
import com.example.matchservice.Exception.MatchExceptionType;
import com.example.matchservice.OpenFeign.UpdateTierPointDTO;
import com.example.matchservice.Repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class APIMatchService {

    private final OpenFeignService openFeignService;
    private final MatchRepository matchRepository;
    private final KafkaService kafkaService;

    @Transactional(rollbackFor = Exception.class)
    public void updatePoint(Long memberId, EnterScoreDTO enterScoreDTO) {
        Match match = getGameMatch(enterScoreDTO, memberId);

        // 점수 수정
        match.updateMatchScore(enterScoreDTO.getScoreA(), enterScoreDTO.getScoreB());

        TeamDTO teamA = openFeignService.getTeamInfoDTO(match.getTeamA()).content().get(0);
        TeamDTO teamB = openFeignService.getTeamInfoDTO(match.getTeamB()).content().get(0);

        // 결과에 따라 점수를 변경하는 알고리즘
        updateTierPoints(teamA, teamB, match.getTeamAScore(), match.getTeamBScore());
    }

    private void updateTierPoints(TeamDTO teamA, TeamDTO teamB, int ATeamScore, int BTeamScore) {
        //Team name으로 TeamDTO OpenFeign으로 가져와서 OpenFeign으로 요청 보내야될듯
        Long teamATier = teamA.getTier().getTierId();
        int teamAPoints = teamA.getTierPoint();
        Long teamBTier = teamB.getTier().getTierId();
        int teamBPoints = teamB.getTierPoint();

        // 티어 차이 계산
        int tierDifference = Math.abs(teamBTier.intValue() - teamATier.intValue());
        int baseChange = 10; // 기본 점수 변화

        // 점수 변화 변수
        int scoreChange;

        if (ATeamScore > BTeamScore) {
            // 팀 A가 이긴 경우
            if (teamATier > teamBTier) {
                // 팀 A가 더 높은 티어인 경우
                scoreChange = baseChange - 2 * tierDifference;
            } else {
                // 팀 A가 더 낮은 티어인 경우
                scoreChange = baseChange + 2 * tierDifference;
            }
            teamAPoints += scoreChange;
            teamBPoints -= scoreChange;
        } else if (ATeamScore < BTeamScore) {
            // 팀 B가 이긴 경우
            if (teamBTier > teamATier) {
                // 팀 B가 더 높은 티어인 경우
                scoreChange = baseChange - 2 * tierDifference;
            } else {
                // 팀 B가 더 낮은 티어인 경우
                scoreChange = baseChange + 2 * tierDifference;
            }
            teamBPoints += scoreChange;
            teamAPoints -= scoreChange;
        }

        // 팀 A 승급/강등 처리
        if (teamAPoints >= 100) {
            int additionalTiers = 1;
            if (teamATier + additionalTiers > 5) {
                teamATier = 5L;
                teamAPoints = 100;
            } else {
                teamATier += additionalTiers;
                teamAPoints = teamAPoints % 100;
            }
        } else if (teamAPoints < 0) {
            int additionalTiers = 1;
            if (teamATier - additionalTiers < 1) {
                teamATier = 1L;
                teamAPoints = 0;
            } else {
                teamATier -= additionalTiers;
                teamAPoints = 100 + teamAPoints; // 남은 포인트
            }
        }

        // 팀 B 승급/강등 처리
        if (teamBPoints >= 100) {
            int additionalTiers = 1;
            if (teamBTier + additionalTiers > 5) {
                teamBTier = 5L;
                teamBPoints = 100;
            } else {
                teamBTier += additionalTiers;
                teamBPoints = teamBPoints % 100;
            }
        } else if (teamBPoints < 0) {
            int additionalTiers = 1;
            if (teamBTier - additionalTiers < 1) {
                teamBTier = 1L;
                teamBPoints = 0;
            } else {
                teamBTier -= additionalTiers;
                teamBPoints = 100 + teamBPoints; // 남은 포인트
            }
        }

        // 업데이트된 팀 정보를 Kafka를 통해 전달
        UpdateTierPointDTO updateTierPoint = new UpdateTierPointDTO();
        updateTierPoint.setTeamAName(teamA.getTeamName());
        updateTierPoint.setTeamATierPoint(teamAPoints);
        updateTierPoint.setTeamATierId(teamATier);

        updateTierPoint.setTeamBName(teamB.getTeamName());
        updateTierPoint.setTeamBTierPoint(teamBPoints);
        updateTierPoint.setTeamBTierId(teamBTier);
        kafkaService.updateTierPoint(updateTierPoint);
    }


    private Match getGameMatch(EnterScoreDTO enterScoreDTO, Long memberId){
        Match match = matchRepository.findById(enterScoreDTO.getMatchId())
                .orElseThrow(() -> new BusinessLogicException(MatchExceptionType.MATCH_NOT_FOUND));

        // 그 경기의 심판만 점수 기입 가능
        if (!match.getReferee().equals(memberId)) {
            log.warn("Not Allow Authority - Update Match Point");
            throw new BusinessLogicException(MatchExceptionType.REFEREE_INVALID_AUTHORITY);
        }

        if (match.getMatchDate().isAfter(LocalDateTime.now())) {
            log.warn("Not Date Allow - Update Match Point");
            throw new BusinessLogicException(MatchExceptionType.MATCH_NOT_DATE_ALLOW);
        }

        // 이미 점수가 입력된 경우 예외 처리
        if (match.getTeamAScore() != 0 && match.getTeamBScore() != 0) {
            log.warn("Match Point Already Filled Out");
            throw new BusinessLogicException(MatchExceptionType.MATCH_ALREADY_FILLED_OUT);
        }

        return match;
    }
}
