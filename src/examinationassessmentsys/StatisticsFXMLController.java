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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class StatisticsFXMLController implements Initializable {

   private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button menu;
    @FXML
    private VBox drawer;

    @FXML
    private LineChart<String, Number> invoiceChart;
    @FXML
    CategoryAxis ixAxis;
    @FXML
    private BarChart<String, Double> productsChart;
    @FXML
    CategoryAxis pxAxis;
    
    @FXML
    private PieChart stockChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       drawerAction();
        loadInvoiceChart();
        loadProductsChart();
        loadStockChart();
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
      private void loadInvoiceChart() {

        
    }
      
        private void loadProductsChart() {
        }
        private void loadStockChart(){
            
        }  
        
        
         @FXML
    public void productAction(ActionEvent event) throws Exception {
        
    }
     @FXML
    public void categoryAction(ActionEvent event) throws Exception {

       
    }

    @FXML
    public void purchaseAction(ActionEvent event) throws Exception {

        
    }

    @FXML
    public void supplierAction(ActionEvent event) throws Exception {

        
    }
    @FXML
    public void reportAction(ActionEvent event) throws Exception {
       
    }
         
    
}
