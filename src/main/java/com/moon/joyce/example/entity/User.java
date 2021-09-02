package com.moon.joyce.example.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Joyce
 * @since 2021-09-01
 */
@Data
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    @TableField("secondar_password")
    private String secondarPassword;
    @TableField("create_by")
    private String createBy;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;
    @TableLogic
    @TableField(value = "delete_flag")
    private Integer deleteFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
