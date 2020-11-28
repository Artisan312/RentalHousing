package com.demo.project.controller;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.equipmentControl.MqttDispose;
import com.demo.equipmentControl.StartupItem;
import com.demo.utils.IpUtils;
import com.demo.utils.RecordLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/control")
@Api(tags = "控制服务")
public class Control {


    private static StartupItem startupItem;
    private RecordLog recordLog;
    public Control()
    {
        this.recordLog= ApplicationContextProvider.getBean(RecordLog.class);
    }
    @ApiOperation("连接mqtt")
    @GetMapping("open")
    public CommonResult open(){
        if(startupItem!=null)
        startupItem=new StartupItem();
        return CommonResult.success("启动成功");
    }
    @ApiOperation("开启设备")
    @PostMapping("/start")
    public CommonResult start(){
        startupItem=new StartupItem();
        return CommonResult.success("启动成功");
    }
    @ApiOperation("日志")
    @PostMapping("/recordLog")
    public CommonResult recordLog(HttpServletRequest request,@RequestParam String str){
        try {
            IpUtils.getIpAddr(request);
            System.out.println(str);
           recordLog.readAction("的地方地方" + str);
        }catch (Exception e)
        {
            recordLog.readAction("错误");
        }
        return CommonResult.success("成功");
    }
    @ApiOperation("关闭设备")
    @PostMapping("stop")
    public CommonResult stop(@RequestParam String pwd){
        startupItem=new StartupItem();

        return CommonResult.success("启动成功");
    }

}
