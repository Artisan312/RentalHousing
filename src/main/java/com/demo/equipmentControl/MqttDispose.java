package com.demo.equipmentControl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mqtt")
@Component
@Data
public class MqttDispose {
    private String HOST ;
    private String clientId ;
    private String userName ;
    private String passWord ;
    private int heartbeatTime;
    private int overTime;
}
