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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class DepartCoursesController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TabPane tabPane ;
     
      
    String names;

    String [] courseName= {"Information Technology","Computer Science", "External courses"};
    String [] mainCourse ={"Information Technology","Computer Science"};
    
    
    
    
        


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String [] courseArray = null;
       
        String code = null;
        System.out.print(names);
        if (names.toLowerCase().contains("Comp".toLowerCase())){
         courseArray =courseName;
         //mainArray =mainCourse;
         code = "IT";
          addTab(courseArray,code);
        }
        else{
            Label unavailable = new Label("This Department is currently unavailable");
            AnchorPane unavail = new AnchorPane();
        AnchorPane.setTopAnchor(unavailable,100.0);
       AnchorPane.setLeftAnchor(unavailable, 50.0);
       AnchorPane.setRightAnchor(unavailable, 50.0);

        unavail.getChildren().add(unavailable);
         
            Tab none = new Tab ("Unavailable");
            none.setContent(unavailable);
            tabPane.getTabs().add(none);
            
      // mainTab();
        }
        
        
    }   
  
   
    private void addTab(String[]courseArray,String code ) {
      Label mLabel = new Label(names);
      String mQuery ="SELECT * FROM Courses c, DepartmentCourse d, Department dp where c.CourseCode = d.CourseCode AND d.DepartmentName=dp.DepartmentName AND dp.DeptFullname='"+names+"'";
       final ListView<Button> mListView = new ListView<>(getCourses(mQuery));
        Tab mTab = new Tab("All Courses");
         AnchorPane mPane = new AnchorPane();
        AnchorPane.setTopAnchor(mLabel , 20.0);
       AnchorPane.setLeftAnchor(mLabel, 50.0);
       AnchorPane.setRightAnchor(mLabel, 70.0);
       AnchorPane.setTopAnchor(mListView, 70.0);
       AnchorPane.setLeftAnchor(mListView, 5.0);
       AnchorPane.setRightAnchor(mListView, 5.0);
       AnchorPane.setBottomAnchor(mListView, 45.0);
       mPane.getChildren().add(mLabel);
        mPane.getChildren().add(mListView);
         mTab.setContent(mPane);
        tabPane.getTabs().add(mTab);
        
         for (String label : courseArray) {
             //String str =names;
             Label l = new Label(label);
             String query = null;
             if ("IT".equals(code)) {
                 if ("Information Technology".equals(label) || "Computer Science".equals(label)) {
                     query = "SELECT * FROM Courses WHERE DegreeName = '" + label + "'";
                 } else {
                     query = "SELECT * FROM Courses c, DepartmentCourse d, Department dp where c.CourseCode = d.CourseCode AND d.DepartmentName=dp.DepartmentName AND dp.DeptFullname='"+names+"' AND DegreeName <> 'Information Technology' AND DegreeName <> 'Computer Science'";
                 }
             }
             // String query = "SELECT * FROM Courses";
             final ListView<Button> cListView = new ListView<>(getCourses(query));
             Tab tab = new Tab(label);
             AnchorPane questionPane = new AnchorPane();
             questionPane.setId("questionPane");
             AnchorPane.setTopAnchor(l , 20.0);
             AnchorPane.setLeftAnchor(l, 50.0);
             AnchorPane.setRightAnchor(l, 70.0);
             AnchorPane.setTopAnchor(cListView, 70.0);
             AnchorPane.setLeftAnchor(cListView, 5.0);
             AnchorPane.setRightAnchor(cListView, 5.0);
             AnchorPane.setBottomAnchor(cListView, 45.0);
             questionPane.getChildren().add(l);
             questionPane.getChildren().add(cListView);
             tab.setContent(questionPane);
             tabPane.getTabs().add(tab);
         }
      
    } 
    public void initData(String name){
       //this.name.setText(name);
       // System.out.print(name);
        names= name;
        System.out.print(names);
    }
    
        public ObservableList<Button>  getCourses(String query)
    {
        ObservableList<Button> courses = FXCollections.observableArrayList();
        
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
			resultSet = statement.executeQuery(query);

			
			// processing returned data and printing into console
			while(resultSet.next()) {
				
                                                
                            Button link = new Button();
                            String code = resultSet.getString("CourseCode");
                            String cname = resultSet.getString("CName");
                            String fullname = code+" - "+cname;
                           link.setText(fullname);
                           link.setStyle("-fx-font: 16 arial; -fx-border-color: transparent; -fx-background-color: transparent;");
                           link.setOnAction((ActionEvent e) -> {
                                 try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsFXML.fxml"));
        loader.setControllerFactory((Class<?> controllerType) -> {
            if (controllerType ==  StatisticsFXMLController.class) {
                 StatisticsFXMLController controller = new  StatisticsFXMLController();
                controller. initCourse(fullname,names,code);
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
        StatisticsFXMLController controller = loader.< StatisticsFXMLController>getController();
        controller.initCourse(fullname,names,code); 
        
        
    } catch (Exception ex) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }   }) ;                 
                                                
                            courses.add(link);                    
                                             
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
                   return courses;     
    }

    @FXML
    public void dAction(ActionEvent event) throws Exception {
        Parent home_page_p = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene home_page_s = new Scene (home_page_p);
        home_page_s.getStylesheets().add(getClass().getResource("appcss.css").toExternalForm());
        
        Stage a_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        a_stage.setScene(home_page_s);
        a_stage.setTitle("Examination Assessment System");
        a_stage.setMaximized(true);
        a_stage.show();

        //authenticate(event);
        
        
    }
    
    
}
