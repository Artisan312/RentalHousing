package com.demo.project.service;

import com.demo.project.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* <p>
 *  服务类
 * </p>
*
* @author com
* @since 2020-10-31
*/
 public interface IUserService extends IService<User> {

 User getopenId(String openId);
 }
