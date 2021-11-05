package com.moon.joyce.example.moudle.school;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moon.joyce.commons.utils.SQLUtils;
import com.moon.joyce.example.entity.User;
import com.moon.joyce.example.entity.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Xing Dao Rong
 * @date 2021/10/21 15:03
 * @desc 学校
 */
@Entity
@TableName("school")
@Table(name = "school")
@org.hibernate.annotations.Table(appliesTo = "school",comment = "学校表")
public class School extends BaseEntity {
    private static final long serialVersionUID = 338384318719828919L;
    @TableField("school_name")
    @Column(name = "school_name",columnDefinition = "varchar(12) COMMENT '学校名称'" )
    private String schoolName;

    @TableField("img")
    @Column(name = "img",columnDefinition = "varchar(64) COMMENT '相片'" )
    private String img;

    @TableField("school_introduce")
    @Column(name = "school_introduce",columnDefinition = "varchar(255) COMMENT '学校资料'" )
    private String schoolIntroduce;

    @TableField("school_address")
    @Column(name = "school_address",columnDefinition = "varchar(255) COMMENT '学校地址'" )
    private String schoolAddress;

    @TableField("s_principal_id")
    @Column(name = "s_principal_id",columnDefinition = "bigint(32) COMMENT '学校负责人id'")
    private Long sPrincipalId;

    @TableField("s_principal_name")
    @Column(name = "s_principal_name",columnDefinition = "varchar(32) COMMENT '学校负责人姓名'")
    private  String sPrincipalName;

    /**
     * 学生集合
     */
    @Transient
    @TableField(exist = false)
    private List<User> students;

    /**
     * 学校集合
     */
    @Transient
    @TableField(exist = false)
    private List<User> teachers;

    /**
     * 科目集合
     */
    @Transient
    @TableField(exist = false)
    private List<Subject> subjects;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSchoolIntroduce() {
        return schoolIntroduce;
    }

    public void setSchoolIntroduce(String schoolIntroduce) {
        this.schoolIntroduce = schoolIntroduce;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public Long getsPrincipalId() {
        return sPrincipalId;
    }

    public void setsPrincipalId(Long sPrincipalId) {
        this.sPrincipalId = sPrincipalId;
    }

    public String getsPrincipalName() {
        return sPrincipalName;
    }

    public void setsPrincipalName(String sPrincipalName) {
        this.sPrincipalName = sPrincipalName;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<User> teachers) {
        this.teachers = teachers;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
