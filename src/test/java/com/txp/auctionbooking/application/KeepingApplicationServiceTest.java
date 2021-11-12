package com.txp.auctionbooking.application;

import com.txp.auctionbooking.application.dto.ApplyDTO;
import com.txp.auctionbooking.application.dto.ApplyKeepingRequest;
import com.txp.auctionbooking.application.dto.ResultCode;
import com.txp.auctionbooking.application.dto.ResultDTO;
import com.txp.auctionbooking.domain.entities.KeepingEntity;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KeepingApplicationServiceTest {

    @Test
    void should_apply_keeping_place_succeed() {
        // given
        KeepingEntity entity = mock(KeepingEntity.class);
        KeepingEntity resEntity = KeepingEntity.builder().applyId(111L).build();
        when(entity.applyKeepingPlace(any())).thenReturn(Optional.of(resEntity));
        KeepingApplicationService applicationService = new KeepingApplicationService(entity);

        // when
        ApplyKeepingRequest request = ApplyKeepingRequest.builder().goodsType(GoodsType.Car).build();
        ResultDTO<ApplyDTO> resultDTO = applicationService.applyKeeping(request);

        // then
        assertEquals(111L, resultDTO.getBody().getApplyId());
    }

    @Test
    void should_apply_keeping_place_fail() {
        // given
        KeepingEntity entity = mock(KeepingEntity.class);
        when(entity.applyKeepingPlace(any())).thenReturn(Optional.empty());
        KeepingApplicationService applicationService = new KeepingApplicationService(entity);

        // when
        ApplyKeepingRequest request = ApplyKeepingRequest.builder().goodsType(GoodsType.Car).build();
        ResultDTO<ApplyDTO> resultDTO = applicationService.applyKeeping(request);

        // then
        assertEquals(ResultCode.CREATE_FAIL, resultDTO.getResultCode());
    }
}