package coffee.ui.mangdemp;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MangdEmpController implements Initializable{

	Connection con;
	
	ObservableList<Person> list = FXCollections.observableArrayList();
	
	@FXML
    private AnchorPane rootPane;
	
	@FXML
	private TableView<Person> CurrentEmployeeTable;

    @FXML
    private TableColumn<Person, String> numIDCol;

    @FXML
    private TableColumn<Person, String> FnameCol;

    @FXML
    private TableColumn<Person, String> LnameCol;

    @FXML
    private TableColumn<Person, String> bdayCol;

    @FXML
    private TableColumn<Person, Double> payrateCol;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createConnection();
		initCol();
		refreshTable();
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
	
	void refreshTable(){
		try{
			list.clear(); //clear list at start of the new update
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT num_id, Fname, Lname, bday, pay_rate FROM person p INNER JOIN employee e on e.emp_id = p.num_id");

			while(rs.next()){
				String num_id = rs.getString("num_id");
				String Fname = rs.getString("Fname");
				String Lname = rs.getString("Lname");
				String bday = rs.getString("bday");
				Double pay_rate = rs.getDouble("pay_rate");
				//System.out.println(clock_in + " " + clock_out + " " +emp_id);
				list.add(new Person(num_id, Fname, Lname, bday, pay_rate));
			}
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		CurrentEmployeeTable.getItems().setAll(list);

	}
	
	private void initCol(){
		numIDCol.setCellValueFactory(new PropertyValueFactory<>("num_id"));
		FnameCol.setCellValueFactory(new PropertyValueFactory<>("Fname"));
		LnameCol.setCellValueFactory(new PropertyValueFactory<>("Lname"));
		bdayCol.setCellValueFactory(new PropertyValueFactory<>("bday"));
		payrateCol.setCellValueFactory(new PropertyValueFactory<>("pay_rate"));
    }
	
	  public static class Person{
	    	private final SimpleStringProperty num_id;
	    	private final SimpleStringProperty Fname;
	    	private final SimpleStringProperty Lname;
	    	private final SimpleStringProperty bday;
	    	private final SimpleDoubleProperty pay_rate;
	    	public Person(String num_id, String Fname, String Lname, String bday, Double pay_rate){
	    		this.num_id = new SimpleStringProperty(num_id);
	    		this.Fname = new SimpleStringProperty(Fname);
	    		this.Lname = new SimpleStringProperty(Lname);
	    		this.bday = new SimpleStringProperty(bday);
	    		this.pay_rate = new SimpleDoubleProperty(pay_rate);
	    	}
	    	
			public String getNum_id() {
				return num_id.get();
			}
			public String getFname() {
				return Fname.get();
			}
			public String getLname() {
				return Lname.get();
			}
			public String getBday() {
				return bday.get();
			}
			public Double getPay_rate() {
				return pay_rate.get();
			}
			
	    	
	    }

}
