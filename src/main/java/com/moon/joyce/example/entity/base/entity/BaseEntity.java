package com.moon.joyce.example.entity.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Xing Dao Rong
 * @date 2021/9/3 13:55
 * @desc 基础类实体类
 */
@Entity
@Table(name = "base_entity")
@org.hibernate.annotations.Table(appliesTo = "base_entity",comment = "实体基础类")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 9019164396662157010L;
    @Id
    @TableId(value = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "bigint(32) COMMENT '主键'")
    private Long id;

    @TableField("create_by")
    @Column(name= "create_by",columnDefinition = "varchar(64) COMMENT '创建者'")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @Column(name= "create_time",columnDefinition = "varchar(64) COMMENT '创建时间'")
 /*   @Temporal(TemporalType.TIMESTAMP)*/
    private Date createTime;

    @TableField("update_by")
    @Column(name= "update_by",columnDefinition = "varchar(64) COMMENT '更新者'")
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @Column(name= "update_time",columnDefinition = "varchar(64) COMMENT '更新时间'" )
 /*   @Temporal(TemporalType.TIMESTAMP)*/
    private Date updateTime;

    @TableLogic
    @TableField(value = "delete_flag")
    @Column(name= "delete_flag",columnDefinition = "varchar(64) COMMENT '删除标志'" )
    private Integer deleteFlag;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
