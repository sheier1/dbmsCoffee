package coffee.ui.manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagerInventoryController implements Initializable{

	//*******************************************
	//*****Remove Items Tab******************************
	 @FXML
	    private TableView<?> InventoryTable;

	    @FXML
	    private TableColumn<?, ?> itemCol;

	    @FXML
	    private TableColumn<?, ?> qtyCol;

	    @FXML
	    private TableColumn<?, ?> expDateCol;
	    //*****************************************************
	    //*******************************************************
	    
	    
	    //***********************************************
	    //***********add Items Tab*****************************************
    @FXML
    private Button addButtton;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private TextField itemQuantityField;

    @FXML
    private TextField itemUnitsField;

    @FXML
    private SplitMenuButton itemSelect;

    @FXML
    void addNewItem(ActionEvent event) {

    }
	//***********************************************************
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
