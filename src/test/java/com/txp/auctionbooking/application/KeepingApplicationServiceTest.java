package com.txp.auctionbooking.application;

import com.txp.auctionbooking.application.dto.ApplyDTO;
import com.txp.auctionbooking.application.dto.ApplyKeepingRequest;
import com.txp.auctionbooking.application.dto.ResultCode;
import com.txp.auctionbooking.application.dto.ResultDTO;
import com.txp.auctionbooking.domain.entities.KeepingEntity;
import com.txp.auctionbooking.domain.services.ApplyKeepingPlaceService;
import com.txp.auctionbooking.domain.services.FeatureConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.ServiceUnavailableException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KeepingApplicationServiceTest {

    @BeforeEach
    void setup() {
        FeatureConfig.APPLY_KEEPING_TOGGLE = true;
    }

    @Test
    void should_apply_keeping_place_succeed() throws ServiceUnavailableException {
        // given
        KeepingEntity resEntity = KeepingEntity.builder().applyId(111L).build();
        ApplyKeepingPlaceService domainService = mock(ApplyKeepingPlaceService.class);
        when(domainService.applyKeepingPlace(any())).thenReturn(Optional.of(resEntity));

        KeepingApplicationService applicationService = new KeepingApplicationService(domainService);

        // when
        ApplyKeepingRequest request = ApplyKeepingRequest.builder().goodsType(GoodsType.Car).build();
        ResultDTO<ApplyDTO> resultDTO = applicationService.applyKeeping(request);

        // then
        assertEquals(111L, resultDTO.getBody().getApplyId());
    }

    @Test
    void should_apply_keeping_place_fail() throws ServiceUnavailableException {
        // given
        ApplyKeepingPlaceService domainService = mock(ApplyKeepingPlaceService.class);
        when(domainService.applyKeepingPlace(any())).thenReturn(Optional.empty());
        KeepingApplicationService applicationService = new KeepingApplicationService(domainService);

        // when
        ApplyKeepingRequest request = ApplyKeepingRequest.builder().goodsType(GoodsType.Car).build();
        ResultDTO<ApplyDTO> resultDTO = applicationService.applyKeeping(request);

        // then
        assertEquals(ResultCode.CREATE_FAIL, resultDTO.getResultCode());
    }

    @Test
    void should_handle_service_unavailable_exception() throws ServiceUnavailableException {
        ApplyKeepingPlaceService domainService = mock(ApplyKeepingPlaceService.class);
        doThrow(ServiceUnavailableException.class).when(domainService).applyKeepingPlace(any());
        FeatureConfig.APPLY_KEEPING_TOGGLE = false;
        KeepingApplicationService applicationService = new KeepingApplicationService(domainService);


        ResultDTO<ApplyDTO> applyDTOResultDTO = applicationService.applyKeeping(ApplyKeepingRequest.builder().goodsType(GoodsType.Car).build());

        assertEquals(ResultCode.SERVICE_UNAVAILABLE_FAIL, applyDTOResultDTO.getResultCode());
    }


}