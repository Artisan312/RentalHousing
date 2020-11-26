package com.demo.project.controller;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.service.IImageService;
import com.demo.utils.RecordLog;
import com.demo.utils.StringUtils;
import com.demo.utils.UploadPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    private RecordLog recordLog;
    public ImageController()
    {
        this.recordLog= ApplicationContextProvider.getBean(RecordLog.class);
    }

    @Autowired
    private IImageService iImageService;

//    @ApiOperation("所有图片")
//    @GetMapping("/list")
//    public CommonResult list(){
//        return CommonResult.success(iImageService.list());
//    }
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

    @ApiOperation("根据id查询")
    @RequestMapping("/get")
    public void get(long id,HttpServletResponse response){
        FileInputStream fis = null;
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(iImageService.getById(id).getHeadPortrait());
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            recordLog.read(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    recordLog.read(e);
                }
            }
        }
    }

//    @ApiOperation("房间所有图片")
//    @GetMapping("list")
//    public CommonResult Roomlist(Integer id){
//
//        return CommonResult.success(iImageService.list());
//    }

//@GetMapping("/avatar")
//public void avatar(long Id)  {
//    if (StringUtils.isBlank(Id)) {
//        throw new RuntimeException("Id不能为空", null);
//    }
//    if (null == avatarType) {
//        avatarType = 1;
//    }
//    ResultResponse<byte[]> avatar = xxxxxService.avatar(userId, avatarType);
//    byte[] data = avatar.getData();
//    ServletOutputStream out = response.getOutputStream();
//    out.write(data);
//    out.flush();
//    }

}
