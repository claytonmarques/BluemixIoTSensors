package com.ibm.bluemix.magoogle.comandos;

import java.util.Properties;

import com.ibm.bluemixmqtt.MqttUtil;

public class ComandoBluemixIotConnectionHandler {
    
//	private MqttHandler handler = null;
	private ComandoInformationHandler informationHandler = null;
	private String deviceId;
    
	public ComandoBluemixIotConnectionHandler() {
        informationHandler = new ComandoInformationHandler();
	}
	
	public void conecta(String deviceId) {

		this.deviceId = deviceId;
		
		//Read properties from the conf file
        Properties props = MqttUtil.readProperties("./MyData/"+deviceId+".conf");

		String org = props.getProperty("org");
		String id = props.getProperty("appId");
		String authmethod = props.getProperty("key");
		String authtoken = props.getProperty("token");
		//isSSL property
		String sslStr = props.getProperty("isSSL");
        boolean isSSL = false;
        if (sslStr.equals("T")) {
            isSSL = true;
        }

        System.out.println("org: " + org);
        System.out.println("id: " + id);
        System.out.println("authmethod: " + authmethod);
        System.out.println("authtoken: " + authtoken);
        System.out.println("isSSL: " + isSSL);

        String serverHost = org + MqttUtil.SERVER_SUFFIX;

        String clientId;

        //Format: a:<orgid>:<app-id>
    	clientId = "a:" + org + ":" + id;
    	
        informationHandler.connect(serverHost, clientId, authmethod, authtoken, isSSL);

	}

	public void desconecta() {
		 informationHandler.disconnect();
	}

	public ComandoInformationHandler getInformationHandler(){
		return informationHandler;
	}

	public void assinaMensagensDoSensor(){

		//  iot-2/type/device_type/id/device_id/cmd/command_id/fmt/format_string
		informationHandler.subscribe("iot-2/type/switch/id/"+deviceId+"/cmd/inverteFuncao/fmt/json", 0);
		
	}
	
}
