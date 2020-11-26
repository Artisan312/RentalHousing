package com.demo.project.controller;

import com.demo.common.result.CommonResult;
import com.demo.project.entity.Room;
import com.demo.project.service.IRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
* <p>
*  前端控制器
* </p>
*
* @author com
* @since 2020-11-01
*/
@RestController
@RequestMapping("room")
@Api(tags = "房间管理")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @ApiOperation("查询所有房间")
    @GetMapping("list")
    public CommonResult list(){
        return CommonResult.success(roomService.list());
    }


    @ApiOperation("通过id查询")
    @GetMapping("get")
    public CommonResult get(long id ){
        return CommonResult.success(roomService.getById(id));
    }

    @ApiOperation("添加房间")
    @PostMapping("insertRoom")
    public CommonResult insertRoom(Room room){
        return CommonResult.success(roomService.save(room));
    }

    @ApiOperation("批量添加")
    @PostMapping("saveBatch")
    public CommonResult saveBatch(Collection<Room> rooms ){
        return CommonResult.success(roomService.saveBatch(rooms));
    }

    @ApiOperation("通过位置查询")
    @GetMapping("LocationQuery")
    public CommonResult LocationQuery(String address ){
        return CommonResult.success(roomService.LocationQuery(address));
    }
}
