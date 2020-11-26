package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.entity.Sensor;
import com.demo.project.service.ISensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public class SensorController {

    @Autowired
    private ISensorService iSensorService;

    @ApiOperation("查询所有")
    @GetMapping("list")
    public CommonResult list(){
        return CommonResult.success(iSensorService.list());
    }

    @ApiOperation("根据id查询")
    @GetMapping("get")
    public CommonResult get(long id){
        return CommonResult.success(iSensorService.getById(id));
    }

    @ApiOperation("新增")
    @GetMapping("insertSensor")
    public CommonResult insertSensor(Sensor sensor){
        return CommonResult.success(iSensorService.save(sensor));
    }





}
