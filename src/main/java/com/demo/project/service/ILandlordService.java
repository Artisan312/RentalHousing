package com.demo.project.service;

import com.demo.project.entity.Landlord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
* <p>
 *  服务类
 * </p>
*
* @author com
* @since 2020-11-01
*/
 public interface ILandlordService extends IService<Landlord> {
 /**
  *
  * @param landlord
  * @return
  */
 Landlord login(Landlord landlord);

 /**
  *
  * @param phone
  * @return
  */
 Landlord selectByPhone(String phone);


}
