/**
 * 
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author Dev
 *
 */
public class SDBController implements Initializable{
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	private Button sdbCancelButton;
	
	Stage stage = new Stage();
	
	public void showCurrentSchedule(ActionEvent event) throws IOException{
		stage = (Stage) sdbCancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void showCurrentSurgery(ActionEvent event) throws IOException{
		stage = (Stage) sdbCancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void showNextSurgery(ActionEvent event) throws IOException{
		stage = (Stage) sdbCancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void showLastSurgery(ActionEvent event){
		System.out.println("Show last surgery");
	}
	public void showSurgeryDetailsBy(ActionEvent event){
	}
	public void showStaff(ActionEvent event) throws IOException{
		stage = (Stage) surgeryDetailsByButton.getScene().getWindow();
		Parent root3 = FXMLLoader.load(getClass().getResource("/application/StaffGUI.fxml"));
		Scene scene3 = new Scene(root3);
		stage.setScene(scene3);
	}
	public void createNewSurgery(ActionEvent event) throws IOException{
		stage = (Stage) sdbCancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void deleteSurgery(ActionEvent event) throws IOException{
		stage = (Stage) sdbCancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
	public void cancelSDB(ActionEvent event) throws IOException{
		stage = (Stage) sdbCancelButton.getScene().getWindow();
		Parent root1 = FXMLLoader.load(getClass().getResource("/application/ORSSGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage.setScene(scene1);
		stage.show();
	}
}
