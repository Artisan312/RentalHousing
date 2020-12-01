package com.demo.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.config.ApplicationContextProvider;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.User;
import com.demo.project.entity.UserToken;
import com.demo.project.service.IUserService;
import com.demo.project.service.IUserTokenService;
import com.demo.utils.DateUtils;
import com.demo.utils.MD5Utils;
import com.demo.utils.RecordLog;
import com.demo.utils.StringUtils;
import com.demo.utils2.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/app/user")
@Api(tags = "用户管理")
public class UserController {
    /**
     * 微信小程序id
     */
    private static String appID = "wx9ea9e8e0078b60f0";
    private static String appSecret = "6902cfc78b04836219d930ebf3711582";
    private static RecordLog recordLog;
    public UserController()
    {
        this.recordLog= ApplicationContextProvider.getBean(RecordLog.class);
    }

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserTokenService iUserTokenService;


    @ApiOperation("通过id查询用户")
    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable long id){
        try{
            return CommonResult.success(userService.getById(id));
        }catch (Exception e) {
            recordLog.read(e);
            e.printStackTrace();
            return CommonResult.failed("未知错误");
        }
    }

    @ApiOperation("根据id更新")
    @PutMapping("/updateById")
    public CommonResult updateById(@RequestBody User user){
        try{
            return CommonResult.success(userService.updateById(user));
        }catch (Exception e) {
            recordLog.read(e);
            e.printStackTrace();
            return CommonResult.failed("未知错误");
        }
    }
    @ApiOperation("登录状态")
    @GetMapping("/LoginStatus/{id}")
    public CommonResult LoginStatus(@PathVariable long id){
        try{
            userService.updateById(userService.getById(id).setState(1));
            return CommonResult.success(true);
        }catch (Exception e) {
            recordLog.read(e);
            e.printStackTrace();
            return CommonResult.failed("未知错误");
        }
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult userLogin(@RequestParam String code) {
        try {//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            String reslut= HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + appID + "&secret="
                            + appSecret + "&js_code="
                            + code
                            + "&grant_type=authorization_code",null
            );
            JSONObject jsonObject =JSONObject.parseObject(reslut);
            if (userService.getopenId(jsonObject.getString("openid")) != null){
                return CommonResult.success(userService.getopenId(jsonObject.getString("openid")));
            }
            else{
                User user = new User();
                user.setOpenId(jsonObject.getString("openid"));
                user.setCreatTime(DateUtils.getNowDate());
                userService.save(user);
                return CommonResult.success(userService.getopenId(jsonObject.getString("openid")));

            }
        } catch (Exception e) {
            recordLog.read(e);
            e.printStackTrace();
            return CommonResult.failed();
        }
    }

    @ApiOperation("更改密码")
    @PostMapping("/updatePwd")
    public CommonResult updatePwd(@RequestBody User user,@RequestParam("pwd") String pwd){
        try{
            if(user.getUserId() != 0||!StringUtils.isEmpty(pwd) ){
                UserToken userToken = iUserTokenService.getById(iUserTokenService.getId(user.getUserId()));
                if (userToken != null) {
                    pwd = iUserTokenService.judgePwd(userToken, pwd);
                    if (pwd != null) {
                        userToken.setPwd(pwd);
                        iUserTokenService.updateById(userToken);
                        return CommonResult.success("成功");
                    } else
                        return CommonResult.failed("未知错误");
                } else
                    return CommonResult.failed("没有设置密码");
            }
            else
                return CommonResult.failed("数据错误");
        }catch (Exception e) {
            recordLog.read(e);
            e.printStackTrace();
            return CommonResult.failed("未知错误");
        }
    }
    @ApiOperation("设置密码")
    @PostMapping("/putPwd")
    public CommonResult putPwd(@RequestParam("userId") long userId,@RequestParam("pwd") String pwd)
    {
        try {
            if(userId != 0||!StringUtils.isEmpty(pwd))
            {
                UserToken userToken=new UserToken();
                userToken.setUserId(userId);
                userToken.setSalt(MD5Utils.salt());
                userToken.setPwd(MD5Utils.string2MD5(pwd,userToken.getSalt()));
                iUserTokenService.save(userToken);
                return CommonResult.success("成功");
            }
            else
                return CommonResult.failed("数据错误");
        }catch (Exception e)
        {
            recordLog.read(e);
            e.printStackTrace();
            e.printStackTrace();
            return CommonResult.failed("未知错误");
        }

    }
}
