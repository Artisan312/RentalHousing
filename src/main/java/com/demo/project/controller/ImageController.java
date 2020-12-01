package com.demo.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.Image;
import com.demo.project.service.IImageService;
import com.demo.project.service.IRoomService;
import com.demo.utils.DateUtils;
import com.demo.utils.RecordLog;
import com.demo.utils.StringUtils;
import com.demo.utils.UploadPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

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
            image.setCreatTime(DateUtils.getNowDate());
            image.setHeadPortrait(uploadPic.getPath());
            iImageService.save(image);
            return CommonResult.success("上传成功");
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
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    recordLog.read(e);
                    e.printStackTrace();
                }
            }
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("根据房间id查询所有图片")
    @GetMapping("/getAll")
    public CommonResult getAll(long roomId){
        return CommonResult.success(iImageService.number(roomId));
    }
    @ApiOperation("根据房间id查询首页图片")
    @GetMapping("/getRoomId/{roomId}")
    public void getRoomId(@PathVariable long roomId,HttpServletResponse response){
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
            e.printStackTrace();
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
//        FileInputStream fis = null;
        try {
//            OutputStream out = response.getOutputStream();
//            File file = new File(iImageService.getById(id).getHeadPortrait());
//            fis = new FileInputStream(file);
//            byte[] b = new byte[fis.available()];
//            fis.read(b);
            String path=iImageService.getById(id).getHeadPortrait();
            if(path!=null) {
                BufferedImage image = ImageIO.read(new FileInputStream(path));
                response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
                OutputStream os = response.getOutputStream(); //获取文件输出流
                ImageIO.write(image, "jpg", os);//输出图片流
//            os.flush();
//            os.close();//关闭流

//            out.write(b);
//            out.flush();
            }


        } catch (Exception e) {
            recordLog.read(e);
            e.printStackTrace();
        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    recordLog.read(e);
//                }
//            }
        }
    }
    @ApiOperation("根据id查询图片")
    @GetMapping("/geiId/{Id}")
    public CommonResult geiId(@PathVariable long Id)
    {
        return CommonResult.success(iImageService.getById(Id).getHeadPortrait());
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

    @ApiOperation("根据id查询图片")
    @GetMapping(value = "/captcha")
    public void imagecode(long id, HttpServletResponse response) throws Exception {
        JSONObject object = new JSONObject();
//        CaptchaGenerator vcg = new CaptchaGenerator();
//        String vcode = vcg.generatorVCode();
//        BufferedImage vcodeImage = vcg.generatorVCodeImage(vcode, true);
        BufferedImage vcodeImage = ImageIO.read(new FileInputStream(iImageService.getById(id).getHeadPortrait()));
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        response.addHeader("code", vcode.toLowerCase());
        try {
            ImageIO.write(vcodeImage, "png", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encodeBuffer(outputStream.toByteArray()).trim();
            base64 = base64.replaceAll("\n", "").replaceAll("\r", "");
            object.put("code", "data:image/jpg;base64," + base64);
            response.getWriter().write(object.toString());
        } catch (IOException e) {
            response.getWriter().write("");
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
                response.getWriter().close();
            }
        }

    }
}
