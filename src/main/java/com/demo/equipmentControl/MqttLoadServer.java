package com.demo.equipmentControl;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MqttLoadServer implements ServletContextListener {
    @Autowired
    public static ClientMQTT mqttReceiveTest;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        mqttReceiveTest.stop();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        mqttReceiveTest.start();
    }
}
