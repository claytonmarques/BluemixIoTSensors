package com.ibm.bluemix.magoogle;

public class Sensor {

	//SensorData data = new SensorData();
	BluemixIotConnectionHandler handler;
	EnviaInformacao enviaInformacao;
	Thread t;
			
	public Sensor() {
		 handler = new BluemixIotConnectionHandler();
	}

	// Liga o sensor ..
	// Ele começa a enviar informações para o Bluemix
	//
	public void liga(){
		enviaInformacao = new EnviaInformacao(handler);
		t = new Thread(enviaInformacao);
        System.out.println("Sensor iniciado !");
        t.start();
/*
 	    try {
 
			Thread.sleep(20000);
			ajustaIndice(0.2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}	
	
	// Desliga o sensor ..
	// Ele para de enviar informações para o Bluemix
	//
	public void desliga(){
        try {
            t.interrupt();
			t.join();
		} catch (InterruptedException e) {
            System.out.println("Erro enquanto tentava interromper o sensor !");
			e.printStackTrace();
		}
        System.out.println("Sensor encerrado !");
	}

	public void ajustaIndice(double novoIndice){
		enviaInformacao.ajustaIndice(novoIndice);
	}
    	
}
