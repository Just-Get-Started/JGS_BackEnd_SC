package com.example.matchservice.OpenFeign;

import com.example.matchservice.DTO.OpenFeignDTO.TeamDTO;
import com.example.matchservice.DTO.PagingResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenAiClient", url = "http://localhost:8080")
public interface OpenFeignClient {
    @GetMapping("/team")
    PagingResponseDTO<TeamDTO> getTeamInfoDTO(@RequestParam("keyword") String keyword);
}
