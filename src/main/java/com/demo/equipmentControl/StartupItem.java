package com.demo.equipmentControl;

import com.demo.project.service.IGatewayService;
import com.demo.utils.RecordLog;
import org.springframework.beans.factory.annotation.Autowired;

public class StartupItem {
    @Autowired
    public static RecordLog recordLog;
    @Autowired
    private static ClientMQTT clientMQTT;
    public StartupItem()
    {
        clientMQTT.start();
    }
    @Autowired
    private IGatewayService iGatewayService;

    public boolean publish()
    {
        try {
            //clientMQTT.publish();
        }catch (Exception e) {
            recordLog.read(e);
        }
        return false;
    }


}

