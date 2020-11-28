package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.Landlord;
import com.demo.project.entity.User;
import com.demo.project.entity.UserToken;
import com.demo.project.mapper.UserTokenMapper;
import com.demo.project.service.IUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.utils.MD5Utils;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author com
* @since 2020-11-28
*/
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements IUserTokenService {
    @Override
    public long getId(long userid) {
        QueryWrapper<UserToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserToken::getUserId, userid);
        return getBaseMapper().selectOne(queryWrapper).getId();
    }
}
