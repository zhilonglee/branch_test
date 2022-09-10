package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhilong
 * @since 2022-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("actor")
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "actor_id", type = IdType.AUTO)
    private Integer actorId;

    private String firstName;

    private String lastName;

    private LocalDateTime lastUpdate;


}
