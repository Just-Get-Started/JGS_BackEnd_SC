package com.example.matchservice.Service;

import com.example.matchservice.DTO.OpenFeignDTO.TeamDTO;
import com.example.matchservice.DTO.PagingResponseDTO;
import com.example.matchservice.OpenFeign.OpenFeignClient;
import com.example.matchservice.OpenFeign.UpdateTierPointDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenFeignService {
    private final OpenFeignClient openFeignClient;

    public PagingResponseDTO<TeamDTO> getTeamInfoDTO(String keyword){
        return openFeignClient.getTeamInfoDTO(keyword);
    }
}
