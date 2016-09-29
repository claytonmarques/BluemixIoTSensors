package com.ibm.bluemix.magoogle.sensores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;

import com.ibm.bluemixmqtt.MqttUtil;

public class SensorInformationHandler implements Runnable {
	
	private double indice= -0.2;
	private double valorAtual= 31;
	private SensorBluemixIotConnectionHandler connectionHandler;
	

	public SensorInformationHandler(SensorBluemixIotConnectionHandler handler){
		connectionHandler = handler;
	}
	
	public void inicializaSensor() {
		Properties props = MqttUtil.readProperties("./MyData/"+connectionHandler.getDeviceId()+".conf");

        String indiceSetup = props.getProperty("indiceSetup");
        String valorInicialSensor = props.getProperty("valorInicialSensor");

        indice = Double.parseDouble(indiceSetup);
        valorAtual = Double.parseDouble(valorInicialSensor);
	
	}

	public void run() {
			
        	while (true) {

                JSONObject contObj = new JSONObject();
                JSONObject jsonObj = new JSONObject();
                try {
                    contObj.put("valorMedido", valorAtual);
                    contObj.put("horarioMedicao", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                    jsonObj.put("d", contObj);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

                connectionHandler.publica(jsonObj.toString());
                System.out.println("[" + connectionHandler.getDeviceId() + "] -> "+ jsonObj.toString());

                valorAtual += indice;

                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

	public void inverteIndice(){
		indice = indice*-1;
	}
	
	public void setValorAtual(double novoValor){
		valorAtual = novoValor;
	}
	
	

}
