package com.demo.project.controller;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.Image;
import com.demo.project.service.IImageService;
import com.demo.project.service.IRoomService;
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

    @Autowired
    private IRoomService roomService;


    @ApiOperation("上传图片")
    @PostMapping("/PictureUpload")
    @ResponseBody
    public CommonResult Upload(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response,long roomId){
        UploadPic uploadPic = new UploadPic();
        try {
            uploadPic.upload(file,request,response);
            Image image=new Image();
            image.setRoomId(roomId);
            image.setHeadPortrait(uploadPic.getPath());
            iImageService.save(image);
            return CommonResult.success(uploadPic.getPath(),"上传成功");
        }catch (Exception e){
            e.getMessage();
            return CommonResult.failed("上传失败");
        }
    }
    @ApiOperation("删除图片")
    @GetMapping("/Delete")
    public CommonResult Delete(long Id){
        FileInputStream fis = null;
        try {
            File file = new File(iImageService.getById(Id).getHeadPortrait());
            fis = new FileInputStream(file);
            if(file.exists()) {
                file.delete();
                iImageService.removeById(Id);
                return CommonResult.failed("成功");
            }

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
        return CommonResult.failed("失败");
    }
    @ApiOperation("根据房间id查询所有图片")
    @GetMapping("/getAll")
    public void getAll(long roomId){

    }
    @ApiOperation("根据房间id查询首页图片")
    @GetMapping("/getroomId")
    public void getroomId(long roomId,HttpServletResponse response){
        FileInputStream fis = null;
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(iImageService.getById(roomService.getById(roomId).getImageId()).getHeadPortrait());
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
    @ApiOperation("根据id查询图片")
    @GetMapping("/get")
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


    @ApiOperation("根据房间id查询所有图片id")
    @GetMapping("/number")
    public CommonResult number(long roomId){
        return CommonResult.success(iImageService.number(roomId));
    }

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
