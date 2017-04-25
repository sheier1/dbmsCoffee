package coffee.ui.manager;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManagerLoginController implements Initializable{

	Connection con;
	
		@FXML
		private AnchorPane loginRootPane;
		
	    @FXML
	    private JFXPasswordField managerPassField;

	    @FXML
	    private JFXTextField managerIDField;

	    @FXML
	    private JFXButton loginButton;

	    @FXML
	    private JFXButton cancelButton;

	    @FXML
	    private Label errorLabel;

	    @FXML
	    void attemptLogin(ActionEvent event) {
	    	String dbID;
	    	String dbPwd;
	    	try{
	    		String id = new String(managerIDField.getText());
	    		String pwd = new String(managerPassField.getText());
	    	
	    		Statement stmt = con.createStatement();
	    		ResultSet rs = stmt.executeQuery("SELECT man_id, pwd FROM MANAGER");
	    		while(rs.next()){
	    			dbID = rs.getString("man_id");
	    			dbPwd = rs.getString("pwd");
	    			if(id.equals(dbID) && pwd.equals(dbPwd)){
	    				errorLabel.setText("Works!");
	    				System.out.println("Found!");
	    				//**Load new view
	    				Stage stage = (Stage) loginRootPane.getScene().getWindow();
	    		    	stage.close();
	    		    	loadWindow("/coffee/ui/manager/manager_screen.fxml", "");
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
	    	Stage stage = (Stage) loginRootPane.getScene().getWindow();
	    	stage.close();
	    	loadWindow("/coffee/ui/main/coffee_main.fxml", "");

	    }
	
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
