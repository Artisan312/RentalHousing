package com.demo.project.service;

import com.demo.project.entity.Sensor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.Map;

/**
* <p>
 *  服务类
 * </p>
*
* @author com
* @since 2020-11-01
*/
 public interface ISensorService extends IService<Sensor> {
 Map<Date,Object> record(long facilityId);
 }
