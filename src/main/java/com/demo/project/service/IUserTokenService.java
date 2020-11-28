package com.demo.project.service;

import com.demo.project.entity.UserToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* <p>
 *  服务类
 * </p>
*
* @author com
* @since 2020-11-28
*/
 public interface IUserTokenService extends IService<UserToken> {

 long getId(long userid);
}
