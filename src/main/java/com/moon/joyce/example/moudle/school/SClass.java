package com.moon.joyce.example.moudle.school;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moon.joyce.example.entity.User;
import com.moon.joyce.example.entity.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Xing Dao Rong
 * @date 2021/10/21 15:25
 * @desc 学校班级
 */
@Entity
@TableName("s_class")
@Table(name = "s_class")
@org.hibernate.annotations.Table(appliesTo = "s_class",comment = "班级表")
public class SClass extends BaseEntity {
    private static final long serialVersionUID = -3183709643984270928L;
    @TableField("class_grande")
    @Column(name = "class_grande",columnDefinition = "varchar(32) COMMENT '年级'")
    private String classGrande;

    @TableField("class_code")
    @Column(name = "class_code",columnDefinition = "varchar(32) COMMENT '班级编码'")
    private String classCode;

    @TableField("class_principal_id")
    @Column(name = "class_principal_id",columnDefinition = "bigint(32) COMMENT '班级负责人id'")
    private Long classPrincipalId;

    @TableField("class_principal_name")
    @Column(name = "class_principal_name",columnDefinition = "varchar(32) COMMENT '班级负责人姓名'")
    private  String classPrincipalName;

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

    public String getClassGrande() {
        return classGrande;
    }

    public void setClassGrande(String classGrande) {
        this.classGrande = classGrande;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Long getClassPrincipalId() {
        return classPrincipalId;
    }

    public void setClassPrincipalId(Long classPrincipalId) {
        this.classPrincipalId = classPrincipalId;
    }

    public String getClassPrincipalName() {
        return classPrincipalName;
    }

    public void setClassPrincipalName(String classPrincipalName) {
        this.classPrincipalName = classPrincipalName;
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
