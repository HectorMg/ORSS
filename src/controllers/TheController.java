/**
 * 
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.*;

import application.DBConnect;
import application.Surgery;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


/**
 * @author hector
 *
 */
public class TheController implements Initializable{
	
	Stage stage = new Stage();
	private ObservableList<Surgery> data = FXCollections.observableArrayList();
	
	//Table
		@FXML
		private TableView<Surgery> tableSchedule;
		@FXML
		private TableColumn<Surgery, String> idCol;
		@FXML
		private TableColumn<Surgery, String> timeCol;
		@FXML
		private TableColumn<Surgery, String> patientCol;
		@FXML
		private TableColumn<Surgery, String> procedureCol;
		@FXML
		private TableColumn<Surgery, String> surgeonCol;
		@FXML
		private TableColumn<Surgery, String> anesCol;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		idCol.setCellValueFactory(new PropertyValueFactory<Surgery, String>("ID"));
		timeCol.setCellValueFactory(new PropertyValueFactory<Surgery, String>("Start"));
		patientCol.setCellValueFactory(new PropertyValueFactory<Surgery, String>("Patient"));
		procedureCol.setCellValueFactory(new PropertyValueFactory<Surgery, String>("Procedure"));
		surgeonCol.setCellValueFactory(new PropertyValueFactory<Surgery, String>("Surgeon"));
		anesCol.setCellValueFactory(new PropertyValueFactory<Surgery, String>("Anes"));
		
		tableSchedule.setEditable(true);
		
		DBConnect con = new DBConnect();
		
		ArrayList<String[]> schedule = con.getCurrentSchedule();
		
		for(int i = 0; i < schedule.size(); i++){
			Surgery surg = new Surgery(schedule.get(i)[0],schedule.get(i)[1], schedule.get(i)[2], schedule.get(i)[3], schedule.get(i)[4], schedule.get(i)[5], schedule.get(i)[6], schedule.get(i)[7]);
			data.add(surg);
		}
		tableSchedule.setItems(data);
		
		showSurgeryDetails(null);
		
