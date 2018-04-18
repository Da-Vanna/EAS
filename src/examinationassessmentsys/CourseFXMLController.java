/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationassessmentsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class CourseFXMLController implements Initializable {
 @FXML
    private Button menu;
    @FXML
    private VBox drawer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawerAction();
    }  
    
     private void drawerAction() {
    
        TranslateTransition openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawer);
        menu.setOnAction((ActionEvent evt) -> {
            if (drawer.getTranslateX() != 0) {
                openNav.play();
                menu.getStyleClass().remove("hamburger-button");
                menu.getStyleClass().add("open-menu");
            } else {
                closeNav.setToX(-(drawer.getWidth()));
                closeNav.play();
                menu.getStyleClass().remove("open-menu");
                menu.getStyleClass().add("hamburger-button");
            }
        });
    }
    
}
