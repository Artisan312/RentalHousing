package com.demo.equipmentControl;

import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * ������Ϣ�Ļص���
 *
 */
public class PushCallback implements MqttCallback {
	@SneakyThrows
	public void connectionLost(Throwable cause) {
		try {
			System.out.println("MQTT重连");
			Thread.sleep(1000);
			MqttLoadServer.mqttReceiveTest.stop();//关闭
			Thread.sleep(2000);
			MqttLoadServer.mqttReceiveTest.start();//重新连接
		}
		catch (Exception e) {
			MqttLoadServer.mqttReceiveTest.stop();//关闭
			e.printStackTrace();
		}
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("MQTT断开");
    }
    @SuppressWarnings("static-access")
	public void messageArrived(String topic, MqttMessage message) {

		try {

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }


}
