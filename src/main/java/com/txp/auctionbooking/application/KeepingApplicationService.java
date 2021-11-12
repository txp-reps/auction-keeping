package com.txp.auctionbooking.application;

import com.txp.auctionbooking.application.dto.ApplyDTO;
import com.txp.auctionbooking.application.dto.ApplyKeepingRequest;
import com.txp.auctionbooking.application.dto.ResultDTO;
import com.txp.auctionbooking.domain.entities.KeepingEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@AllArgsConstructor
@Service
public class KeepingApplicationService {

    private KeepingEntity keepingEntity;

    public ResultDTO<ApplyDTO> applyKeeping(ApplyKeepingRequest request) {
        Optional<KeepingEntity> keepingEntity = this.keepingEntity.applyKeepingPlace(request.getGoodsType());
        ApplyDTO resultData = ApplyDTO.builder().build();
        keepingEntity.ifPresent(keepingEntity1 -> resultData.setApplyId(keepingEntity1.getApplyId()));

        return ResultDTO.ok(resultData);
    }

    public ResultDTO<ApplyDTO> queryApplyStatus(long applyId) {
        return null;
    }
}
