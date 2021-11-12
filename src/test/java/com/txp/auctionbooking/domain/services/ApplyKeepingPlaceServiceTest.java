package com.txp.auctionbooking.domain.services;

import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.domain.entities.KeepingEntity;
import com.txp.auctionbooking.infrastructure.externalservice.AuctionManagerSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class ApplyKeepingPlaceServiceTest {

    @BeforeEach
    void setup() {
        FeatureConfig.APPLY_KEEPING_TOGGLE = true;
    }

    @Test
    public void should_start_checker_when_connection_error() {
        // given
        AuctionManagerSystem auctionManagerSystem = mock(AuctionManagerSystem.class);
        doThrow(ResourceAccessException.class).when(auctionManagerSystem).keepPlaceApply(any());
        KeepingEntity keepingEntity = KeepingEntity.builder().provider(auctionManagerSystem).build();
        ApplyKeepingPlaceService keepingPlaceService = new ApplyKeepingPlaceService(keepingEntity, auctionManagerSystem);


        // when then
        assertTrue(FeatureConfig.APPLY_KEEPING_TOGGLE);
        assertThrows(ResourceAccessException.class, () -> keepingPlaceService.applyKeepingPlace(GoodsType.Car));
        assertFalse(FeatureConfig.APPLY_KEEPING_TOGGLE);

    }


}