package com.moon.joyce.example.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moon.joyce.commons.base.entity.BaseEntity;
import com.moon.joyce.commons.utils.entity.Page;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Joyce
 * @since 2021-09-01
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class User extends Page {

    private static final long serialVersionUID = 8843327699543286578L;
    private String username;
    private String password;
    private String phone;
    private String email;
    @TableField("secondar_password")
    private String secondarPassword;

    private Integer status;
    @TableField(value = "status_code")
    private String statusCode;
    @TableField(value = "file_url")
    private String fileUrl;
    @TableField(value = "user_type_id")
    private Long UserTypeId;
}
