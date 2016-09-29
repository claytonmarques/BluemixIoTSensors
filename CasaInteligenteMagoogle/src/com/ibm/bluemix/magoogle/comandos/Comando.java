package com.ibm.bluemix.magoogle.comandos;

import com.ibm.bluemix.magoogle.comandos.ComandoBluemixIotConnectionHandler;
import com.ibm.bluemix.magoogle.sensores.SensorInformationHandler;

public class Comando {

	ComandoBluemixIotConnectionHandler connectionHandler;

	Thread t;
			

	public Comando() {
		 connectionHandler = new ComandoBluemixIotConnectionHandler();
	}

    public void connectaComBluemix(String nomeDoComando, SensorInformationHandler sensorInformationHandler){
    	connectionHandler.conecta(nomeDoComando);
    	connectionHandler.getInformationHandler().inject(sensorInformationHandler);
    	connectionHandler.assinaMensagensDoSensor();
    }
    
	public void desconnectaDoBluemix(){
		connectionHandler.desconecta();
	}   

}
