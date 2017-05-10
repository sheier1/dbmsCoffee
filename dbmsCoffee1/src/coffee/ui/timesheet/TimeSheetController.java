package coffee.ui.timesheet;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import coffee.ui.manager.ManagerLoginController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TimeSheetController implements Initializable{
	
	Connection con;
	ObservableList<Employee> list = FXCollections.observableArrayList();

	   @FXML
	    private Button ClockIn;

	    @FXML
	    private Button ClockOut;

	    @FXML
	    private TableView<Employee> ClockTable;

	    @FXML
	    private TableColumn<Employee, String> inCol;

	    @FXML
	    private TableColumn<Employee, String> outCol;

	    @FXML
	    private TableColumn<Employee, String> idCol;

	    @FXML
	    private JFXButton refreshButton;

	    @FXML
	    private Label clockedLabel;

	    @FXML
	    private Label clockedoutLabel;
	    
	    @FXML
	    void checkIn(ActionEvent event) {
	    	clockedoutLabel.setText("");
	    	java.sql.Timestamp inTime = new java.sql.Timestamp(new java.util.Date().getTime());
	    	try{
	    	PreparedStatement stmt = con.prepareStatement("INSERT INTO clock VALUES(?,?,?)");
	        stmt.setTimestamp(1, inTime); 
	        stmt.setTimestamp(2, inTime);
	        stmt.setString(3, ManagerLoginController.pubID);
	    	stmt.execute(); // execute insert statement
	        clockedLabel.setText("Clocked in!");
	        stmt.close();
	        refreshTable();
	        refreshAllTable();
	       } catch (Exception e) {
	         e.printStackTrace();
	       }
	    }

	    @FXML
	    void checkOut(ActionEvent event) {
	    	clockedLabel.setText("");
	    	java.sql.Timestamp outTime = new java.sql.Timestamp(new java.util.Date().getTime());
	    	try{
//	    	PreparedStatement stmt = con.prepareStatement("INSERT INTO clock VALUES('"+inTime+"','"+new Timestamp(date.getTime()).toString()+"','"+EmployeeLoginController.pubID+"')");
	         PreparedStatement stmt = con.prepareStatement("UPDATE clock SET clock_out = '"+outTime+"' WHERE clock_out = clock_in AND emp_id = "+ManagerLoginController.pubID);
	    	 stmt.execute(); // execute insert statement
	         clockedoutLabel.setText("Clocked out!");
	         stmt.close();
	         refreshTable();
	         refreshAllTable();
	       } catch (Exception e) {
	         e.printStackTrace();
	       }

	    }
	    
	    @FXML
	    void refreshTable(ActionEvent event) {
	    	refreshTable();
	    }
	    
	    void refreshTable(){
			try{
				list.clear(); //clear list at start of the new update
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM clock WHERE emp_id = "+ManagerLoginController.pubID);
				while(rs.next()){
					String clock_in = rs.getString("clock_in");
					String clock_out = rs.getString("clock_out");
					String emp_id = rs.getString("emp_id");
					System.out.println(clock_in + " " + clock_out + " " +emp_id);
					list.add(new Employee(clock_in, clock_out, emp_id));
				}
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			ClockTable.getItems().setAll(list);

		}
	    
	    private void initCol(){
			inCol.setCellValueFactory(new PropertyValueFactory<>("clock_in"));
			outCol.setCellValueFactory(new PropertyValueFactory<>("clock_out"));
			idCol.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
	    }
	    
//---------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------
	    @FXML
	    private TableView<Employee> AllClockedTable;
	    
	    @FXML
	    private TableColumn<Employee, String> inAllCol;

	    @FXML
	    private TableColumn<Employee, String> outAllCol;

	    @FXML
	    private TableColumn<Employee, String> idAllCol;

	    private void initAllCol(){
			inAllCol.setCellValueFactory(new PropertyValueFactory<>("clock_in"));
			outAllCol.setCellValueFactory(new PropertyValueFactory<>("clock_out"));
			idAllCol.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
	    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createConnection();
		initCol();
		initAllCol();
		refreshTable();
		refreshAllTable();
	}
	
	void refreshAllTable(){
		try{
			list.clear(); //clear list at start of the new update
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM clock");
			while(rs.next()){
				String clock_in = rs.getString("clock_in");
				String clock_out = rs.getString("clock_out");
				String emp_id = rs.getString("emp_id");
				System.out.println(clock_in + " " + clock_out + " " +emp_id);
				list.add(new Employee(clock_in, clock_out, emp_id));
			}
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		AllClockedTable.getItems().setAll(list);

	}
	
	void createConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeedb", "root", "19Fender94");	
			System.out.println("Database Connection Success");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class Employee{
    	private final SimpleStringProperty clock_in;
    	private final SimpleStringProperty clock_out;
    	private final SimpleStringProperty emp_id;
    	public Employee(String clock_in, String clock_out, String emp_id){
    		this.clock_in = new SimpleStringProperty(clock_in);
    		this.clock_out = new SimpleStringProperty(clock_out);
    		this.emp_id = new SimpleStringProperty(emp_id);
    	}
		public String getClock_in() {
			return clock_in.get();
		}
		public String getClock_out() {
			return clock_out.get();
		}
		public String getEmp_id() {
			return emp_id.get();
		}
    	
    	
    }

}
