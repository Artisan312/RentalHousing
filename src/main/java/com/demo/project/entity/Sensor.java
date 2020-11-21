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
@TableName("t_sensor")
@ApiModel(value="Sensor对象", description="")
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录id")
    @TableId(value = "sensor_id", type = IdType.AUTO)
    private long  sensorId;

    @ApiModelProperty(value = "设备id")
    private long  facilityId;

    @ApiModelProperty(value = "时间")
    private Date time;

    @ApiModelProperty(value = "值")
    private String value;


}