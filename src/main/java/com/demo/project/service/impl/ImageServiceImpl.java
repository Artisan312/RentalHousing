package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.Image;
import com.demo.project.entity.User;
import com.demo.project.mapper.ImageMapper;
import com.demo.project.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author com
* @since 2020-11-01
*/
@Service
    public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {
    /**
     * 根据openId查询User
     * @param  roomId 房间id
     * @return
     */
    @Override
    public QueryWrapper<Image>  getroomId(long roomId) {
        QueryWrapper<Image> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Image::getRoomId, roomId);
        return queryWrapper;
    }

    }
