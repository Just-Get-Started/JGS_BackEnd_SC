package com.example.teamservice.Service;

import com.example.teamservice.Entity.Tier;
import com.example.teamservice.Exception.BusinessLogicException;
import com.example.teamservice.Exception.TierExceptionType;
import com.example.teamservice.Repository.TierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TierService {
    private final TierRepository tierRepository;

    @Transactional(readOnly = true)
    public Tier getTierByName(String tierName){
        Tier tier = tierRepository.findByTierName(tierName);
        if(tier == null){
            log.warn("Invalid tier name : {}", tierName);
            throw new BusinessLogicException(TierExceptionType.INVALID_TIER_NAME);
        }
        return tier;
    }

//    @Transactional(readOnly = true)
//    public Tier getTierById(Long tierId){
//        return tierRepository.findById(tierId).orElseThrow(
//                () -> new BusinessLogicException(TierExceptionType.INVALID_TIER_ID));
//    }
}
