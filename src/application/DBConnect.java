package application;

import java.sql.*;
import java.util.ArrayList;
import java.lang.ClassNotFoundException;


public class DBConnect {
	private Connection con;
	private Statement sment;
	private ResultSet rs;
	private PreparedStatement insert;
	private PreparedStatement search;
	private PreparedStatement update;
	private PreparedStatement delete;

	public DBConnect() {
		
		con = null;
		sment = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:8889/orss", "root", "root");
			
			sment = con.createStatement();

		} catch (SQLException ex) {
			
			System.out.println("Error: " + ex.getMessage());
		}catch (ClassNotFoundException ex) {
			
			System.out.println("Error: " + ex.getMessage());
		}
	}
	

	public ArrayList<String[]> getStaffInfo() {
		
		//Queries Staff Table and Puts it in an ArrayList that is returned
		
		ArrayList<String[]> employees = new ArrayList<String[]>();
		String[] employee = new String[6];
		
		try {
			
			String query = "select * from Staff";
			rs = sment.executeQuery(query);
			while (rs.next()) {
				//Query from DB
				int idNum = rs.getInt("nID");
				String id = Integer.toString(idNum);
				String title = rs.getString("Title");
				String name = rs.getString("Names");
				String lastName = rs.getString("LastNames"); 
				int surgeries = rs.getInt("NumberOfSurgeries");
				String surgeryNum = Integer.toString(surgeries);
				String bio = rs.getString("Bio");
				
				//Insert into 2-D Array
				
				employee[0] = id;
				employee[1] = title;
				employee[2] = name;
				employee[3] = lastName;
				employee[4] = bio;
				employee[5] = surgeryNum;
				
				employees.add(employee.clone());
				
				
			}
			
			return employees;

		}catch (Exception ex) {
			System.out.println("Error: " + ex);
			employees = null;
			return employees;
		}
	}
	
	/*public ArrayList<String[]> getDrs(){
		//Queries Doctors and puts them in Arraylist doctors:
		
				ArrayList<String[]> doctors = new ArrayList<String[]>();
				String[] dr = new String[6];
				
				try {
					
					String query = "select * from Staff";
					rs = sment.executeQuery(query);
					while (rs.next()) {
						//Query from DB
						
						String title = rs.getString("Title");
						String name = rs.getString("Names");
						String lastName = rs.getString("LastNames"); 
						
						
						if(title.equals("Dr.")){
							
							dr[0] = title;
							dr[1] = name;
							dr[2] = lastName;
						
							doctors.add(dr.clone());
							
						}
						
					}
					
					return doctors;

				}catch (Exception ex) {
					System.out.println("Error: " + ex);
					doctors = null;
					return doctors;
				}
	}
	
	public ArrayList<String[]> getNrs(){
		//Queries Doctors and puts them in Arraylist doctors:
		
				ArrayList<String[]> nurses = new ArrayList<String[]>();
				String[] nrs = new String[6];
				
				try {
					
					String query = "select * from Staff";
					rs = sment.executeQuery(query);
					while (rs.next()) {
						//Query from DB
						
						String title = rs.getString("Title");
						String name = rs.getString("Names");
						String lastName = rs.getString("LastNames"); 
						
						
						if(title.equals("Enfra.")){
							
							nrs[0] = title;
							nrs[1] = name;
							nrs[2] = lastName;
						
							nurses.add(nrs.clone());
							
						}
						
					}
					
					return nurses;

				}catch (Exception ex) {
					System.out.println("Error: " + ex);
					nurses = null;
					return nurses;
				}
	}*/
	public void InsertStaffInfo(int nID, String nTitle, String nName, String nLastName, int nNumOfSurgeries, String nBio){
	
		
		try{
			insert = con.prepareStatement("insert into staff(nID, Title, Names, LastNames, Bio, NumberOfSurgeries) values(?,?,?,?,?,?)");
			search = con.prepareStatement("select * from staff where nID = ?");
			update = con.prepareStatement("update staff set `Title` = ?, `Names` = ?, `LastNames` = ?, `Bio` = ?, `NumberOfSurgeries` = ? where `nID` = ?");
			
			String sID = Integer.toString(nID);
			search.setString(1, sID);
			rs = search.executeQuery();
			
			if(!rs.next()){
				int newID = 0;
				if(nID == 0){
					String query = "select max(nID) as nID from staff";
					rs = sment.executeQuery(query);
					
					if(rs.next()){
						newID = rs.getInt("nID") + 1;	
					}
					
				}else{
					newID = nID;
				}
				insert.setInt(1, newID);
				insert.setString(2, nTitle);
				insert.setString(3, nName);
				insert.setString(4, nLastName);
				insert.setString(5, nBio);
				insert.setInt(6, nNumOfSurgeries);
				insert.executeUpdate();
			}else{

				update.setString(1, nTitle);
				update.setString(2, nName);
				update.setString(3, nLastName);
				update.setString(4, nBio);
				update.setInt(5, nNumOfSurgeries);
				update.setString(6, sID);
				update.executeUpdate();
			}
			
			
		}catch(SQLException e ){
			System.out.println("Exception " + e);
		}
		
	}
	
	public void InsertSurgery(Surgery surg){

		
		try{
			insert = con.prepareStatement("insert into CurrentSchedule(Surgeon, Anesthesiologist, Nurse, Details, Start, Patient, SurgicalProcedure) values(?,?,?,?,?,?,?)");
			search = con.prepareStatement("select * from CurrentSchedule where ID = ?");
			update = con.prepareStatement("update CurrentSchedule set `Surgeon` = ?, `Anesthesiologist` = ?, `Nurse` = ?, `Details` = ?, `Start` = ?, `Patient` = ?, `SurgicalProcedure` = ? where `ID` = ?");
			
			String id = surg.getID();
			String surgeon = surg.getSurgeon();
			String anes = surg.getAnes();
			String nurse = surg.getNurse();
			String dets = surg.getDetails();
			String time = surg.getStart();
			String patient = surg.getPatient();
			String procedure = surg.getProcedure();
			
			search.setString(1, id);
			rs = search.executeQuery();
			
			if(!rs.next()){
				if(id.equals("0")){
					String query = "select max(ID) as ID from staff";
					rs = sment.executeQuery(query);
					
				
				insert.setString(1, surgeon);
				insert.setString(2, anes);
				insert.setString(3, nurse);
				insert.setString(4, dets);
				insert.setString(5, time);
				insert.setString(6, patient);
				insert.setString(7, procedure);
				insert.executeUpdate();
				}
			}else{

				update.setString(1, surgeon);
				update.setString(2, anes);
				update.setString(3, nurse);
				update.setString(4, dets);
				update.setString(5, time);
				update.setString(6, patient);
				update.setString(7, procedure);
				update.setString(8, id);
				update.executeUpdate();
			}
			
			
		}catch(SQLException e ){
			System.out.println("Exception " + e);
		}
	}
	public void ModifyPastSurgery(Surgery surg){

		
		try{
			search = con.prepareStatement("select * from PastSurgeries where ID = ?");
			update = con.prepareStatement("update PastSurgeries set `Surgeon` = ?, `Anesthesiologist` = ?, `Nurse` = ?, `Details` = ?, `Start` = ?, `Patient` = ?, `SurgicalProcedure` = ? where `ID` = ?");
			
			String id = surg.getID();
			String surgeon = surg.getSurgeon();
			String anes = surg.getAnes();
			String nurse = surg.getNurse();
			String dets = surg.getDetails();
			String time = surg.getStart();
			String patient = surg.getPatient();
			String procedure = surg.getProcedure();
			
			search.setString(1, id);
			rs = search.executeQuery();
			
			if(rs.next()){
				update.setString(1, surgeon);
				update.setString(2, anes);
				update.setString(3, nurse);
				update.setString(4, dets);
				update.setString(5, time);
				update.setString(6, patient);
				update.setString(7, procedure);
				update.setString(8, id);
				update.executeUpdate();
					
				}
			
			
		}catch(SQLException e ){
			System.out.println("Exception " + e);
		}
	}
	public void deleteSurgeryInfo(String id){
		try{
			sment.executeUpdate("delete from CurrentSchedule where ID = " + id);
		}catch(SQLException e){
			System.out.println("Exception: " + e);
		}
	}
	public void deleteSurgeryInfo2(String id){
		try{
			sment.executeUpdate("delete from PastSurgeries where ID = " + id);
		}catch(SQLException e){
			System.out.println("Exception: " + e);
		}
	}
	public void transferSurgery(String id){
		try{
			insert = con.prepareStatement("insert into PastSurgeries(ID, Surgeon, Anesthesiologist, Nurse, Details, Start, Patient, SurgicalProcedure) values(?,?,?,?,?,?,?,?)");
			search = con.prepareStatement("select * from CurrentSchedule where ID = ?");
			delete = con.prepareStatement("delete from CurrentSchedule where ID = ?");
			
			
			search.setString(1, id);
			rs = search.executeQuery();
			
			if(rs.next()){
				
				String surgeon = rs.getString("Surgeon");
				String anes = rs.getString("Anesthesiologist");
				String nurse = rs.getString("Nurse");
				String dets = rs.getString("Details");
				String time = rs.getString("Start");
				String patient = rs.getString("Patient");
				String procedure = rs.getString("SurgicalProcedure");
				insert.setString(1, id);
				insert.setString(2, surgeon);
				insert.setString(3, anes);
				insert.setString(4, nurse);
				insert.setString(5, dets);
				insert.setString(6, time);
				insert.setString(7, patient);
				insert.setString(8, procedure);
				insert.executeUpdate();
				
				delete.setString(1, id);
				delete.executeUpdate();
				
			}
			
			
		}catch(SQLException e ){
			System.out.println("Exception " + e);
		}
	}
	public void deleteEmployeeInfo(String id){
		try{
			sment.executeUpdate("delete from staff where nID = " + id);
		}catch(SQLException e){
			System.out.println("Exception: " + e);
		}
	}
	public ArrayList<String[]> getCurrentSchedule() {
		
		//Queries Schedule Table and Puts it in an ArrayList that is returned
		
		ArrayList<String[]> schedule = new ArrayList<String[]>();
		String[] surgery = new String[8];
		
		try {
			
			String query = "select * from CurrentSchedule";
			rs = sment.executeQuery(query);
			while (rs.next()) {
				
				//Query from DB
				int idNum = rs.getInt("ID");
				String surgID = Integer.toString(idNum);
				String surgeon = rs.getString("Surgeon");
				String anes = rs.getString("Anesthesiologist");
				String nurse = rs.getString("Nurse");
				String dets = rs.getString("Details");
				String start = rs.getString("Start");
				String patient = rs.getString("Patient");
				String procedure = rs.getString("SurgicalProcedure");
				
				//Insert into 2-D Array
				
				surgery[0] = surgID;
				surgery[1] = surgeon;
				surgery[2] = anes;
				surgery[3] = nurse;
				surgery[4] = dets;
				surgery[5] = start;
				surgery[6] = patient;
				surgery[7] = procedure;
				
				schedule.add(surgery.clone());
				
			}
			
			return schedule;

		}catch (Exception ex) {
			System.out.println("Error: " + ex);
			schedule = null;
			return schedule;
		}
	}
	public ArrayList<String[]> getPastSchedule() {
		
		//Queries Schedule Table and Puts it in an ArrayList that is returned
		
		ArrayList<String[]> schedule = new ArrayList<String[]>();
		String[] surgery = new String[8];
		
		try {
			
			String query = "select * from PastSurgeries";
			rs = sment.executeQuery(query);
			while (rs.next()) {
				
				//Query from DB
				int idNum = rs.getInt("ID");
				String surgID = Integer.toString(idNum);
				String surgeon = rs.getString("Surgeon");
				String anes = rs.getString("Anesthesiologist");
				String nurse = rs.getString("Nurse");
				String dets = rs.getString("Details");
				String start = rs.getString("Start");
				String patient = rs.getString("Patient");
				String procedure = rs.getString("SurgicalProcedure");
				
				//Insert into 2-D Array
				
				surgery[0] = surgID;
				surgery[1] = surgeon;
				surgery[2] = anes;
				surgery[3] = nurse;
				surgery[4] = dets;
				surgery[5] = start;
				surgery[6] = patient;
				surgery[7] = procedure;
				
				schedule.add(surgery.clone());
				
			}
			
			return schedule;

		}catch (Exception ex) {
			System.out.println("Error: " + ex);
			schedule = null;
			return schedule;
		}
	}
}
