package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.service.IImageService;
import com.demo.utils.UploadPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public class ImageController {

    @Autowired
    private IImageService iImageService;

    @ApiOperation("所有图片")
    @GetMapping("/list")
    public CommonResult list(){

        return CommonResult.success(iImageService.list());
    }
    @ApiOperation("上传图片")
    @PostMapping("/PictureUpload")
    @ResponseBody
    public CommonResult Upload(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        UploadPic uploadPic = new UploadPic();
        try {
            uploadPic.upload(file,request,response);
            return CommonResult.success(uploadPic.getPath(),"上传成功");
        }catch (Exception e){
            e.getMessage();
            return CommonResult.failed("上传失败");
        }
    }
//    @ApiOperation("房间所有图片")
//    @GetMapping("list")
//    public CommonResult Roomlist(Integer id){
//
//        return CommonResult.success(iImageService.list());
//    }

}
