package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.service.IRoomService;
import com.demo.project.service.ISensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author com
* @since 2020-11-01
*/
@RestController
@RequestMapping("Sensor")
@Api(tags = "设备控制")
@Log4j2
    public class SensorController {

    @Autowired
    private ISensorService iSensorService;

    @ApiOperation("查询所有")
    @GetMapping("list")
    public CommonResult list(){
        return CommonResult.success(iSensorService.list());
    }

}
