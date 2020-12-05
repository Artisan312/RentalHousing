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
 /**
  * 根据用户id查询
  * @param userid
  * @return
  */
 long getId(long userid);

 String judgePwd(UserToken userToken,String pwd);
}
