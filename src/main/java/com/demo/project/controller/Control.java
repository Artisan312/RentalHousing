package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.equipmentControl.StartupItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/control")
@Api(tags = "控制服务")
public class Control {

    @ApiOperation("查询所有设备")
    @GetMapping("open")
    public CommonResult open(){
        StartupItem startupItem=new StartupItem();
        return CommonResult.success("启动成功");
    }
}
