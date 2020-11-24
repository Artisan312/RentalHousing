package com.demo.equipmentControl;

import com.demo.utils.RecordLog;
import org.springframework.beans.factory.annotation.Autowired;

public class StartupItem {
    @Autowired
    private static RecordLog recordLog;
    @Autowired
    private static ClientMQTT clientMQTT;
    public StartupItem()
    {
        clientMQTT.start();
    }
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

