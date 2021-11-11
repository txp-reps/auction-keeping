package com.txp.auctionbooking.application;

import com.txp.auctionbooking.application.dto.ApplyDTO;
import com.txp.auctionbooking.application.dto.ApplyKeepingRequest;
import com.txp.auctionbooking.application.dto.ResultDTO;
import org.springframework.stereotype.Service;

@Service
public class KeepingApplicationService {
    public ResultDTO<ApplyDTO> applyKeeping(ApplyKeepingRequest car) {
        return null;
    }

    public ResultDTO<ApplyDTO> queryApplyStatus(long applyId) {
        return null;
    }
}
