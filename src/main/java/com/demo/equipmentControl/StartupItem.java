package com.demo.equipmentControl;

import org.springframework.beans.factory.annotation.Autowired;

public class StartupItem {
    @Autowired
    private static ClientMQTT clientMQTT;
    public StartupItem()
    {
        clientMQTT.start();
    }

}

