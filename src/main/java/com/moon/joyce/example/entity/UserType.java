package joyce.example.entity;

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
 * @since 2021-09-25
 */
@Entity
@TableName("user_type")
@Table(name = "user_type")
@org.hibernate.annotations.Table(appliesTo = "user_type",comment = "角色表")
public class UserType extends Page {
    private static final long serialVersionUID = 1573373319724501081L;
    /**
     * 身份
     */
    @TableField("id_code")
    @Column(name = "id_code",unique = true,columnDefinition = "varchar(64) COMMENT '角色名字'")
    private String idCode;

}
