package examinationassessmentsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author jaret_000
 */
public class LoginViewController implements Initializable {

   /* @FXML private TextField volunteerIDTextField;
    @FXML private PasswordField pwField;
    @FXML private Label errMsgLabel;
    
    public void loginButtonPushed(ActionEvent event) throws IOException, NoSuchAlgorithmException
    {
       /* //query the database with the volunteerID provided, get the salt
        //and encrypted password stored in the database
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        int volunteerNum = Integer.parseInt(volunteerIDTextField.getText());
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/volunteer", "student", "student");
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM volunteers WHERE volunteerID = ?";
            
            //3.  prepare the statement
            ps = conn.prepareStatement(sql);
            
            //4.  bind the volunteerID to the ?
            ps.setInt(1, volunteerNum);
            
            //5. execute the query
            resultSet = ps.executeQuery();
            
            //6.  extract the password and salt from the resultSet
            String dbPassword=null;
            byte[] salt = null;
            boolean admin = false;
            Volunteer volunteer = null;
            
            while (resultSet.next())
            {
                dbPassword = resultSet.getString("password");
                
                Blob blob = resultSet.getBlob("salt");
                
                //convert into a byte array
                int blobLength = (int) blob.length();
                salt = blob.getBytes(1, blobLength);
                
                admin = resultSet.getBoolean("admin");
                
                volunteer = new Volunteer(resultSet.getString("firstName"),
                                                       resultSet.getString("lastName"),
                                                       resultSet.getString("phoneNumber"),
                                                       resultSet.getDate("birthday").toLocalDate(),
                                                       resultSet.getString("password"),
                                                       resultSet.getBoolean("admin"));
                volunteer.setVolunteerID(resultSet.getInt("VolunteerID"));
                volunteer.setImageFile(new File(resultSet.getString("imageFile")));  
            }
            
            //convert the password given by the user into an encryted password
            //using the salt from the database
            String userPW = PasswordGenerator.getSHA512Password(pwField.getText(), salt);
            
            SceneChanger sc = new SceneChanger();
            
            if (userPW.equals(dbPassword))
                SceneChanger.setLoggedInUser(volunteer);
            
            //if the passwords match - change to the VolunteerTableView
            if (userPW.equals(dbPassword) && admin)
                sc.changeScenes(event, "VolunteerTableView.fxml", "All Volunteers");
            else if (userPW.equals(dbPassword))
            {
                //create an instance of the controller class for log hours view
                LogHoursViewController controllerClass = new LogHoursViewController();
                sc.changeScenes(event, "LogHoursView.fxml", "Log Hours", volunteer, controllerClass);
            }
            else
                //if the do not match, update the error message
                errMsgLabel.setText("The volunteerID and password do not match");
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        
    }
        
    /**
     * Initializes the controller class.
     */
    
     @FXML
    public void cancelAction(ActionEvent event) {
        //resetFields();
    }

    @FXML
    public void closeAction(ActionEvent event) {
       // Platform.exit();
    }

    @FXML
    public void minusAction(ActionEvent event) {
      //  Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
       // stage.setIconified(true);
    }
    
 @FXML
    public void loginAction(ActionEvent event) throws Exception {
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // errMsgLabel.setText("");
    }  
    
}
