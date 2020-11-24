package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.equipmentControl.StartupItem;
import com.demo.utils.RecordLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/control")
@Api(tags = "控制服务")
public class Control {


    private static StartupItem startupItem;
    @ApiOperation("连接mqtt")
    @GetMapping("open")
    public CommonResult open(){
        if(startupItem!=null)
        startupItem=new StartupItem();
        return CommonResult.success("启动成功");
    }
    @ApiOperation("开启设备")
    @GetMapping("start")
    public CommonResult start(){
        startupItem=new StartupItem();
        return CommonResult.success("启动成功");
    }
    @ApiOperation("关闭设备")
    @GetMapping("stop")
    public CommonResult stop(){
        startupItem=new StartupItem();

        return CommonResult.success("启动成功");
    }
}
