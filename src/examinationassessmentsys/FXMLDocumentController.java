/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationassessmentsys;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

/**
 *
 * @author Home
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML private TableView<Department> tableView;
    @FXML private TableColumn<Department, String> DepartName;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     DepartName.setCellValueFactory(new PropertyValueFactory<Department, String>("departName"));
     
      tableView.setItems(getDepart());
     
    }

    public ObservableList<Department>  getDepart()
    {
        ObservableList<Department> depart = FXCollections.observableArrayList();
        depart.add(new Department("Frank"));
        depart.add(new Department("Rebecca"));
        depart.add(new Department("Mr."));
        
        return depart;
    }
    
}
