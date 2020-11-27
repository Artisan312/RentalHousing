package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.Sensor;
import com.demo.project.entity.User;
import com.demo.project.mapper.SensorMapper;
import com.demo.project.service.ISensorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author com
* @since 2020-11-01
*/
@Service
    public class SensorServiceImpl extends ServiceImpl<SensorMapper, Sensor> implements ISensorService {

    public Map<Date,Object> record(long facilityId)
    {
        QueryWrapper<Sensor> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Sensor::getFacilityId,facilityId );
        List<Sensor> list=getBaseMapper().selectList(queryWrapper);
        Map<Date,Object> map=new HashMap<>();
        for(Sensor sensor:list)
        {
            map.put(sensor.getTime(),sensor.getValue());
        }
        return map;
    }

    }
