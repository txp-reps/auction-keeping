package com.txp.auctionbooking.domain.entities;

import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.infrastructure.dao.KeepingDao;
import com.txp.auctionbooking.infrastructure.externalservice.AuctionManagerSystem;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.naming.ServiceUnavailableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class KeepingEntityTest {

    private final KeepingDao dao = mock(KeepingDao.class);
    private final AuctionManagerSystem externalService = mock(AuctionManagerSystem.class);

    @Test
    void should_apply_keeping_place_succeed() throws ServiceUnavailableException {
        // given
        GoodsType car = GoodsType.Car;
        ArgumentCaptor<KeepingEntity> captor = ArgumentCaptor.forClass(KeepingEntity.class);

        when(dao.keepPlaceApply(captor.capture())).thenReturn(new KeepingEntity());
        when(externalService.keepPlaceApply(car)).thenReturn(true);


        // when
        KeepingEntity keepingEntity = KeepingEntity.builder()
                .applyType("keeping")
                .provider(externalService)
                .repository(dao).build();

        keepingEntity.applyKeepingPlace(car).orElseGet(KeepingEntity::new);


        // then
        verify(dao, times(1)).keepPlaceApply(any(KeepingEntity.class));

        assertEquals("keeping", captor.getValue().getApplyType());
        assertNotNull(captor.getValue().getApplyId());
    }

}