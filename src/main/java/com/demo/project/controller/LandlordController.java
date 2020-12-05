package com.demo.project.controller;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.Landlord;
import com.demo.project.entity.SecretKey;
import com.demo.project.entity.UserToken;
import com.demo.project.service.IImageService;
import com.demo.project.service.ILandlordService;
import com.demo.project.service.ISecretKeyService;
import com.demo.utils.*;
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

    @Autowired
    private ISecretKeyService iSecretKeyService;

//    @ApiOperation("查询所有房东")
//    @GetMapping("/list")
//    public CommonResult list(){
//        return CommonResult.success(iLandlordService.list());
//    }

    @ApiOperation("新增房东")
    @PostMapping("/insertLandlord")
    @ResponseBody
    public CommonResult insertLandlord(@RequestBody Landlord landlord){//HttpServletRequest request,@RequestBody
        try {
            if(//((boolean)request.getSession().getAttribute("Code")==true)&&
             !StringUtils.isEmpty(landlord.getPhone())//(landlord.getPhone() != null || !"".equals(landlord.getPhone()))
            &&!StringUtils.isEmpty(landlord.getPwd())//(landlord.getPwd() != null || !"".equals(landlord.getPwd()))
            //&&!StringUtils.isEmpty(landlord.getName())
            )
            {
                landlord.setCreatTime(DateUtils.getNowDate());
                String salt=MD5Utils.salt();
                landlord.setSalt(salt);
                landlord.setPwd(MD5Utils.string2MD5(landlord.getPwd(),salt));
                return CommonResult.success(iLandlordService.save(landlord));
            }
        }catch (Exception e)
        {
            recordLog.read(e);
            e.printStackTrace();
        }
        return CommonResult.failed();
    }
    @ApiOperation("登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestParam("name")String name,@RequestParam("pwd")String pwd,@RequestParam("id")long id){//@RequestBody Landlord landlord
        Landlord landlord;
        try{
            if(id!=0&&StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(pwd)&& iSecretKeyService.getById(id).getState()!=0) {
                if (!StringUtils.isEmpty(name)||!StringUtils.isEmpty(pwd)) {
                    landlord = iLandlordService.login(name, pwd);
                    if (landlord == null)
                        return CommonResult.failed("用户名和密码错误");
                    else {
                        landlord.setPwd(null);
                        landlord.setSalt(null);
                        iSecretKeyService.removeById(id);
                        return CommonResult.success(landlord);
                    }
                }
                else
                    return CommonResult.failed("输入错误2");
            }
            else
            {
                return CommonResult.failed("未验证");
            }
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
       }
    }
    @ApiOperation("根据id更新")
    @PostMapping("/updateById")
    @ResponseBody
    public CommonResult updateById(@RequestBody Landlord landlord){
        if(landlord.getPwd()==null || landlord.getSalt()==null || "".equals(landlord.getPwd())||"".equals(landlord.getSalt()))
            return CommonResult.success(iLandlordService.updateById(landlord));
        else
            return CommonResult.failed("数据错误");
    }
    @ApiOperation("更改密码")
    @PostMapping("/updatePwd")
    public CommonResult updatePwd(@RequestParam("landlordId") long landlordId,@RequestParam("oldPwd") String oldPwd, @RequestParam("pwd") String pwd){
        try{
            if(StringUtils.isNotEmpty(oldPwd) && StringUtils.isNotEmpty(pwd)) {
                Landlord landlord=iLandlordService.getById(landlordId);
                if(landlord.getPwd()==MD5Utils.string2MD5(oldPwd,landlord.getSalt()))
                {
                    landlord.setPwd(MD5Utils.string2MD5(pwd,landlord.getSalt()));
                    return CommonResult.success(iLandlordService.updateById(landlord));
                }
                return CommonResult.failed("密码错误");
            }
            else
                return CommonResult.failed("数据错误");
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
        }
    }


    @ApiOperation("根据phone查询")
    @GetMapping("/selectByPhone")
    public CommonResult selectByPhone(@RequestParam("phone") String phone){
        try{
            if(!StringUtils.isEmpty(phone)) {
                Landlord landlord=iLandlordService.selectByPhone(phone);
                landlord.setSalt(null);
                landlord.setPwd(null);
                return CommonResult.success(landlord);
            }
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
            int height=80;
            BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            //生成对应宽高的初始图片
            String randomText = VerifyCode.drawRandomText(width,height,verifyImg);
            SecretKey secretKey=new SecretKey();
            secretKey.setCreatTime(DateUtils.getNowDate());
            secretKey.setValidatecode(randomText);
            String salt= MD5Utils.salt();
            secretKey.setValueKey(salt);
            iSecretKeyService.save(secretKey);
            //单独的一个类方法，出于代码复用考虑，进行了封装。
            //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
            request.getSession().setAttribute("verifyCode", salt);
            //request.getSession().setAttribute("Code", false);
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
        try {
        String essionCode=null;
        SecretKey secretKey;
        try {
             essionCode = request.getSession().getAttribute("verifyCode").toString();
        }catch (Exception e)
        {
            recordLog.read(e);
        }
        if (validateCode != null && !"".equals(validateCode) && essionCode != null && !"".equals(essionCode)) {
            secretKey=iSecretKeyService.getValueKey(essionCode);
            if (validateCode.equalsIgnoreCase(secretKey.getValidatecode())) {
                secretKey.setState(1);
                iSecretKeyService.updateById(secretKey);
                return CommonResult.success(secretKey);
            } else {
                return CommonResult.failed("验证失败！");
            }
        } else {
            if(!StringUtils.isEmpty(validateCode)) {
                secretKey = iSecretKeyService.getValidateCode(validateCode);
                if(secretKey!=null) {
                    secretKey.setState(1);
                    iSecretKeyService.updateById(secretKey);
                    return CommonResult.success(secretKey);
                }
            }
            return CommonResult.failed("验证失败！");
        }
        }catch (Exception e)
        {
            recordLog.read(e);
            e.printStackTrace();
            return CommonResult.failed("验证失败");
        }
    }

}
