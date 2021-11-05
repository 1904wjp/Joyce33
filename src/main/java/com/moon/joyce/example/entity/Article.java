package com.moon.joyce.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.moon.joyce.example.entity.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xing Dao Rong
 * @date 2021/10/22 16:47
 * @desc 文章编辑实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TableName("article")
@Table(name = "article")
@org.hibernate.annotations.Table(appliesTo = "article",comment = "文章编辑表")
public class Article extends BaseEntity {
    @Column(name = "author",columnDefinition = "varchar(12) COMMENT '作者名'")
    private String author;
    @Column(name = "title",columnDefinition = "varchar(64) COMMENT '标题'")
    private String title;
    @Column(name = "content",columnDefinition = "text COMMENT '文章的内容'")
    private String content;
    @Column(name = "pv_content",columnDefinition = "text COMMENT '上一版本文章的内容'")
    private String pvContent;
    @Column(name = "user_id",columnDefinition = "bigint(32) COMMENT '用户主键'")
    private Long userId;
    @Column(name = "class_id",columnDefinition = "bigint(32) COMMENT '分类主键'")
    private Long classId;
}