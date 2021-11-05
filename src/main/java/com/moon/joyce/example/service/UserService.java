package com.moon.joyce.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.joyce.example.entity.User;

import java.util.List;
import java.util.Map;

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
     * 先分页查询后对结果排序
     * @param params
     * @return
     */
    List<User>  getUserList(User user);



    /**
     * 统计所有数据数量
     * @param params
     * @return
     */
    int getUsersCount();



    /**
     * 根据条件查询user
     * @param user
     * @return
     */
    int updateUser(User dbUser,User user,String type);

}
