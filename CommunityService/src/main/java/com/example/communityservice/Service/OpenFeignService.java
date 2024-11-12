package com.example.communityservice.Service;

import com.example.communityservice.OpenFeign.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenFeignService {
    private final OpenFeignClient openFeignClient;

    public void validateLeaderAuthority(String teamName, String memberId){
        openFeignClient.validateLeaderAuthority(teamName, memberId);
    }
}
