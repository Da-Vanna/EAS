/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationassessmentsys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.layout.HBox;
 import javafx.scene.paint.Color;
 import javafx.stage.Stage; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Home
 */
public class ExaminationAssessmentSys extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
      

        Scene scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add(getClass().getResource("appcss.css").toExternalForm());
        stage.setTitle("Examination Assessment System");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       /* Connection connection = null;
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

			String msAccessDBName = "C:/Users/Home/Desktop/EAS1.accdb";
			String dbURL = "jdbc:ucanaccess://" + msAccessDBName; 

			// Step 2.A: Create and get connection using DriverManager class
			connection = DriverManager.getConnection(dbURL); 

			// Step 2.B: Creating JDBC Statement 
			statement = connection.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM Courses");

			System.out.println("ID\tName\t\t\tAge\tMatches");
			System.out.println("==\t================\t===\t=======");

			// processing returned data and printing into console
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1) + "\t" + 
						resultSet.getString(2) + "\t" + 
						resultSet.getString(3) + "\t" +
						resultSet.getString(4));
			}
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
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
		}*/
    }
    
}
