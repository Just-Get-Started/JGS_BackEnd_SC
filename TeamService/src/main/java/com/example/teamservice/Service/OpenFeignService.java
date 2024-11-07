package com.example.teamservice.Service;

import com.example.teamservice.DTO.Response.TeamMemberListDTO;
import com.example.teamservice.DTO.Response.TeamReviewListDTO;
import com.example.teamservice.OpenFeign.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenFeignService {
    private final OpenFeignClient openFeignClient;

    public TeamMemberListDTO getTeamMemberList(String teamName){
        return openFeignClient.findTeamMembersByTeamName(teamName);
    }

    public TeamReviewListDTO getTeamReviewList(String teamName){
        return openFeignClient.findTeamReviewsByTeamName(teamName);
    }
}
