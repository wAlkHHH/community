package com.walkhhh.community.service;

import com.walkhhh.community.mapper.UserMapper;
import com.walkhhh.community.model.User;
import com.walkhhh.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-11 22:49
 */
@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            User oldUser = users.get(0);
            User dbUser = new User();
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarurl(oldUser.getAvatarurl());
            dbUser.setName(oldUser.getName());
            dbUser.setToken(oldUser.getToken());
            userMapper.updateByExample(dbUser, userExample);
        }

    }
}
