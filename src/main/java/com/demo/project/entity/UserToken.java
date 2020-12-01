package com.demo.project.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
* 
* </p>
*
* @author com
* @since 2020-11-28
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("k_user_token")
@ApiModel(value="UserToken对象", description="")
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private long id;

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "加盐")
    private String salt;


}