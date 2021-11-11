package com.txp.auctionbooking.interfaces;

import com.txp.auctionbooking.application.KeepingApplicationService;
import com.txp.auctionbooking.application.dto.ApplyDTO;
import com.txp.auctionbooking.application.dto.ApplyKeepingRequest;
import com.txp.auctionbooking.application.dto.ResultCode;
import com.txp.auctionbooking.application.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "拍品保存")
@RequiredArgsConstructor
@RestController
@RequestMapping("/contracts/{cid}")
public class KeepingController {

    private final KeepingApplicationService applicationService;

    @ApiOperation("申请保管场地")
    @PostMapping("/safe-keeping")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO<ApplyDTO> buildBoardingCard(@PathVariable Long cid,
                                                 @RequestBody ApplyKeepingRequest applyKeepingRequest,
                                                 HttpServletResponse httpServletResponse) {
        ResultDTO<ApplyDTO> resultDTO = applicationService.applyKeeping(applyKeepingRequest);
        feedWithCorrectHttpCode(httpServletResponse, resultDTO);

        return resultDTO;
    }

    @ApiOperation("查询申请状态")
    @GetMapping("/safe-keeping")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO<ApplyDTO> queryApplyStatus(@PathVariable Long cid,
                                                 @RequestParam Integer applyId,
                                                 HttpServletResponse httpServletResponse) {
        ResultDTO<ApplyDTO> resultDTO = applicationService.queryApplyStatus(applyId);
        feedWithCorrectHttpCode(httpServletResponse, resultDTO);

        return resultDTO;
    }


    private void feedWithCorrectHttpCode(HttpServletResponse httpServletResponse, ResultDTO<ApplyDTO> resultDTO) {
        if (resultDTO.getResultCode() == ResultCode.CREATE_FAIL) {
            httpServletResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }

        if (resultDTO.getResultCode() == ResultCode.FIND_SUCCEED) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }

        if (resultDTO.getResultCode() == ResultCode.SERVICE_UNAVAILABLE_FAIL) {
            httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }

}
