package com.demo.project.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import java.time.LocalDate;
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
@TableName("t_lease")
@ApiModel(value="Lease对象", description="")
public class Lease implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "出租房记录id")
    @TableId(value = "lease_id", type = IdType.AUTO)
    private Integer leaseId;

    @ApiModelProperty(value = "房id")
    private Integer roomId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatTime;

    @ApiModelProperty(value = "合同开始时间")
    private LocalDate startTime;

    @ApiModelProperty(value = "合同结束时间")
    private LocalDate expiryTimer;

    @ApiModelProperty(value = "合同")
    private Integer contractId;

    @ApiModelProperty(value = "状态（过期：1，未过期：0）")
    private Integer state;


}