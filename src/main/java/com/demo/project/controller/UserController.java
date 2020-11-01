package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.entity.User;
import com.demo.project.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        return CommonResult.success(userService.list());
    }

    @ApiOperation("查询用户")
    @GetMapping("get")
    public CommonResult get(Integer id){
        return CommonResult.success(userService.getById(id));
    }

    @ApiOperation("新增用户")
    @GetMapping("insertUser")
    public CommonResult insertUser(User user){
        return CommonResult.success(userService.save(user));
    }

    @ApiOperation("登录")
    @GetMapping("login")
    public CommonResult login(User user){
        user= userService.getopenId(user.getOpenId());
        if(user==null)
            return CommonResult.success(userService.save(user));
        else
            return  CommonResult.success(user);
    }

    @ApiOperation("根据id更新")
    @GetMapping("updateById")
    public CommonResult updateById(User user){
        return CommonResult.success(userService.updateById(user));
    }

}
