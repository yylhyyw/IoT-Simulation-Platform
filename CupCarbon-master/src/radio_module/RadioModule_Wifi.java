package radio_module;

import java.io.PrintStream;

import org.bson.Document;

import device.SensorNode;
import utilities.UColor;

public class RadioModule_Wifi extends RadioModule {

	public RadioModule_Wifi(SensorNode sensorNode, String name) {
		super(sensorNode, name);
		init();
	}

	public void init() {
		frequency = 2.4e9;
		radioRangeColor1 = UColor.GREEN_TRANSPARENT;
		radioRangeColor2 = UColor.GREEND_TRANSPARENT;
		setRadioRangeRadius(400);
		setRadioRangeRadiusOri(400);
	}	
	
	public int getStandard() {
		return RadioModule.WIFI_802_11;
	}
	
	@Override
	public String getStandardName() {
		return "WIFI";
	}
	
	public double getTransmitPower() {
		double tpW = (Math.pow(10, transmitPower/10.))/1000.;
		double nTpW = tpW * 1e6;
		nTpW = nTpW * pl /100.;
		double nTpDbm = 10*Math.log10(nTpW/1000.);
		return nTpDbm;
	}
	
	@Override
	public RadioModule duplicate(SensorNode sensorNode) {
		RadioModule nRadioModule = new RadioModule_Wifi(sensorNode, name);
		nRadioModule.setMy(getMy());
		nRadioModule.setCh(getCh());
		nRadioModule.setNId(getNId());
		nRadioModule.setRadioRangeRadius(getRadioRangeRadius());
		nRadioModule.setETx(getETx());
		nRadioModule.setERx(getERx());
		nRadioModule.setESleep(getESleep());
		nRadioModule.setEListen(getEListen());
		nRadioModule.setRadioDataRate(getRadioDataRate());
		nRadioModule.setRadioConsoTxModel(getRadioConsoTxModel());
		nRadioModule.setRadioConsoRxModel(getRadioConsoRxModel());
		return nRadioModule;
	}

	public static String getPayLoad(String data) {
		return "";
	}
	
	@Override
	public void save(PrintStream fos, RadioModule currentRadioModule) {
		if (currentRadioModule.getName().equals(getName()))
			fos.println("current_radio_name:"+ getName());
		else
			fos.println("radio_name:"+ getName());
		fos.println("radio_standard:" + getStandardName());
		fos.println("radio_my:" + getMy());
		fos.println("radio_channel:" + getCh());
		fos.println("radio_network_id:" + getNId());
		fos.println("radio_radius:" + getRadioRangeRadius());
		fos.println("radio_etx:" + getETx());
		fos.println("radio_erx:" + getERx());
		fos.println("radio_esleep:" + getESleep());
		fos.println("radio_elisten:" + getEListen());
		fos.println("radio_data_rate:" + getRadioDataRate());
		fos.println("conso_tx_model:" + getRadioConsoTxModel());
		fos.println("conso_rx_model:" + getRadioConsoRxModel());
	}
	
	@Override
	public Document saveToDB(int index, RadioModule currentRadioModule, String deviceId, Document document) {
		if(!document.containsKey("prefix")) 
			document.append("prefix", "radio_module");
		if(document.containsKey("device_id")) 
			document.append("device_id", deviceId);
		if (currentRadioModule.getName().equals(getName()))
			document.append("current_radio_name", getName())
				.append("current_radio_index", index);
		document.append("radio_name" + index, getName())
			.append("radio_standard" + index, getStandardName())
			.append("radio_my"  + index, getMy())
			.append("radio_channel"  + index, getCh())
			.append("radio_network_id" + index, getNId())
			.append("radio_radius" + index, getRadioRangeRadius())
			.append("radio_etx" + index, getETx())
			.append("radio_erx" + index, getERx())
			.append("radio_esleep" + index, getESleep())
			.append("radio_elisten" + index, getEListen())
			.append("radio_data_rate" + index, getRadioDataRate())
			.append("conso_tx_model" + index, getRadioConsoTxModel())
			.append("conso_rx_model" + index, getRadioConsoRxModel());
		return document;
	}
}
