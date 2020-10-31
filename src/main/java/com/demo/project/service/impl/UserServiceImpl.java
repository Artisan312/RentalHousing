package com.demo.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.entity.User;
import com.demo.project.mapper.UserMapper;
import com.demo.project.service.IUserService;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author com
* @since 2020-10-31
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
