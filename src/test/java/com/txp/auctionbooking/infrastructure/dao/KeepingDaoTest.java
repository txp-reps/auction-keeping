package com.txp.auctionbooking.infrastructure.dao;

import com.txp.auctionbooking.domain.entities.KeepingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KeepingDaoTest {

    @Autowired
    private KeepingDao dao;

    @Test
    public void should_save_entity_succeed() {
        // given
        KeepingEntity keepingEntity = KeepingEntity.builder()
                .applyType("keeping")
                .applyId(111L)
                .applyStatus("applying")
                .build();

        // when
        dao.keepPlaceApply(keepingEntity);


        // then
        long count = dao.countApplies();
        assertEquals(1, count);

    }

}