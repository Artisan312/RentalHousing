package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.service.IFacilityService;
import com.demo.project.service.IGatewayService;
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
@RequestMapping("gateway")
@Api(tags = "网关管理")
    public class GatewayController {

    @Autowired
    private IGatewayService iGatewayService;

    @ApiOperation("查询所有网关")
    @GetMapping("list")
    public CommonResult list(){
        return CommonResult.success(iGatewayService.list());
    }


}
