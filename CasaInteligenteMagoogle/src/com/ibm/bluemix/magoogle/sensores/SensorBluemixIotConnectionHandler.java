package com.ibm.bluemix.magoogle.sensores;

import java.util.Properties;

import com.ibm.bluemixmqtt.MqttHandler;
import com.ibm.bluemixmqtt.MqttUtil;

public class SensorBluemixIotConnectionHandler {
    
	private MqttHandler handler = null;
	private String deviceId;
    
	public SensorBluemixIotConnectionHandler() {
	}
	
	public void conecta(String deviceId) {
		
		this.deviceId = deviceId;
		
        Properties props = MqttUtil.readProperties("./MyData/"+deviceId+".conf");

        String org = props.getProperty("org");
        String id = props.getProperty("deviceid");
        String authmethod = "use-token-auth";
        String authtoken = props.getProperty("token");
        String deviceType = props.getProperty("deviceType");
        String sslStr = props.getProperty("isSSL");
        boolean isSSL = false;
        if (sslStr.equals("T")) {
            isSSL = true;
        }
        String appId = props.getProperty("appId");

        System.out.println("org: " + org);
        System.out.println("id: " + id);
        System.out.println("authmethod: " + authmethod);
        System.out.println("authtoken: " + authtoken);
        System.out.println("isSSL: " + isSSL);
        System.out.println("appId: " + appId);

        String serverHost = org + MqttUtil.SERVER_SUFFIX;

        //Format: d:<orgid>:<type-id>:<divice-id>
        String clientId;

        // https://new-console.ng.bluemix.net/docs/services/IoT/reference/security/connect_devices_apps_gw.html
        //Format: d:<orgid>:<type-id>:<divice-id>
        clientId = "d:" + org + ":" + deviceType + ":" + id;
         

        handler = new MqttHandler();
        handler.connect(serverHost, clientId, authmethod, authtoken, isSSL);

	}

	public void desconecta() {
		 handler.disconnect();
	}

	public void publica(String oQue){
		handler.publish("iot-2/evt/status/fmt/json", oQue, false, 0);
	}
	
	public String getDeviceId(){
		return deviceId;
	}
	
}
