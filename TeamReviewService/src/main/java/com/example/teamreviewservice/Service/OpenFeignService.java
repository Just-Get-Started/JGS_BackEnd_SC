package com.example.teamreviewservice.Service;

import com.example.teamreviewservice.DTO.OpenFeignDTO.MatchDTO;
import com.example.teamreviewservice.OpenFeign.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenFeignService {
    private final OpenFeignClient openFeignClient;

    public void validateLeaderAuthority(String teamName, String memberId){
        openFeignClient.validateLeaderAuthority(teamName, memberId);
    }

    public MatchDTO findMatchById(Long matchId){
        return openFeignClient.findMatchById(matchId);
    }

}
