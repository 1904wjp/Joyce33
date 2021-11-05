package com.moon.joyce.example.moudle.school;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moon.joyce.example.entity.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xing Dao Rong
 * @date 2021/10/21 14:55
 * @desc 角色
 */
@Entity
@TableName("s_role")
@Table(name = "s_role")
@org.hibernate.annotations.Table(appliesTo = "s_role",comment = "学校角色表")
public class SRole extends BaseEntity {
    private static final long serialVersionUID = -8697920897603785641L;
    @Column(name = "s_role_name",columnDefinition = "varchar(12) COMMENT '学校角色名称'")
    private String SRoleName;

    public String getSRoleName() {
        return SRoleName;
    }

    public void setSRoleName(String SRoleName) {
        this.SRoleName = SRoleName;
    }
}
