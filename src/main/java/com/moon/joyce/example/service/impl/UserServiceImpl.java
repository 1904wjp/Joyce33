package com.moon.joyce.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.joyce.commons.contracts.Contract;
import com.moon.joyce.example.entity.User;
import com.moon.joyce.example.mapper.UserMapper;
import com.moon.joyce.example.service.MailService;
import com.moon.joyce.example.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public int getUserCount(User user, String type) {
        QueryWrapper wrapper = new QueryWrapper();
        if (null==user){
            return -1;
        }

        /**
         * 该用户名是否存在
         */
        if (type.equals(Contract.USER_TYPE_UNIQUE_USERNAME)){
            if (Objects.isNull(user.getUsername())||StringUtils.isEmpty(user.getUsername().trim())){
                return -1;
            }
        }

        /**
         *  验证手机号码是否存在
         */
        if (type.equals(Contract.USER_TYPE_UNIQUE_PHONE)){
            if (Objects.isNull(user.getPhone())||StringUtils.isEmpty(user.getPhone().trim())){
                return -1;
            }
        }

        /**
         *  验证邮件是否存在
         */
        if (type.equals(Contract.USER_TYPE_UNIQUE_EMAIL)){
            if (Objects.isNull(user.getEmail())||StringUtils.isEmpty(user.getEmail().trim())){
                return -1;
            }
        }

        /**
         * 该用户名状态激活码是否存在
         */
        if (type.equals(Contract.USER_TYPE_UNIQUE_STATUS_CODE)){
            if (Objects.isNull(user.getStatusCode())||StringUtils.isEmpty(user.getStatusCode().trim())){
                return -1;
            }
        }

        if (Objects.nonNull(user.getUsername())&&StringUtils.isNotEmpty(user.getUsername().trim())){
            wrapper.eq("username",user.getUsername());
        }
        if (Objects.nonNull(user.getPassword())&&StringUtils.isNotEmpty(user.getPassword().trim())){
            wrapper.eq("password",user.getPassword());
        }
        if (Objects.nonNull(user.getEmail())&&StringUtils.isNotEmpty(user.getEmail().trim())){
            wrapper.eq("email",user.getEmail());
        }
        if (Objects.nonNull(user.getPhone())&&StringUtils.isNotEmpty(user.getPhone().trim())){
            wrapper.eq("phone",user.getPhone());
        }
        if (Objects.nonNull(user.getSecondarPassword())&&StringUtils.isNotEmpty(user.getSecondarPassword().trim())){
            wrapper.eq("secondar_password",user.getSecondarPassword());
        }
        if (Objects.nonNull(user.getStatusCode())&&StringUtils.isNotEmpty(user.getStatusCode().trim())){
            wrapper.eq("status_code",user.getStatusCode());
        }

        return baseMapper.selectCount(wrapper);

    }

    @Override
    public User getUser(User user,String type) {
        QueryWrapper wrapper = new QueryWrapper();
        if (null==user){
            return null;
        }

        /**
         * 登录
         */
        if (type.equals(Contract.USER_TYPE_LOGIN)){
            if (Objects.isNull(user.getUsername())||Objects.isNull(user.getPassword())||StringUtils.isEmpty(user.getUsername().trim())||StringUtils.isEmpty(user.getPassword().trim())){
                return null;
            }
                wrapper.ne("status",Contract.USER_TYPE_INVAILD_STATUS);

        }
        /**
  /*       * 该用户名是否存在
         *//*
        if (type.equals(Contract.USER_TYPE_UNIQUE_USERNAME)){
           if (Objects.isNull(user.getUsername())||StringUtils.isEmpty(user.getUsername().trim())){
               return null;
           }
               wrapper.ne("status",Contract.USER_TYPE_INVAILD_STATUS);

        }*/
//        /**
//         *  验证手机号码是否存在
//         */
//        if (type.equals(Contract.USER_TYPE_UNIQUE_PHONE)){
//            if (Objects.isNull(user.getPhone())||StringUtils.isEmpty(user.getPhone().trim())){
//                return null;
//            }
//
//        }
//        /**
//         *  验证邮件是否存在
//         */
//        if (type.equals(Contract.USER_TYPE_UNIQUE_EMAIL)){
//            if (Objects.isNull(user.getEmail())||StringUtils.isEmpty(user.getEmail().trim())){
//                return null;
//            }
//        }
//        /**
//         * 该用户名状态激活码是否存在
//         */
//        if (type.equals(Contract.USER_TYPE_UNIQUE_STATUS_CODE)){
//            if (Objects.isNull(user.getStatusCode())||StringUtils.isEmpty(user.getStatusCode().trim())){
//                return null;
//            }
//        }

        if (Objects.nonNull(user.getUsername())&&StringUtils.isNotEmpty(user.getUsername().trim())){
            wrapper.eq("username",user.getUsername());
        }
        if (Objects.nonNull(user.getPassword())&&StringUtils.isNotEmpty(user.getPassword().trim())){
            wrapper.eq("password",user.getPassword());
        }
        if (Objects.nonNull(user.getEmail())&&StringUtils.isNotEmpty(user.getEmail().trim())){
            wrapper.eq("email",user.getEmail());
        }
        if (Objects.nonNull(user.getPhone())&&StringUtils.isNotEmpty(user.getPhone().trim())){
            wrapper.eq("phone",user.getPhone());
        }
        if (Objects.nonNull(user.getSecondarPassword())&&StringUtils.isNotEmpty(user.getSecondarPassword().trim())){
            wrapper.eq("secondar_password",user.getSecondarPassword());
        }
        if (Objects.nonNull(user.getStatusCode())&&StringUtils.isNotEmpty(user.getStatusCode().trim())){
            wrapper.eq("status_code",user.getStatusCode());
        }

        return baseMapper.selectOne(wrapper);
    }


    @Override
    public List<User> getListUser(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        if (null!=user.getUsername()&&StringUtils.isNotEmpty(user.getUsername().trim())){
            wrapper.eq("username",user.getUsername());
        }
        wrapper.ne("status",Contract.USER_TYPE_INVAILD_STATUS);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public int updateUser(User newUser,User user, String type) {

        /**
         * 该用户名状态是否存在
         */
        if (Objects.isNull(newUser.getStatus())){
            return -1;
        }

        /**
         * 激活用户
         */
        if (type.equals(Contract.USER_TYPE_UP_VAILD_STATUS)){ ;
            newUser.setStatus(Contract.USER_TYPE_VAILD_STATUS);
        }

        /**
         * 升级vip
         */
        if (type.equals(Contract.USER_TYPE_UP_VIP_STATUS)){
            newUser.setStatus(Contract.USER_TYPE_VIP_STATUS);
        }

        /**
         * 修改密码
         */
        if (type.equals(Contract.USER_TYPE_PASSWORD)){
            if (Objects.isNull(newUser.getPassword())||StringUtils.isEmpty(newUser.getPassword().trim())){
                return -1;
            }
        }

        /**
         * 更改户名
         */
        if (type.equals(Contract.USER_TYPE_UNIQUE_USERNAME)){
            if (Objects.isNull(newUser.getUsername())||StringUtils.isEmpty(newUser.getUsername().trim())){
                return -1;
            }
        }
        /**
         *  更改号码
         */
        if (type.equals(Contract.USER_TYPE_UNIQUE_PHONE)){
            if (Objects.isNull(newUser.getPhone())||StringUtils.isEmpty(newUser.getPhone().trim())){
                return -1;
            }
        }
        /**
         *  更改邮件
         */
        if (type.equals(Contract.USER_TYPE_UNIQUE_EMAIL)){
            if (Objects.isNull(newUser.getEmail())||StringUtils.isEmpty(newUser.getEmail().trim())){
                return -1;
            }
        }
        UpdateWrapper wrapper = new UpdateWrapper();
        if (Objects.nonNull(user.getUsername())&&StringUtils.isNotEmpty(user.getUsername().trim())){
            wrapper.eq("username",user.getUsername());
        }
        if (Objects.nonNull(user.getPassword())&&StringUtils.isNotEmpty(user.getPassword().trim())){
            wrapper.eq("password",user.getPassword());
        }
        if (Objects.nonNull(user.getEmail())&&StringUtils.isNotEmpty(user.getEmail().trim())){
            wrapper.eq("email",user.getEmail());
        }
        if (Objects.nonNull(user.getPhone())&&StringUtils.isNotEmpty(user.getPhone().trim())){
            wrapper.eq("phone",user.getPhone());
        }
        if (Objects.nonNull(user.getSecondarPassword())&&StringUtils.isNotEmpty(user.getSecondarPassword().trim())){
            wrapper.eq("secondar_password",user.getSecondarPassword());
        }
        if (Objects.nonNull(user.getStatus())){
            wrapper.eq("status",user.getStatus());
        }
        if (Objects.isNull(user.getId())){
            wrapper.eq("id",user.getId());
        }

        return baseMapper.update(newUser, wrapper);
    }


}
