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
* @since 2020-11-01
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_gateway")
@ApiModel(value="Gateway对象", description="")
public class Gateway implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "网关id")
    @TableId(value = "gateway_id", type = IdType.AUTO)
    private long gatewayId;

    @ApiModelProperty(value = "房id")
    private long roomId;

    @ApiModelProperty(value = "订阅主题，获取传感器数据")
    private long subscribe_topics;

    @ApiModelProperty(value = "发布主题，控制设备")
    private long publishing_topics;

    @ApiModelProperty(value = "时间")
    private Date creatTime;

    @ApiModelProperty(value = "设备状态（正常：0，异常：1）")
    private Integer state;


}