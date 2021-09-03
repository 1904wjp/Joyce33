package com.moon.joyce.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.joyce.example.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Joyce
 * @since 2021-09-01
 */
public interface UserService extends IService<User> {
    /**
     * 根据条件查询user
     * @param user
     * @return
     */
    int getUserCount(User user,String type);
    /**
     * 根据条件查询user
     * @param user
     * @return
     */
    User getUser(User user,String type);


    /**
     * 根据条件获取user list数据
     * @param user
     * @return
     */
    List<User> getListUser(User user);

    /**
     * 根据条件查询user
     * @param user
     * @return
     */
    int updateUser(User newUser,User user,String type);

}
