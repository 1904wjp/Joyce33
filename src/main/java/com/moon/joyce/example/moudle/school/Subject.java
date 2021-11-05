package com.moon.joyce.example.moudle.school;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moon.joyce.example.entity.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xing Dao Rong
 * @date 2021/10/21 15:33
 * @desc 科目
 */
@Entity
@TableName("s_subject")
@Table(name = "s_subject")
@org.hibernate.annotations.Table(appliesTo = "s_subject",comment = "科目表")
public class Subject extends BaseEntity {
    private static final long serialVersionUID = -7680807147324490178L;
    @TableField("subject_name")
    @Column(name = "subject_name",columnDefinition = "varchar(32) COMMENT '科目名字'")
    private String subjectName;
    @TableField("subject_code")
    @Column(name = "subject_code",columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String subjectCode;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
}

