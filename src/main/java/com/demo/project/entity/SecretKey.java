package com.demo.project.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import java.util.Date;

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
* @since 2020-12-01
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Secret_key")
@ApiModel(value="SecretKey对象", description="")
public class SecretKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    @ApiModelProperty(value = "秘钥")
    private String valueKey;

    @ApiModelProperty(value = "生产时间")
    private Date creatTime;

    @ApiModelProperty(value = "验证码")
    private String validatecode;

    @ApiModelProperty(value = "登录状态（未验证通过：0；已验证通过：1）")
    private int state;


}