/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationassessmentsys;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Home
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML private ListView<Button> listView;
   // @FXML private TableColumn<Department, String> DepartName;
    
    @FXML
    private Label label;
    
    @FXML
     
    
    String p;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //DepartName.setCellValueFactory(new PropertyValueFactory<Department, String>("departName"));
     
      //tableView.setItems(getDepart());
      listView.setItems(getDepart());
     
    }

    public ObservableList<Button>  getDepart()
    {
        ObservableList<Button> depart = FXCollections.observableArrayList();
        //depart.add(new Department("Frank"));
        //depart.add(new Department("Rebecca"));
        //depart.add(new Department("Mr."));
        
        //return depart;
        Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
        
        // Step 1: Loading or registering Oracle JDBC driver class
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException cnfex) {

			System.out.println("Problem in loading or registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}

		// Step 2: Opening database connection
                	try {

			String msAccessDBName = "C:\\Users\\Davanna\\Desktop\\Versions of project\\ExaminationAssessmentSys - V7-March-28th\\src\\examinationassessmentsys\\database\\EAS1.accdb";//call a function to return the string
			String dbURL = "jdbc:ucanaccess://" + msAccessDBName; 

			// Step 2.A: Create and get connection using DriverManager class
			connection = DriverManager.getConnection(dbURL); 

			// Step 2.B: Creating JDBC Statement 
			statement = connection.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM Department");
                         
			int count=0;
                         String [] name = new String[10];
			// processing returned data and printing into console
			while(resultSet.next()) {
				//System.out.println(resultSet.getString(1) + "\t" + 
						//resultSet.getString(2) + "\t" + 
						//resultSet.getString(3) + "\t" +
						//resultSet.getString(4));
                            
                             count++;                
                                            
                            Button link = new Button();
                           link.setText(resultSet.getString("DeptFullname"));
                            name[count] = resultSet.getString("DeptFullname");
                           System.out.print(name[count]);
                           link.setStyle("-fx-font: 16 arial; -fx-border-color: transparent; -fx-background-color: transparent;");
                           final String y = name[count];
                           link.setOnAction((ActionEvent e) -> {
                            
                              /* Parent home_page_p = null;
                               try {
                                   FXMLLoader loader = new FXMLLoader();
                                   home_page_p = FXMLLoader.load(getClass().getResource("DepartCourses.fxml"));
                               } catch (IOException ex) {
                                   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                               }
                               DepartCoursesController display = new DepartCoursesController();
                               display = loader.getController();
                               Scene home_page_s = new Scene (home_page_p);
                               home_page_s.getStylesheets().add(getClass().getResource("departcourses.css").toExternalForm());
                               
                               Stage a_stage =(Stage) ((Node) e.getSource()).getScene().getWindow();
                               a_stage.setScene(home_page_s);
                               a_stage.setTitle("Examination Assessment System");
                               a_stage.show(); */ 
                         /*  FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DepartCourses.fxml"));
        Parent tableViewParent = null;
                                try {
                                    tableViewParent = loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                }
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        DepartCoursesController controller = loader.getController();
        controller.initData(y);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();*/
         Scene scene = null;                
                         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DepartCourses.fxml"));
        loader.setControllerFactory((Class<?> controllerType) -> {
            if (controllerType ==  DepartCoursesController.class) {
                 DepartCoursesController controller = new  DepartCoursesController();
                controller.initData(y);
                return controller ;
            } else {
                try {
                    return controllerType.newInstance();
                } catch (Exception d) {
                    throw new RuntimeException(d);
                }
            }
        });
        Parent tableViewParent = null;
          tableViewParent = loader.load();
         Scene tableViewScene = new Scene(tableViewParent);
          Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.setTitle("Examination Assessment System");
        window.setMaximized(true);
        window.show();
        DepartCoursesController controller = loader.< DepartCoursesController>getController();
        controller.initData(y);
        
        
    } catch (Exception ex) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
                           
                           
                           }) ;                 
                                                
                            depart.add(link);                    
                                             
			}
                        
                        
		}
		catch(SQLException sqlex){
		}
		finally {

			// Step 3: Closing database connection
			try {
				if(null != connection) {

					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connection
					connection.close();
				}
			}
			catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
                   return depart;     
    }
    
}
