package senscript;

import device.SensorNode;
import simulation.WisenSimulation;

public class Command_BUFFER extends Command {

	protected String arg = "" ;
	
	public Command_BUFFER(SensorNode sensor, String arg) {
		this.sensor = sensor ;
		this.arg = arg ;
	}

	@Override
	public double execute() {
		String v = "" ;
		WisenSimulation.simLog.add("S" + sensor.getId() + " BUFFER .");
		//v = sensor.getDataSize()+"";
		// default execute on buffer 1
		v = sensor.getBufferIndex(1)+"";
		sensor.getScript().addVariable(arg, v);
		return 0 ;
	}

	@Override
	public String toString() {
		return "GETPOS";
	}

}
