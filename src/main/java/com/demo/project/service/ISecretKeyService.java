package com.demo.project.service;

import com.demo.project.entity.SecretKey;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* <p>
 *  服务类
 * </p>
*
* @author com
* @since 2020-12-01
*/
 public interface ISecretKeyService extends IService<SecretKey> {

  SecretKey getValueKey(String key);

 }
