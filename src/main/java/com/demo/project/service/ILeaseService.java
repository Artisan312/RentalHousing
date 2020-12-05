package com.demo.project.service;

import com.demo.project.entity.Lease;
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
 public interface ILeaseService extends IService<Lease> {
 List<Lease> getRoomId(long roomId);

 }
