package coffee.ui.employee;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmployeeLoginController implements Initializable{

	Connection con;
	public static String pubID;
	
    @FXML
    private AnchorPane empLoginRootPane;

    @FXML
    private JFXPasswordField employeePassField;

    @FXML
    private JFXTextField employeeIDField;

    @FXML
    private JFXButton empLoginButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label errorLabel;

    @FXML
    void attemptEmployeeLogin(ActionEvent event) {
    	String dbID;
    	String dbPwd;
    	try{
    		String id = new String(employeeIDField.getText());
    		String pwd = new String(employeePassField.getText());
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT emp_id, pwd FROM EMPLOYEE");
    		while(rs.next()){
    			dbID = rs.getString("emp_id");
    			dbPwd = rs.getString("pwd");
    			if(id.equals(dbID) && pwd.equals(dbPwd)){
    				errorLabel.setText("Works!");
    				System.out.println("Found!");
    				pubID = id;
    				//**Load new view
    				
    				Stage stage = (Stage) empLoginRootPane.getScene().getWindow();
    		    	stage.close();
    		    	loadWindow("/coffee/ui/employee/employee_screen.fxml", "");
    			}
    			else{
    				errorLabel.setText("ID not valid, Please Retry");
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) empLoginRootPane.getScene().getWindow();
    	stage.close();
    	loadWindow("/coffee/ui/main/coffee_main.fxml", "");
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createConnection();
	}

	
	void loadWindow(String loc, String title){
    	
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/organic-fairtrade-riseup.png")));
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