		tableSchedule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSurgeryDetails(newValue));
	
	}
	@FXML
	private Button newSurgeryButton;
	@FXML
	private Button deleteSurgeryButton;
	@FXML
	private Button pSurgeriesButton;
	@FXML
	private Button cScheduleButton;
	@FXML
	private Button cSurgeryButton;
	@FXML
	private Button lastSurgeryButton;
	@FXML
	private Button nextSurgeryButton;
	@FXML
	private Button surgeryDetailsByButton;
	@FXML
	private Button enterButton;
	@FXML
	private Button deletButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button staffButton;
	@FXML
	private Label newSurgery;
	@FXML
	private TextField idInput;
	@FXML
	private TextField timeInput;
	@FXML
	private TextField patientInput;
	@FXML
	private TextField procedureInput;
	@FXML
	private TextArea detsInput;
	@FXML
	private TextField surgeonInput;
	@FXML
	private TextField anesInput;
	@FXML
	private TextField nurseInput;
	
		
	
	public void showSurgeryDetails(Surgery surg){
		if(surg != null){
			idInput.setText(surg.getID());
			timeInput.setText(surg.getStart());
			patientInput.setText(surg.getPatient());
			procedureInput.setText(surg.getProcedure());
			detsInput.setText(surg.getDetails());
			surgeonInput.setText(surg.getSurgeon());
			anesInput.setText(surg.getAnes());
			nurseInput.setText(surg.getNurse());
			
			enterButton.setText("Modify");
			newSurgery.setText("Surgery: " + surg.getID());
			cancelButton.setText("Cancel");
		}
	}
	public void deleteSurgery(ActionEvent event){
		DBConnect con = new DBConnect();
		String id = idInput.getText();
		con.deleteSurgeryInfo(id);
		
		data.removeAll(data);
		
		ArrayList<String[]> schedule = con.getCurrentSchedule();
		
		for(int i = 0; i < schedule.size(); i++){
			Surgery surg = new Surgery(schedule.get(i)[0],schedule.get(i)[1], schedule.get(i)[2], schedule.get(i)[3], schedule.get(i)[4], schedule.get(i)[5], schedule.get(i)[6], schedule.get(i)[7]);
			data.add(surg);
		}
		tableSchedule.setItems(data);
		cancelMod();
	}
	public void sendInformation(ActionEvent event){
		
		String id = idInput.getText();
		String time = timeInput.getText();
		String patient = patientInput.getText();
		String procedure = procedureInput.getText();
		String dets = detsInput.getText();
		String surgeon = surgeonInput.getText();
		String anes = anesInput.getText();
		String nurse = nurseInput.getText();
		
		if(id.equals("")){
			id = "0";
		}
	
		Surgery surgery = new Surgery(id, surgeon, anes, nurse, dets, time, patient, procedure);
		DBConnect con = new DBConnect();
		con.InsertSurgery(surgery);
		
		data.removeAll(data);
		
		ArrayList<String[]> schedule = con.getCurrentSchedule();
		
		for(int i = 0; i < schedule.size(); i++){
			Surgery surg = new Surgery(schedule.get(i)[0],schedule.get(i)[1], schedule.get(i)[2], schedule.get(i)[3], schedule.get(i)[4], schedule.get(i)[5], schedule.get(i)[6], schedule.get(i)[7]);
			data.add(surg);
		}
		tableSchedule.setItems(data);
		cancelMod();
	}
	
	public void cancelMod(){
		enterButton.setText("Enter");
		cancelButton.setText("Clear");
		newSurgery.setText("New Surgery");
		idInput.clear();
		timeInput.clear();
		patientInput.clear();
		procedureInput.clear();
		detsInput.clear();
		surgeonInput.clear();
		anesInput.clear();
		nurseInput.clear();
	}
	public void retireSurgery(ActionEvent event){
		DBConnect con = new DBConnect();
		String surgID = idInput.getText();
		con.transferSurgery(surgID);
		
		data.removeAll(data);
		
		ArrayList<String[]> schedule = con.getCurrentSchedule();
		
		for(int i = 0; i < schedule.size(); i++){
			Surgery surg = new Surgery(schedule.get(i)[0],schedule.get(i)[1], schedule.get(i)[2], schedule.get(i)[3], schedule.get(i)[4], schedule.get(i)[5], schedule.get(i)[6], schedule.get(i)[7]);
			data.add(surg);
		}
		tableSchedule.setItems(data);
		cancelMod();
		
	}
	public void showCurrentSchedule(ActionEvent event){
	
	}
	public void showPastSurgeries(ActionEvent event) throws IOException {
		stage = (Stage) pSurgeriesButton.getScene().getWindow();
		Parent root4 = FXMLLoader.load(getClass().getResource("/application/PastGUI.fxml"));
		Scene scene4 = new Scene(root4);
		stage.setScene(scene4);
	}
	public void showCurrentSurgery(ActionEvent event){
		tableSchedule.getSelectionModel().select(0);
	}
	public void showNextSurgery(ActionEvent event){
		tableSchedule.getSelectionModel().select(1);
	}
	public void showLastSurgery(ActionEvent event) throws IOException{
		stage = (Stage) pSurgeriesButton.getScene().getWindow();
		Parent root4 = FXMLLoader.load(getClass().getResource("/application/PastGUI.fxml"));
		Scene scene4 = new Scene(root4);
		stage.setScene(scene4);
		
		
	}
	public void showSurgeryDetailsBy(ActionEvent event) throws IOException{
		stage = (Stage) surgeryDetailsByButton.getScene().getWindow();
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/SDBGUI.fxml"));
		Scene scene2 = new Scene(root2);
		stage.setScene(scene2);
	}
	public void showStaff(ActionEvent event) throws IOException{
		stage = (Stage) surgeryDetailsByButton.getScene().getWindow();
		Parent root3 = FXMLLoader.load(getClass().getResource("/application/StaffGUI.fxml"));
		Scene scene3 = new Scene(root3);
		stage.setScene(scene3);
	}
	public void createNewSurgery(ActionEvent event){
		System.out.println("Show create new surgery input fields");
	}
	
	
}
