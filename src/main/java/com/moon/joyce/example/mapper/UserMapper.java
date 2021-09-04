package com.moon.joyce.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.joyce.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Joyce
 * @since 2021-09-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> getUsers(User user);
    int getTatlo();
}
