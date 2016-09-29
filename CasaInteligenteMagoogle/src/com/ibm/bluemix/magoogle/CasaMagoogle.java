package com.ibm.bluemix.magoogle;

import com.ibm.bluemix.magoogle.comandos.Comando;
import com.ibm.bluemix.magoogle.sensores.Sensor;

// Controller para as demais classes
public class CasaMagoogle {

	private static Sensor sensorTemperaturaBoiler = new Sensor();
	private static Comando controleAquecedorBoiler = new Comando();

	private static Sensor sensorNivelCaixaDagua = new Sensor();
	private static Comando controleMotorBombaCaixaDagua = new Comando();
	
	public static void main(String[] args) {
		ligaSensorTemperaturaBoiler();
     	ligaInterruptorAquecedorBoiler();
		
//		ligaSensorNivelCaixaDagua();
//		ligaInterruptorMotorBombaCaixaDagua();

		try {
			Thread.sleep(3600000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		desligaInterruptorMotorBombaCaixaDagua();
		desligaSensorNivelCaixaDagua();

//		desligaInterruptorAquecedorBoiler();
//		desligaSensorTemperaturaBoiler();
		
	}
	
	public static void ligaSensorTemperaturaBoiler(){
		sensorTemperaturaBoiler.connectaComBluemix("SensorTemperaturaBoiler");
		sensorTemperaturaBoiler.liga();
	}

	public static void ligaInterruptorAquecedorBoiler(){
		controleAquecedorBoiler.connectaComBluemix("AquecedorEletricoBoiler", sensorTemperaturaBoiler.getInformationHandler());		
	}

	public static void desligaSensorTemperaturaBoiler(){
	    sensorTemperaturaBoiler.desliga();
	    sensorTemperaturaBoiler.desconnectaDoBluemix();
	}

	public static void desligaInterruptorAquecedorBoiler(){
		controleAquecedorBoiler.desconnectaDoBluemix();
	}
	
	public static void ligaSensorNivelCaixaDagua(){
		sensorNivelCaixaDagua.connectaComBluemix("SensorNivelCaixaDagua");
		sensorNivelCaixaDagua.liga();
	}

	public static void ligaInterruptorMotorBombaCaixaDagua(){
		controleMotorBombaCaixaDagua.connectaComBluemix("MotorBombaCaixaDagua", sensorNivelCaixaDagua.getInformationHandler());		
	}

	public static void desligaSensorNivelCaixaDagua(){
	    sensorTemperaturaBoiler.desliga();
	    sensorTemperaturaBoiler.desconnectaDoBluemix();
	}

	public static void desligaInterruptorMotorBombaCaixaDagua(){
		controleMotorBombaCaixaDagua.desconnectaDoBluemix();
	}



}
