package com.moon.joyce.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moon.joyce.example.entity.dto.Page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * <p>
 * 
 * </p>
 *
 * @author Joyce
 * @since 2021-10-08
 */
@Entity
@TableName("sys_menu")
@Table(name = "sys_menu")
@org.hibernate.annotations.Table(appliesTo = "sys_menu",comment = "菜单表")
public class SysMenu extends Page {

    private static final long serialVersionUID=1L;

    @TableField("menu_name")
    @Column(name = "menu_name",columnDefinition = "varchar(12) COMMENT '菜单名称'")
    private String menuName;

    @TableField("menu_url")
    @Column(name = "menu_url",columnDefinition = "varchar(12) COMMENT '菜单地址'")
    private String menuUrl;

    @TableField("menu_order")
    @Column(name = "menu_order",columnDefinition = "bigint(12) COMMENT '菜单排序'")
    private Long menuOrder;

    @TableField("parent_id")
    @Column(name = "parent_id",columnDefinition = "bigint(32) COMMENT '菜单父类id'")
    private Long parentId;

    @TableField("file_url")
    @Column(name = "file_url",columnDefinition = "varchar(32) COMMENT '文件地址'")
    private String fileUrl;


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Long getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Long menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
