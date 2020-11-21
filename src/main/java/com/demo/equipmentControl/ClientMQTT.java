package com.demo.equipmentControl;

import com.demo.common.config.ApplicationContextProvider;
import com.demo.utils.RecordLog;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

/**
 * MQTT连接
 */
@Component
public class ClientMQTT {

    @Autowired
    private static RecordLog recordLog;

    private static MqttDispose datasourcePro;
    private static MqttClient client;
    private static MqttConnectOptions options;

//    @SuppressWarnings("unused")
//    private ScheduledExecutorService scheduler;

    /**
     * 初始化连接mqtt参数
     */
   public ClientMQTT() {
        this.datasourcePro = ApplicationContextProvider.getBean(MqttDispose.class);
        try {
            client = new MqttClient(datasourcePro.getHOST(), datasourcePro.getClientId(), new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(true);//断开连接时是否清除连接（重连获取以前数据）
            options.setUserName(datasourcePro.getUserName());
            options.setPassword(datasourcePro.getPassWord().toCharArray());
            options.setConnectionTimeout(datasourcePro.getOverTime());//设置超过时间
            options.setKeepAliveInterval(datasourcePro.getHeartbeatTime());//设置会话心跳时间
            client.setCallback(new PushCallback());
        } catch (Exception e) {
            recordLog.read(e);
        }
    }

    /**
     *连接mqtt
     */
    public static void start() {
        try {
            while (true) {
                try {
                    if (!client.isConnected()) {
                        client.connect(options);
                    }
                    if (client.isConnected()) {//连接成功，跳出连接
                        break;
                    }
                } catch (MqttException e) {
                    recordLog.read(e);
                }
            }
            //订阅消息
//            int[] Qos  = {0};
//            String[] topic1 = {datasourcePro.getTOPIC1()};
//            client.subscribe(topic1, Qos);

        } catch (Exception e) {
            recordLog.read(e);
        }
    }

    /**
     * 断开连接mqtt
     */
    public static void stop() {
        try {
            if(client.isConnected()) {
//                client.unsubscribe(datasourcePro.getTOPIC1());
                // 断开连接
                client.disconnect();
                // 关闭客户端
                client.close();
            }
        } catch (MqttException e) {
            recordLog.read(e);
        }
    }

    /**
     * 单个主题订阅
     * @param topic
     * @param qos
     * @throws MqttException
     */
    public static void subscribe(String topic,int qos)  {
        try {
            if (client.isConnected())
                client.subscribe(topic,qos);
        }
        catch (MqttException e){
            recordLog.read(e);
        }
    }

    /**
     *多个主题订阅
     * @param topic
     * @param qos
     * @throws MqttException
     */
    public static void subscribe(String[] topic,int[] qos)  {
        try {
            if (client.isConnected())
                client.subscribe(topic,qos);
        }
        catch (MqttException e){
            recordLog.read(e);
        }
    }
    public static void publish(String topic,byte[] str,int qos)
    {
        try {
            if (client.isConnected())
                client.publish(topic,str,qos,true);
        }
        catch (MqttException e){
                recordLog.read(e);
            }
    }

}
