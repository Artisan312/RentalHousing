package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.project.entity.Landlord;
import com.demo.project.entity.User;
import com.demo.project.mapper.LandlordMapper;
import com.demo.project.service.ILandlordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.utils.MD5Utils;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author com
* @since 2020-11-01
*/
@Service
public class LandlordServiceImpl extends ServiceImpl<LandlordMapper, Landlord> implements ILandlordService {

    @Override
    public Landlord login(Landlord landlord) {
        Landlord LandLord;
        if(landlord.getUsername()==null||"".equals(landlord.getUsername()))
            LandLord=this.selectByPhone(landlord.getPhone());
        else
            LandLord=this.selectByUsername(landlord.getUsername());
        if(LandLord.getPwd()== MD5Utils.string2MD5(landlord.getPwd(),LandLord.getSalt()))
            return LandLord;
        else
            return null;
    }
    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    @Override
    public Landlord selectByPhone(String phone) {
        QueryWrapper<Landlord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Landlord::getPhone, phone);
        return getBaseMapper().selectOne(queryWrapper);
    }

    @Override
    public Landlord selectByUsername(String phone) {
        QueryWrapper<Landlord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Landlord::getUsername, phone);
        return getBaseMapper().selectOne(queryWrapper);
    }

}
