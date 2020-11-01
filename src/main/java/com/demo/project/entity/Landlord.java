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
* @since 2020-11-01
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_landlord")
@ApiModel(value="Landlord对象", description="")
public class Landlord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房东id")
    private Integer landlordId;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "时间")
    private LocalDateTime creatTime;

    @ApiModelProperty(value = "登录状态（未登录：0；已登录：1）")
    private Integer state;


}