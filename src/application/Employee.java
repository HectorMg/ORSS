package application;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
	
	private final SimpleStringProperty id;
	private final SimpleStringProperty title;
	private final SimpleStringProperty name;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty numOfSurgeries;
	private final SimpleStringProperty bio;
	
	public Employee(String sID, String sTitle, String sName, String sLastName, String sBio,String sNumOfSurgeries){
		this.id = new SimpleStringProperty(sID);
		this.title = new SimpleStringProperty(sTitle);
		this.name = new SimpleStringProperty(sName);
		this.lastName = new SimpleStringProperty(sLastName);
		this.numOfSurgeries = new SimpleStringProperty(sNumOfSurgeries);
		this.bio = new SimpleStringProperty(sBio);
	}
	//Getters:
	
	public String getID(){
		return id.get();
	}
	public String getTitle(){
		return title.get();
	}
	public String getName(){
		return name.get();
	}
	public String getLastName(){
		return lastName.get();
	}
	public String getNumOfSurgeries(){
		return numOfSurgeries.get();
	}
	public String getBio(){
		return bio.get();
	}
	
	//Setters:
	
	public void setID(String nID){
		id.set(nID);
	}
	public void setTitle(String nTitle){
		title.set(nTitle);
	}
	public void setName(String nName){
		name.set(nName);
	}
	public void setLastName(String nLastName){
		lastName.set(nLastName);
	}
	public void setNumOfSurgeries(String nNumOfSurgeries){
		numOfSurgeries.set(nNumOfSurgeries);
	}
	public void setBio(String nBio){
		bio.set(nBio);
	}
}
