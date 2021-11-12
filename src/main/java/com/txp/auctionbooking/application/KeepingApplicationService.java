package com.txp.auctionbooking.application;

import com.txp.auctionbooking.application.dto.ApplyDTO;
import com.txp.auctionbooking.application.dto.ApplyKeepingRequest;
import com.txp.auctionbooking.application.dto.ResultCode;
import com.txp.auctionbooking.application.dto.ResultDTO;
import com.txp.auctionbooking.domain.entities.KeepingEntity;
import com.txp.auctionbooking.domain.services.ApplyKeepingPlaceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.util.Optional;

@RequiredArgsConstructor
@AllArgsConstructor
@Service
@Slf4j
public class KeepingApplicationService {

    private ApplyKeepingPlaceService keepingPlaceService;

    public ResultDTO<ApplyDTO> applyKeeping(ApplyKeepingRequest request) {
        Optional<KeepingEntity> keepingEntity;
        ApplyDTO resultData = ApplyDTO.builder().build();

        try {
            keepingEntity = this.keepingPlaceService.applyKeepingPlace(request.getGoodsType());
        } catch (ServiceUnavailableException e) {
            log.error(e.getLocalizedMessage());
            return ResultDTO.fail(resultData, ResultCode.SERVICE_UNAVAILABLE_FAIL, "服务暂不可用，稍后再试");
        }

        if (!keepingEntity.isPresent()) {
            return ResultDTO.fail(resultData, ResultCode.CREATE_FAIL, "无可用车位，请排队");
        }


        keepingEntity.ifPresent(keepingEntity1 -> resultData.setApplyId(keepingEntity1.getApplyId()));

        return ResultDTO.ok(resultData);
    }

    public ResultDTO<ApplyDTO> queryApplyStatus(long applyId) {
        return null;
    }
}
