package com.demo.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.result.CommonResult;
import com.demo.project.entity.User;
import com.demo.project.service.IUserService;
import com.demo.utils.RecordLog;
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
    private String appID = "wx4bd8b4a9b1e3c26e";
    private String appSecret = "bfffc7550da959acf43dc02013585b0d";
    @Autowired
    private static RecordLog recordLog;

    @Autowired
    private IUserService userService;

//    @ApiOperation("查询所有用户")
//    @GetMapping("list")
//    public CommonResult list(){
//        try{
//            return CommonResult.success(userService.list());
//        }catch (Exception e) {
//            log.error(e.getMessage());
//            return CommonResult.failed("未知错误");
//        }
//    }

    @ApiOperation("通过id查询用户")
    @GetMapping("get/{id}")
    public CommonResult get(long id){
        try{
            return CommonResult.success(userService.getById(id));
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
        }
    }

//    @ApiOperation("新增用户")
//    @PostMapping("insertUser")
//    public CommonResult insertUser(User user){
//        try{
//            return CommonResult.success(userService.save(user));
//        }catch (Exception e) {
//            log.error(e.getMessage());
//            return CommonResult.failed("未知错误");
//        }
//    }

//    @ApiOperation("登录")
//    @PostMapping("login")
//    public CommonResult login(String code){
//        try{
//            user= userService.getopenId(user.getOpenId());
//            if(user==null)
//                return CommonResult.success(userService.save(user));
//            else
//                return  CommonResult.success(user.getUserId());
//        }catch (Exception e) {
//            log.error(e.getMessage());
//            return CommonResult.failed("未知错误");
//       }
//    }

    @ApiOperation("根据id更新")
    @PutMapping("updateById")
    public CommonResult updateById(@RequestBody User user){
        try{
            return CommonResult.success(userService.updateById(user));
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
        }
    }
    @ApiOperation("登录状态")
    @GetMapping("LoginStatus/{id}")
    public CommonResult LoginStatus(@PathVariable long id){
        try{
            userService.updateById(userService.getById(id).setState(1));
            return CommonResult.success(true);
        }catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed("未知错误");
        }
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public CommonResult userLogin(@RequestParam String code) {

        //判断第一个字符是否为“{”
        if (code.startsWith("{")) {
            code = code.substring(1);
        }

        //判断最后一个字符是否为“}”
        if (code.endsWith("}")) {
            code = code.substring(0, code.length() - 1);
        }

        String str1 = code.replaceAll("\"", "");
        String str2 = str1.replaceAll("code:", "");

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
                System.out.println(user);
                userService.save(user);
                return CommonResult.success(userService.getopenId(jsonObject.getString("openid")));
            }
        } catch (Exception e) {
            recordLog.read(e);
            return CommonResult.failed();
        }
    }


}
