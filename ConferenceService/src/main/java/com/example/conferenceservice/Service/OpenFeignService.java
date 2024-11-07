package com.example.conferenceservice.Service;

import com.example.conferenceservice.OpenFeign.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenFeignService {
    private final OpenFeignClient openFeignClient;

    public String isTeamPresent(String teamName){
        return openFeignClient.isTeamPresent(teamName);
    }
}
