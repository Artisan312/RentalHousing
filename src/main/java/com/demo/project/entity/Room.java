package com.demo.project.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import com.baomidou.mybatisplus.annotation.TableField;
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
* @since 2020-11-01
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_room")
@ApiModel(value="Room对象", description="")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房id")
    @TableId(value = "room_id", type = IdType.AUTO)
    private long roomId;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "房型")
    private String roomShape;

    @ApiModelProperty(value = "面积")
    private String shape;

    @ApiModelProperty(value = "租房名")
    private String roomName;

    @ApiModelProperty(value = "房东id")
    private Integer landlordId;

    @ApiModelProperty(value = "网关id")
    private Integer gatewayId;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "时间")
    private Date creatTime;

    @ApiModelProperty(value = "出租id")
    private long leaseId;

    @ApiModelProperty(value = "首页图片id")
     @TableField("Image_id")
    private long imageId;

    @ApiModelProperty(value = "是否出租（是：1，否：0）")
    private Integer state;


}