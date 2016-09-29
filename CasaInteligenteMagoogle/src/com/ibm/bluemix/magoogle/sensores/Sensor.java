package com.ibm.bluemix.magoogle.sensores;

public class Sensor {

	SensorBluemixIotConnectionHandler connectionHandler;
	SensorInformationHandler informationHandler;
	Thread t;
			
	public Sensor() {
		connectionHandler = new SensorBluemixIotConnectionHandler();
		informationHandler = new SensorInformationHandler(connectionHandler);
	}

	public void liga(){
		t = new Thread(informationHandler);
        t.start();
        System.out.println("Sensor ligado !");
	}	
	
	public void desliga(){
        try {
            t.interrupt();
			t.join();
	        System.out.println("Sensor desligado !");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public void connectaComBluemix(String nomeDoComando){
    	connectionHandler.conecta(nomeDoComando);
    	
    }
    
	public void desconnectaDoBluemix(){
		connectionHandler.desconecta();
	}

	public SensorInformationHandler getInformationHandler(){
		return informationHandler;
	}

	public void setInformationHandler(SensorInformationHandler informationHandler){
		this.informationHandler = informationHandler;
	}

}
