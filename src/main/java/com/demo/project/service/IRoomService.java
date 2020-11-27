package com.demo.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* <p>
 *  服务类
 * </p>
*
* @author com
* @since 2020-11-01
*/
 public interface IRoomService extends IService<Room> {
 /**
  *
  * 通过位置查询
  */
 List<Room> LocationQuery(String address);

}
