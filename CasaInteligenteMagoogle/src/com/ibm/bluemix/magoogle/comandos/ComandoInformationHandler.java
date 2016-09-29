package com.ibm.bluemix.magoogle.comandos;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.ibm.bluemix.magoogle.sensores.SensorInformationHandler;
import com.ibm.bluemixmqtt.MqttHandler;

public class ComandoInformationHandler extends MqttHandler {
	private SensorInformationHandler sensorInformationHandler;
	private String estado="DESLIGADO";

	public ComandoInformationHandler(){
		super();
	}
	
	public void inject(SensorInformationHandler controleSensor){
		this.sensorInformationHandler = controleSensor;
		this.sensorInformationHandler.inicializaSensor();
	}

	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		super.messageArrived(topic, mqttMessage);

		if(getPayload().contains("LIGAR")){
			if(estado.equals("DESLIGADO")){
				mudaEstado();
				sensorInformationHandler.inverteIndice();				
			}
		}
		if(getPayload().contains("DESLIGAR")){
			if(estado.equals("LIGADO")){
				mudaEstado();
				sensorInformationHandler.inverteIndice();				
			}
		}

	}
	
    public String mudaEstado(){
    	if(estado.equals("LIGADO")){
    		estado = "DESLIGADO";
    	} else {
    		estado = "LIGADO";
    	}
    	return estado;
    } 


}
