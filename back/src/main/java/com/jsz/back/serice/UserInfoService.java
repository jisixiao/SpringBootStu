package com.jsz.back.serice;

import com.jsz.back.common.base.service.BaseServiceImpl;
import com.jsz.back.dao.UserInfoMapper;
import com.jsz.back.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author jushangzhi@novacloud.com
 * @create 2018-05-25 20:50
 * @description
 **/
@Service
public class UserInfoService extends BaseServiceImpl<UserInfo> {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public Mapper<UserInfo> getMapper() {
        return userInfoMapper;
    }

    public UserInfo login(String userName, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setPassword(password);

        UserInfo info = userInfoMapper.selectOne(userInfo);
        return info;
    }

    public UserInfo getUserInfoByUserName(String userName){
        UserInfo info = new UserInfo();
        info.setUsername(userName);
        UserInfo userInfo = userInfoMapper.selectOne(info);
        return userInfo;
    }


}
