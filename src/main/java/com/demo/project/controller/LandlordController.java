package com.demo.project.controller;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.Landlord;
import com.demo.project.entity.UserToken;
import com.demo.project.service.IImageService;
import com.demo.project.service.ILandlordService;
import com.demo.utils.DateUtils;
import com.demo.utils.RecordLog;
import com.demo.utils.VerifyCode;
import com.demo.utils.myUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
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

//    @ApiOperation("查询所有房东")
//    @GetMapping("/list")
//    public CommonResult list(){
//        return CommonResult.success(iLandlordService.list());
//    }

    @ApiOperation("新增房东")
    @GetMapping("/insertLandlord")
    public CommonResult insertLandlord(HttpServletRequest request,@RequestBody Landlord landlord){
        try {
            if(((boolean)request.getSession().getAttribute("Code")==true)
            &&(landlord.getPhone() != null && !"".equals(landlord.getPhone()))
            &&(landlord.getPwd() != null && !"".equals(landlord.getPwd()))
            &&(landlord.getName() != null && !"".equals(landlord.getName()))
            )
            {

                landlord.setCreatTime(DateUtils.getNowDate());

            }
        }catch (Exception e)
        {
            recordLog.read(e);
            e.printStackTrace();
        }


        return CommonResult.success(iLandlordService.save(landlord));
    }
    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult login(HttpServletRequest request,@RequestBody Landlord landlord){
        try{
            if((boolean)request.getSession().getAttribute("Code")==true) {
                if (((landlord.getPwd() != null && landlord.getPhone() != null) && (!"".equals(landlord.getPwd()) && !"".equals(landlord.getPhone())))
                        || ((landlord.getPwd() != null && landlord.getUsername() != null) && (!"".equals(landlord.getPwd()) && !"".equals(landlord.getUsername())))
                )
                    landlord = iLandlordService.login(landlord);
                else
                    return CommonResult.failed("输入错误");
            }
            else
            {
                return CommonResult.failed("未验证");
            }
           return  CommonResult.success(landlord);
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
       }
    }
    @ApiOperation("根据id更新")
    @PostMapping("/updateById")
    public CommonResult updateById(@RequestBody Landlord landlord){
        if(landlord.getPwd()==null || landlord.getSalt()==null || "".equals(landlord.getPwd())||"".equals(landlord.getSalt()))
            return CommonResult.success(iLandlordService.updateById(landlord));
        else
            return CommonResult.failed("数据错误");
    }
    @ApiOperation("更改密码")
    @GetMapping("/updatePwd")
    public CommonResult updatePwd(@RequestBody Landlord landlord, @RequestParam("pwd") String pwd){
        try{
            if(landlord.getPwd()!=null||landlord.getSalt()!=null)
                return CommonResult.success(iLandlordService.updateById(landlord));
            else
                return CommonResult.failed("数据错误");
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
        }
    }

    /* 获取验证码图片*/
    @ApiOperation("获取验证码")
    @GetMapping("/getValidateCode")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            int width=200;
            int height=69;
            BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            //生成对应宽高的初始图片
            String randomText = VerifyCode.drawRandomText(width,height,verifyImg);
            //单独的一个类方法，出于代码复用考虑，进行了封装。
            //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
            request.getSession().setAttribute("verifyCode", randomText);
            request.getSession().setAttribute("Code", false);
            response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = response.getOutputStream(); //获取文件输出流
            ImageIO.write(verifyImg,"png",os);//输出图片流
            os.flush();
            os.close();//关闭流

        } catch (IOException e) {
            recordLog.read(e);
            e.printStackTrace();

        }
    }

    @ApiOperation(value = "验证码校验")
    @GetMapping(value = "/checkValidateCode")
    public CommonResult checkValidateCode(HttpServletRequest request, @RequestParam("validateCode") String validateCode) {
        String sessionCode = request.getSession().getAttribute("verifyCode").toString();
        if (validateCode != null && !"".equals(validateCode) && sessionCode != null && !"".equals(sessionCode)) {
            if (validateCode.equalsIgnoreCase(sessionCode)) {
                request.getSession().setAttribute("Code", true);
                return CommonResult.success("验证通过！");
            } else {
                return CommonResult.failed("验证失败！");
            }
        } else {
            return CommonResult.failed("验证失败！");
        }
    }
}
