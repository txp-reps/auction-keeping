package com.txp.auctionbooking.interfaces;

import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.application.KeepingApplicationService;
import com.txp.auctionbooking.application.dto.ApplyDTO;
import com.txp.auctionbooking.application.dto.ApplyKeepingRequest;
import com.txp.auctionbooking.application.dto.ResultCode;
import com.txp.auctionbooking.application.dto.ResultDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KeepingControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    KeepingApplicationService applicationService;

    long contractId = 1L;

    @Test
    public void should_apply_keeping_place_succeed() {
        // given
        String applyKeepingAddress = "http://localhost:" + port + "//contracts/" + contractId + "/safe-keeping";
        ApplyDTO result = ApplyDTO.builder().applyId(111L).applyType("keeping").applyStatus("applying").placeNumber(null).build();
        ApplyKeepingRequest applyKeepingRequest  = ApplyKeepingRequest.builder().goodsType(GoodsType.Car).build();
        when(applicationService.applyKeeping(applyKeepingRequest)).thenReturn(ResultDTO.ok(result));

        // when
        ResponseEntity<ResultDTO> responseEntity = restTemplate.postForEntity(applyKeepingAddress, applyKeepingRequest, ResultDTO.class);

        // then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(responseEntity.getBody()).getResultCode(), ResultCode.CREATE_SUCCEED);
    }

    @Test
    public void should_query_apply_status_succeed() {
        // given
        long applyId = 111;
        String applyKeepingAddress = "http://localhost:" + port + "//contracts/" + contractId + "/safe-keeping?applyId="+applyId;
        ApplyDTO result = ApplyDTO.builder().applyId(applyId).applyType("keeping").applyStatus("applied").placeNumber("001").build();
        when(applicationService.queryApplyStatus(applyId)).thenReturn(ResultDTO.ok(result, ResultCode.FIND_SUCCEED));

        // when
        ResponseEntity<ResultDTO> responseEntity = restTemplate.getForEntity(applyKeepingAddress, ResultDTO.class);

        // then
        ResultDTO resultDTO = Objects.requireNonNull(responseEntity.getBody());
        HashMap dtoBody = (HashMap) (resultDTO.getBody());
        assertEquals(dtoBody.get("placeNumber"),"001");
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}