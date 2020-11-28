package com.demo.project.controller;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.Landlord;
import com.demo.project.entity.UserToken;
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
    private RecordLog recordLog;
    public LandlordController()
    {
        this.recordLog= ApplicationContextProvider.getBean(RecordLog.class);
    }

    @Autowired
    private ILandlordService iLandlordService;

    @ApiOperation("查询所有房东")
    @GetMapping("/list")
    public CommonResult list(){

        return CommonResult.success(iLandlordService.list());
    }
    @ApiOperation("登录")
    @PostMapping("/login")
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
    @ApiOperation("根据id更新")
    @PostMapping("/updateById")
    public CommonResult updateById(@RequestBody Landlord landlord){
        if(landlord.getPwd()==null||landlord.getSalt()==null)
            return CommonResult.success(iLandlordService.updateById(landlord));
        else
            return CommonResult.failed("数据错误");
    }
    @ApiOperation("更改密码")
    @PostMapping("/updatePwd")
    public CommonResult updatePwd(@RequestBody Landlord landlord){
        try{

            if(landlord.getPwd()==null||landlord.getSalt()==null)
                return CommonResult.success(iLandlordService.updateById(landlord));
            else
                return CommonResult.failed("数据错误");
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
        }
    }
}
