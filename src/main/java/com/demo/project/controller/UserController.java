package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.entity.User;
import com.demo.project.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
@Log4j2
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("list")
    public CommonResult list(){
        try{
            return CommonResult.success(userService.list());
        }catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("未知错误");
        }
    }

    @ApiOperation("通过id查询用户")
    @GetMapping("get")
    public CommonResult get(long id){
        try{
            return CommonResult.success(userService.getById(id));
        }catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("未知错误");
        }
    }

    @ApiOperation("新增用户")
    @PostMapping("insertUser")
    public CommonResult insertUser(User user){
        try{
            return CommonResult.success(userService.save(user));
        }catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("未知错误");
        }
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public CommonResult login(User user){
        try{
            user= userService.getopenId(user.getOpenId());
            if(user==null)
                return CommonResult.success(userService.save(user));
            else
                return  CommonResult.success(user);
        }catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("未知错误");
        }

    }

    @ApiOperation("根据id更新")
    @PostMapping("updateById")
    public CommonResult updateById(User user){
        try{
            return CommonResult.success(userService.updateById(user));
        }catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("未知错误");
        }
    }

}
