package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.entity.Landlord;
import com.demo.project.service.IImageService;
import com.demo.project.service.ILandlordService;
import com.demo.utils.RecordLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* <p>
*  前端控制器
* </p>
*
* @author com
* @since 2020-11-01
*/
@RestController
@RequestMapping("landlord")
@Api(tags = "房东管理")
    public class LandlordController {
    @Autowired
    private static RecordLog recordLog;

    @Autowired
    private ILandlordService iLandlordService;

    @ApiOperation("查询所有房东")
    @GetMapping("list")
    public CommonResult list(){

        return CommonResult.success(iLandlordService.list());
    }
    @ApiOperation("登录")
    @PostMapping("login")
    public CommonResult login(@RequestBody Landlord landlord){
        try{
            landlord= iLandlordService.login(landlord);
            if(landlord==null)
                return CommonResult.success(iLandlordService.save(landlord));
            else
                return  CommonResult.success(landlord);
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
       }
    }
}
