package com.demo.project.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("t_facility")
@ApiModel(value="Facility对象", description="")
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备id")
    @TableId(value = "facility_id", type = IdType.AUTO)
    private Integer facilityId;

    @ApiModelProperty(value = "网关id")
    private Integer gatewayId;

    @ApiModelProperty(value = "设备类型")
    private String type;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatTime;

    @ApiModelProperty(value = "设备状态（正常：0，异常：1）")
    private Integer state;


}