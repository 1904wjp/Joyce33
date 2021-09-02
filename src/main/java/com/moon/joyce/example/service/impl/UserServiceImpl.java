package com.moon.joyce.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.joyce.commons.contracts.Contract;
import com.moon.joyce.example.entity.User;
import com.moon.joyce.example.mapper.UserMapper;
import com.moon.joyce.example.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joyce
 * @since 2021-09-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUser(User user,String type) {
        if (null==user){
            return null;
        }
        if (type.equals(Contract.SELECT_USER_TYPE_LOGIN)){
            if (StringUtils.isNotEmpty(user.getUsername().trim())&&StringUtils.isNotEmpty(user.getPassword().trim())){
                QueryWrapper wrapper = new QueryWrapper();
                wrapper.eq("username",user.getUsername());
                wrapper.eq("password",user.getPassword());
                return baseMapper.selectOne(wrapper);
            }
        }
        if (type.equals(Contract.SELECT_USER_TYPE_UNIQUE_USERNAME)){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("username",user.getUsername());
            return baseMapper.selectOne(wrapper);
        }
        return null;
    }


    @Override
    public List<User> getListUser(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        if (null!=user.getUsername()&&StringUtils.isNotEmpty(user.getUsername().trim())){
            wrapper.eq("username",user.getUsername());
        }
        return baseMapper.selectList(wrapper);
    }



}
