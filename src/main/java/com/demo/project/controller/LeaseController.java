package com.demo.project.controller;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.Lease;
import com.demo.project.service.IGatewayService;
import com.demo.project.service.ILeaseService;
import com.demo.utils.DateUtils;
import com.demo.utils.ListSortUtil;
import com.demo.utils.RecordLog;
import com.demo.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author com
* @since 2020-11-01
*/
@RestController
@RequestMapping("/lease")
@Api(tags = "出租管理")
    public class LeaseController {

    @Autowired
    private ILeaseService iLeaseService;
    private static RecordLog recordLog;
    public LeaseController()
    {
        this.recordLog= ApplicationContextProvider.getBean(RecordLog.class);
    }

    @ApiOperation("查询所有出租记录")
    @GetMapping("/list")
    public CommonResult list(){

        return CommonResult.success(iLeaseService.list());
    }

    @ApiOperation("新增")
    @GetMapping("/insertLease")
    public CommonResult insertLease(@RequestParam("roomId")long roomId, @RequestParam("userId")long userId, @RequestParam("StartTime")String StartTime, @RequestParam("ExpiryTimer")String ExpiryTimer){
        try {
            if(StringUtils.isNotEmpty(StartTime)&&StringUtils.isNotEmpty(ExpiryTimer)) {//date!=null&&(date.indexOf("月")!=-1)||(date.indexOf("日")!=-1)
                Lease lease = new Lease();
                lease.setRoomId(roomId);
                lease.setUserId(userId);
                lease.setCreatTime(DateUtils.getNowDate());
                lease.setStartTime(StartTime);
                lease.setExpiryTimer(ExpiryTimer);
                iLeaseService.save(lease);
                return CommonResult.success("成功");
            }
            else
                return CommonResult.failed("数据错误");
        }catch (Exception e){
            recordLog.read(e);
            e.printStackTrace();
            return CommonResult.failed("未知错误");
        }
    }
    @ApiOperation("查询最新的数据")
    @GetMapping("/queryNewData")
    public CommonResult queryNewData(long roomId){
//        List<Lease> lease=iLeaseService.getRoomId(roomId);
//        lease.
//        lease.get(0);
        return CommonResult.success(iLeaseService.list());
    }

}
