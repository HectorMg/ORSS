/**
 * 
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.*;

import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import application.Employee;
import application.DBConnect;

import java.util.ArrayList;




/**
 * @author hector
 *
 */
public class StaffController implements Initializable{

	//Table
	@FXML
	private TableView<Employee> tableData;
	@FXML
	private TableColumn<Employee, String> idCol;
	@FXML
	private TableColumn<Employee, String> titleCol;
	@FXML
	private TableColumn<Employee, String> nameCol;
	@FXML
	private TableColumn<Employee, String> lastNameCol;
	@FXML
	private TableColumn<Employee, String> surgeryNumCol;
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("ID"));
		titleCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("Title"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		surgeryNumCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("numOfSurgeries"));
		tableData.setEditable(true);
		
		DBConnect con = new DBConnect();
		
		ArrayList<String[]> employees = con.getStaffInfo();
		
		for(int i = 0; i < employees.size(); i++){
			data.add(new Employee(employees.get(i)[0],employees.get(i)[1], employees.get(i)[2], employees.get(i)[3], employees.get(i)[4], employees.get(i)[5]));
		}
		tableData.setItems(data);
		
		showEmployeeDetails(null);
		
		tableData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showEmployeeDetails(newValue));
	}
	
	@FXML
	private Button newSurgeryButton;
	@FXML
	private Button deleteSurgeryButton;
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
	private Button staffButton;
	@FXML
	public TextField idInput;
	@FXML
	public TextField titleInput;
	@FXML
	public TextField nameInput;
	@FXML
	public TextField lastNameInput;
	@FXML
	public TextField numOfSurgeriesInput;
	@FXML
	public TextArea bioInput;
	@FXML
	public Button enterButton;
	@FXML
	public Button deleteButton;
	@FXML
	public Button cancelButton;

	
	
	Stage stage = new Stage();
	private ObservableList<Employee> data = FXCollections.observableArrayList();
	
	
	public void sendInformation(ActionEvent event){
		String id = idInput.getText();
		String title = titleInput.getText();
		String name = nameInput.getText();
		String lastName = lastNameInput.getText();
		String numOfSurgeries = numOfSurgeriesInput.getText();
		String bio = bioInput.getText();
		int iD;
		
		if(id.equals("")){
			iD = 0;
		}else{
			iD= Integer.parseInt(id);
		}
		int numberOfSurgeries = Integer.parseInt(numOfSurgeries);
		
		DBConnect con = new DBConnect();
		con.InsertStaffInfo(iD, title, name, lastName, numberOfSurgeries, bio);
		
		data.removeAll(data);
		
		ArrayList<String[]> employees = con.getStaffInfo();
		
		for(int i = 0; i < employees.size(); i++){
			data.add(new Employee(employees.get(i)[0],employees.get(i)[1], employees.get(i)[2], employees.get(i)[3], employees.get(i)[4], employees.get(i)[5]));
		}
		tableData.setItems(data);
		cancelMod();
	}
	
	
	public void showEmployeeDetails(Employee employee){
		
		if(employee != null){
			idInput.setText(employee.getID());
			titleInput.setText(employee.getTitle());
			nameInput.setText(employee.getName());
			lastNameInput.setText(employee.getLastName());
			numOfSurgeriesInput.setText(employee.getNumOfSurgeries());
			bioInput.setText(employee.getBio());
			enterButton.setText("Modify");
		}
		
		
	}
	
	public void deleteEmployee(){
		
		DBConnect con = new DBConnect();
		String id =idInput.getText();
		con.deleteEmployeeInfo(id);
		
		data.removeAll(data);
		
		ArrayList<String[]> employees = con.getStaffInfo();
		
		for(int i = 0; i < employees.size(); i++){
			data.add(new Employee(employees.get(i)[0],employees.get(i)[1], employees.get(i)[2], employees.get(i)[3], employees.get(i)[4], employees.get(i)[5]));
		}
		tableData.setItems(data);
		cancelMod();
		
	}
	public void cancelMod(){
		enterButton.setText("Enter");
		idInput.clear();
		titleInput.clear();
		nameInput.clear();
		lastNameInput.clear();
		numOfSurgeriesInput.clear();
		bioInput.clear();
		
	}
	
	public void showCurrentSchedule(ActionEvent event) throws IOException{
		stage = (Stage) cancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void showCurrentSurgery(ActionEvent event) throws IOException{
		stage = (Stage) cancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void showNextSurgery(ActionEvent event) throws IOException{
		stage = (Stage) cancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void showLastSurgery(ActionEvent event){
		System.out.println("Show last surgery");
	}
	public void showSurgeryDetailsBy(ActionEvent event) throws IOException{
		stage = (Stage) surgeryDetailsByButton.getScene().getWindow();
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/SDBGUI.fxml"));
		Scene scene2 = new Scene(root2);
		stage.setScene(scene2);
	}
	public void showStaff(ActionEvent event){
		
	}
	public void createNewSurgery(ActionEvent event) throws IOException{
		stage = (Stage) cancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void deleteSurgery(ActionEvent event) throws IOException{
		stage = (Stage) cancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	
}
