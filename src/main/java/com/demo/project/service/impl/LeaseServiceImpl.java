package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.Lease;
import com.demo.project.entity.User;
import com.demo.project.mapper.LeaseMapper;
import com.demo.project.service.ILeaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public class LeaseServiceImpl extends ServiceImpl<LeaseMapper, Lease> implements ILeaseService {

    @Override
    public List<Lease> getRoomId(long roomId) {
        QueryWrapper<Lease> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Lease::getRoomId, roomId);
        return getBaseMapper().selectList(queryWrapper);
    }
}
