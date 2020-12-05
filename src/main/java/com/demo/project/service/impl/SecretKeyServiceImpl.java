package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.SecretKey;
import com.demo.project.entity.UserToken;
import com.demo.project.mapper.SecretKeyMapper;
import com.demo.project.service.ISecretKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author com
* @since 2020-12-01
*/
@Service
    public class SecretKeyServiceImpl extends ServiceImpl<SecretKeyMapper, SecretKey> implements ISecretKeyService {

    @Override
    public SecretKey getValueKey(String key) {
        QueryWrapper<SecretKey> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SecretKey::getValueKey, key);
        return getBaseMapper().selectOne(queryWrapper);
    }

    @Override
    public SecretKey getValidateCode(String code) {
        QueryWrapper<SecretKey> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SecretKey::getValidatecode, code);
        return getBaseMapper().selectOne(queryWrapper);
    }
}
