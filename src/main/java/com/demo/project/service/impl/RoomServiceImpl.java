package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.Room;
import com.demo.project.entity.User;
import com.demo.project.mapper.RoomMapper;
import com.demo.project.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author com
* @since 2020-11-01
*/
@Service
    public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    /**
     * 根据位置查询
     * @param address
     * @return
     */
    @Override
    public List<Room> LocationQuery(String address) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Room::getAddress,address);
        List<Room> list = getBaseMapper().selectList(queryWrapper);
        return list;
    }
}
