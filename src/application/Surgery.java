package application;

import javafx.beans.property.SimpleStringProperty;

public class Surgery {
	
	private final SimpleStringProperty surgID;
	private final SimpleStringProperty surgeon;
	private final SimpleStringProperty anes;
	private final SimpleStringProperty nurse;
	private final SimpleStringProperty dets;
	private final SimpleStringProperty start;
	private final SimpleStringProperty patient;
	private final SimpleStringProperty procedure;
	
	public Surgery(String sID, String sSurgeon, String sAnes, String sNurse, String sDets, String sStart, String sPatient, String sProcedure){
		this.surgID = new SimpleStringProperty(sID);
		this.surgeon = new SimpleStringProperty(sSurgeon);
		this.anes = new SimpleStringProperty(sAnes);
		this.nurse = new SimpleStringProperty(sNurse);
		this.dets = new SimpleStringProperty(sDets);
		this.start = new SimpleStringProperty(sStart);
		this.patient = new SimpleStringProperty(sPatient);
		this.procedure = new SimpleStringProperty(sProcedure);
	}
	//Getters:
	
	public String getID(){
		return surgID.get();
	}
	public String getSurgeon(){
		return surgeon.get();
	}
	public String getAnes(){
		return anes.get();
	}
	public String getNurse(){
		return nurse.get();
	}
	public String getDetails(){
		return dets.get();
	}
	public String getStart(){
		return start.get();
	}
	public String getPatient(){
		return patient.get();
	}
	public String getProcedure(){
		return procedure.get();
	}
	
	//Setters:
	
	public void setID(String nID){
		surgID.set(nID);
	}
	public void setSurgeon(String nSurgeon){
		surgeon.set(nSurgeon);
	}
	public void setAnes(String nAnes){
		anes.set(nAnes);
	}
	public void setNurse(String nNurse){
		nurse.set(nNurse);
	}
	public void setDetails(String nDets){
		dets.set(nDets);
	}
	public void setStart(String nStart){
		start.set(nStart);
	}
	public void setPatient(String nPatient){
		patient.set(nPatient);
	}
	public void setProcedure(String nProcedure){
		procedure.set(nProcedure);
	}
}
