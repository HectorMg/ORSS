package application;

import javafx.beans.property.SimpleStringProperty;

public class Doctor {
	
	private final SimpleStringProperty title;
	private final SimpleStringProperty name;
	private final SimpleStringProperty lastName;
	
	
	public Doctor(String sTitle, String sName, String sLastName){
		this.title = new SimpleStringProperty(sTitle);
		this.name = new SimpleStringProperty(sName);
		this.lastName = new SimpleStringProperty(sLastName);
		
	}
	//Getters:

	public String getTitle(){
		return title.get();
	}
	public String getName(){
		return name.get();
	}
	public String getLastName(){
		return lastName.get();
	}
	
	
	//Setters:
	
	
	public void setTitle(String nTitle){
		title.set(nTitle);
	}
	public void setName(String nName){
		name.set(nName);
	}
	public void setLastName(String nLastName){
		lastName.set(nLastName);
	}
}
