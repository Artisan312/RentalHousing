package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.service.IImageService;
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
@RequestMapping("image")
@Api(tags = "图片")
@Log4j2
    public class ImageController {

    @Autowired
    private IImageService iImageService;

    @ApiOperation("所有图片")
    @GetMapping("list")
    public CommonResult list(){

        return CommonResult.success(iImageService.list());
    }

//    @ApiOperation("房间所有图片")
//    @GetMapping("list")
//    public CommonResult Roomlist(Integer id){
//
//        return CommonResult.success(iImageService.list());
//    }

}
