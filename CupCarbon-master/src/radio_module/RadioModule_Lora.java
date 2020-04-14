package radio_module;

import java.io.PrintStream;

import org.bson.Document;

import device.SensorNode;
import utilities.UColor;

public class RadioModule_Lora extends RadioModule {

	protected int spreadingFactor = 7;
	protected int codeRate = 0;
	
	public RadioModule_Lora(SensorNode sensorNode, String name) {
		super(sensorNode, name);
		init();
	}

	public void init() {
		frequency = 868e5; 
		//868 MHz for Europe
		//915 MHz for North America
		//433 MHz band for Asia
		
		radioRangeColor1 = UColor.BLUE_TTRANSPARENT;
		radioRangeColor2 = UColor.BLUE_TRANSPARENT;
		setRadioRangeRadius(5000);
		setRadioRangeRadiusOri(5000);
	}	
	
	public int getStandard() {
		return RadioModule.LORA;
	}
	
	@Override
	public String getStandardName() {
		return "LORA";
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
		RadioModule nRadioModule = new RadioModule_Lora(sensorNode, name);
		nRadioModule.setMy(getMy());
		nRadioModule.setCh(getCh());
		nRadioModule.setNId(getNId());
		nRadioModule.setRadioRangeRadius(getRadioRangeRadius());
		nRadioModule.setETx(getETx());
		nRadioModule.setERx(getERx());
		nRadioModule.setESleep(getESleep());
		nRadioModule.setEListen(getEListen());
		nRadioModule.setRadioDataRate(getRadioDataRate());
		nRadioModule.setSpreadingFactor(getSpreadingFactor());
		nRadioModule.setCodeRate(getCodeRate());
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
		fos.println("spreading_factor:" + getSpreadingFactor());
		fos.println("code_rate:" + getCodeRate());
		fos.println("conso_tx_model:" + getRadioConsoTxModel());
		fos.println("conso_rx_model:" + getRadioConsoRxModel());
	}
	
	@Override
	public Document saveToDB(int index, RadioModule currentRadioModule, String deviceId, Document document) {
		if(!document.containsKey("prefix")) 
			document.append("prefix", "radio_module");
		if(!document.containsKey("device_id")) 
				document.append("device_id", deviceId);
		if (currentRadioModule.getName().equals(getName())) 
			document.append("current_radio_name", getName())
				.append("current_radio_index", index);
		document.append("radio_name"+ index, getName())
			.append("radio_standard"+ index, getStandardName())
			.append("radio_my"+ index, getMy())
			.append("radio_channel"+ index, getCh())
			.append("radio_network_id"+ index, getNId())
			.append("radio_radius"+ index, getRadioRangeRadius())
			.append("radio_etx"+ index, getETx())
			.append("radio_erx"+ index, getERx())
			.append("radio_esleep"+ index, getESleep())
			.append("radio_elisten"+ index, getEListen())
			.append("radio_data_rate"+ index, getRadioDataRate())
			.append("spreading_factor"+ index, getSpreadingFactor())
			.append("code_rate"+ index, getCodeRate())
			.append("conso_tx_model"+ index, getRadioConsoTxModel())
			.append("conso_rx_model"+ index, getRadioConsoRxModel());
		return document;
	}
	
	public int getSpreadingFactor() {
		return spreadingFactor;
	}
	
	public void setSpreadingFactor(int spreadingFactor) {
		this.spreadingFactor = spreadingFactor ;
	}
	
	public int getCodeRate() {
		return codeRate;
	}
	
	public void setCodeRate(int codeRate) {
		this.codeRate = codeRate ;
	}
	
}
