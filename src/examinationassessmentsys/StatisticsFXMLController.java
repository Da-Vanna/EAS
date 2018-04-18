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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.FileOutputStream;
import java.io.IOException; 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Scrollbar;
import static java.awt.SystemColor.window;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.util.Calendar;
import javafx.geometry.Pos;
import javafx.stage.Modality;


/**
 * FXML Controller class
 *
 * @author Home
 */
public class StatisticsFXMLController implements Initializable {
 
 @FXML
private AnchorPane mainPane,tablePane,graphPane,summPane; 
 
 
// @FXML
//private VBox courseDetail;
    @FXML
    private TabPane tabPaneS;
    @FXML
    private Tab main;
    @FXML
    private Tab tables;
    @FXML
    private Tab graphs;
    @FXML
    private Tab summary;
    @FXML
    private Button dlreport;
    
   @FXML
    private TableView sView;
   
   @FXML
    private TableView summaryT;
   @FXML
    private ScrollPane gScroll;  
    @FXML
    private ScrollPane pScroll; 
    
     @FXML
    private ScrollPane Scroll2; 
     
     @FXML
    private Text reportFor;
     
   String course;
   String name;
   String code;
   WritableImage image;
   WritableImage imagePie; 
   int counta=0, countaMinus=0,countaPlus=0,countb=0, countbMinus=0,countbPlus=0,countcPlus=0,countc=0,countf1=0,countf2=0,countf3=0;
   
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     try {
         // ResultSet rep = getResult("SELECT * FROM Courses WHERE CourseCode = '"+code+"'");
          ResultSet ser3 =getResult("SELECT * FROM StudentGrade where YearSat='2017/2018'");
      
        while(ser3.next()) {
            switch (ser3.getString("LetterGrade")){
                case "A-":
               countaMinus++;
                    
                case "A":
               counta++;
                    
               case "A+":
               countaPlus++;     
               
                case "B-":
               countbMinus++;    
                   
               case "B":
                    countb++;
                   
               case "B+":
               countbPlus++;
                   
                 case "C":
                    countc++;
                   
               case "C+":
               countcPlus++;   
                   
                 case "F1":
               countf1++;  
                     
                  case "F2":
               countf2++; 
                      
                case "F3":
               countf3++; 
                   
                   
                    
            }
            
            //System.out.print(ser3.getString("LetterGrade"));
        
        }
        System.out.println("a count"+counta);
        System.out.println("b count"+countb);
         
         summaryT.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
         BarChart bchart = getBar();
         //image = bchart.snapshot(new SnapshotParameters(), null);
         //Scene scene = new Scene(bchart, 800, 600);
         //image = scene.snapshot(null);
         gScroll.setContent(bchart);
         bchart.setAnimated(false);
         Scene sceneBar = new Scene(bchart,600,300);
         image = sceneBar.snapshot(null);
         PieChart pie = getPie();
         pScroll.setContent(pie);
         pie.setAnimated(false);
         Scene scenePie = new Scene(pie,600,300);
         imagePie = scenePie.snapshot(null);
         
         PieChart p= getPie();
         Scroll2.setContent(p);
         
         
         
         //Text reportText = new Text();
         //reportText.setId("intro");
         // reportText.setText("Course Code: \n"
         //    + "\n"
         //    + "Course Name: \n"
         //    + "\n"
         //   + "Weightings: ");
         
         //Label slabel = new Label ("Report for" );
         
         //courseDetail.getChildren().add(reportText);
         // BorderPane rPane =new BorderPane ();
         
         //TableView rTable = new TableView();
         //rTable.setEditable(false);
         /*TableColumn yearCol = new TableColumn("Year");
         TableColumn maxCol = new TableColumn("MAX%");
         TableColumn minCol = new TableColumn("MIN%");
         TableColumn avgCol = new TableColumn("AVG");
         TableColumn modeCol = new TableColumn("MODE");
         TableColumn medianCol = new TableColumn("MEDIAN");
         TableColumn noRegCol = new TableColumn("NO.REG");
         TableColumn noWroteCol = new TableColumn("NO.WROTE");
         TableColumn noPassCol = new TableColumn("NO.PASSED");
         TableColumn noFailCol = new TableColumn("NO.FAILED");
         TableColumn passPerCol = new TableColumn("% PASS");
         TableColumn failPerCol = new TableColumn("%FAIL");
         TableColumn f3Col = new TableColumn("0-29 F3");
         TableColumn f2Col = new TableColumn("30-39 F2");
         TableColumn f1Col = new TableColumn("40-49 F1");
         TableColumn CCol = new TableColumn("50-54 C");
         TableColumn CplusCol = new TableColumn("55-59 C+");
         TableColumn BminusCol = new TableColumn("60-64 B-");
         TableColumn BCol = new TableColumn("65-69 B");
         TableColumn BplusCol = new TableColumn("70-74 B+");
         TableColumn AminusCol = new TableColumn("75-79 A-");
         TableColumn ACol = new TableColumn("80-89 A");
         TableColumn AplusCol = new TableColumn("90-100 A+");*/
         // ScrollPane sp = new ScrollPane();
         //sView.getColumns().addAll(yearCol,maxCol,minCol,avgCol,modeCol,medianCol,noRegCol,noWroteCol,noPassCol,noFailCol,passPerCol,failPerCol,f3Col,f2Col,f1Col,CCol,CplusCol,BminusCol,BCol,BplusCol,AminusCol,ACol,AplusCol);
         //rTable.setTableMenuButtonVisible(true);
         //sp.setContent(rTable);
         //sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
         //sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
         //sp.setFitToHeight(true);
         //sp.setHmax(3);
         // rPane.setRight(sp);
         //BorderPane.setMargin(sp, new Insets(0,0,10,10));
         // rPane.getChildren().add(rTable);
         
         //tables.setContent(rPane);
     } catch (SQLException ex) {
         Logger.getLogger(StatisticsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
     }
              
    }  
    public BarChart getBar() throws SQLException{
        
     final String a = "A";
     final String aPlus = "A+";
     final String aMinus = "A-";
    final  String b = "B";
    final String bPlus = "B+";
    final String bMinus = "B-";
    final  String c = "C";
    final String cPlus = "C+";
    final  String fOne = "F1";
    final  String fTwo = "F2";
    final String fThree = "F3";
       final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Course Summary");
        xAxis.setLabel("Grade");       
        yAxis.setLabel("Value");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");  
        series1.getData().add(new XYChart.Data(aPlus, 2));
        series1.getData().add(new XYChart.Data(a, 2));
        series1.getData().add(new XYChart.Data(aMinus, 1));
        series1.getData().add(new XYChart.Data(bPlus, 3));
        series1.getData().add(new XYChart.Data(b, 2));
        series1.getData().add(new XYChart.Data(bMinus, 4));
        series1.getData().add(new XYChart.Data(cPlus, 8));
        series1.getData().add(new XYChart.Data(c, 1));
        series1.getData().add(new XYChart.Data(fOne, 3.15));
        series1.getData().add(new XYChart.Data(fTwo, 1));   
        series1.getData().add(new XYChart.Data(fThree, 2));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
         series2.getData().add(new XYChart.Data(aPlus, 2));
        series2.getData().add(new XYChart.Data(a, 2));
        series2.getData().add(new XYChart.Data(aMinus, 2));
        series2.getData().add(new XYChart.Data(bPlus, 2));
        series2.getData().add(new XYChart.Data(b, 2));
        series2.getData().add(new XYChart.Data(bMinus, 8));
        series2.getData().add(new XYChart.Data(cPlus, 5));
        series2.getData().add(new XYChart.Data(c, 10));
        series2.getData().add(new XYChart.Data(fOne, 3));
        series2.getData().add(new XYChart.Data(fTwo, 12));   
        series2.getData().add(new XYChart.Data(fThree, 2)); 
        
        ResultSet ser3 =getResult("SELECT * FROM StudentGrade where YearSat='2017/2018'");
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        int count =0;
        while(ser3.next()) {
            if (ser3.getString("LetterGrade").equals("A")){
               count+=1;
            }
            
            //System.out.print(ser3.getString("LetterGrade"));
        
        }
        System.out.print(count);
        
        series3.getData().add(new XYChart.Data(aPlus, countaPlus));
        series3.getData().add(new XYChart.Data(a, counta));
        series3.getData().add(new XYChart.Data(aMinus, countaMinus));
        series3.getData().add(new XYChart.Data(bPlus, countbPlus));
        series3.getData().add(new XYChart.Data(b, countb));
        series3.getData().add(new XYChart.Data(bMinus, countbMinus));
        series3.getData().add(new XYChart.Data(cPlus, countcPlus));
        series3.getData().add(new XYChart.Data(c, countc));
        series3.getData().add(new XYChart.Data(fOne, countf1));
        series3.getData().add(new XYChart.Data(fTwo, countf2));   
        series3.getData().add(new XYChart.Data(fThree, countf3)); 
        bc.getData().addAll(series1, series2, series3);
     return bc;
    }
    
    public PieChart getPie(){
        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");
return chart;
}
    
     public ResultSet  getResult(String query)
    {
    ResultSet result =null;
    Connection connection = null;
    Statement statement = null;
    
    try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException cnfex) {

			System.out.println("Problem in loading or registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}
    try {

			String msAccessDBName = "C:\\Users\\Davanna\\Desktop\\Versions of project\\ExaminationAssessmentSys - V7-March-28th\\src\\examinationassessmentsys\\database\\EAS1.accdb";//call a function to return the string
			String dbURL = "jdbc:ucanaccess://" + msAccessDBName; 

			// Step 2.A: Create and get connection using DriverManager class
			connection = DriverManager.getConnection(dbURL); 

			// Step 2.B: Creating JDBC Statement 
			statement = connection.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			result = statement.executeQuery(query);

  
    }catch(SQLException sqlex){
		}
	  return result;	
    }
     
     
     
    @FXML
    private void download(ActionEvent event) throws DocumentException, FileNotFoundException {
    try {
              
            OutputStream file = new FileOutputStream(new File("D:\\Test11.pdf"));


            Document document = new Document();

            PdfWriter.getInstance(document, file);


            document.open();
            // the code below would also give the current date
            //Calendar cal = Calendar.getInstance();
             //int day = cal.get(Calendar.DATE);
            // document.add(new Paragraph(String.valueOf(day)));
            
            //Font font = new Font(FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.BLACK);
             Font font = new Font(Font.getFamily("TIMES_ROMAN"), 11f,Font.UNDERLINE);
             Font font2 = new Font(Font.getFamily("TIMES_ROMAN"), 10f);
             Font font1 = new Font(Font.getFamily("TIMES_ROMAN"), 14f,Font.BOLD|Font.UNDERLINE);
             Font font3 = new Font(Font.getFamily("TIMES_ROMAN"), 12f, Font.BOLD,BaseColor.BLACK);
             
            
            ResultSet rs= getResult("SELECT * FROM Courses WHERE CourseCode = 'ACCT1002' ");
            Paragraph time = new Paragraph(new Date().toString());
            time.setAlignment(Element.ALIGN_RIGHT);
            document.add(time);
            document.add( Chunk.NEWLINE );
            //document.addTitle("Report for [Course Code][Title][Academic Year]");
            //document.addSubject("Couse Components");
           // document.addSubject("Couse Weighting");
            
            Paragraph heading = new Paragraph("Report for "+ course,font1);
            heading.setAlignment(Element.ALIGN_CENTER);
            document.add(heading); 
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            
            document.add(new Paragraph("General Summary of Results & Trends:",font3));
             document.add( Chunk.NEWLINE );
            
            PdfPTable table = new PdfPTable(new float[] { 3, 2, 2,2, 2,2, 2,2, 2,2, 2,2, 2,2, 2,2, 2,2, 2,2,2,2,2});
            table.setWidthPercentage(100);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase(new Chunk("YEAR",font3)));
            cell.setRotation(90);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            PdfPCell cell1;
            cell1 = new PdfPCell(new Phrase(new Chunk("MAX%",font3)));
            cell1.setRotation(90);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell1);
            PdfPCell cell2;
            cell2 = new PdfPCell(new Phrase(new Chunk("MIN%",font3)));
            cell2.setRotation(90);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell2);
            PdfPCell cell3;
            cell3 = new PdfPCell(new Phrase("AVG",font3));
            cell3.setRotation(90);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell3);
            PdfPCell cell4;
            cell4 = new PdfPCell(new Phrase("MODE",font3));
            cell4.setRotation(90);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell4);
            
             PdfPCell cell5;
            cell5 = new PdfPCell(new Phrase("MEDIAN",font3));
            cell5.setRotation(90);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell5);
            PdfPCell cell6;
            cell6 = new PdfPCell(new Phrase("NO.REGIS",font3));
            cell6.setRotation(90);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell6);
             PdfPCell cell7;
            cell7 = new PdfPCell(new Phrase("NO.WROTE",font3));
            cell7.setRotation(90);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell7);
            
            PdfPCell cell8;
            cell8 = new PdfPCell(new Phrase("NO.PASSED",font3));
            cell8.setRotation(90);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell8);
            
             PdfPCell cell9;
            cell9 = new PdfPCell(new Phrase("NO.FAILED",font3));
            cell9.setRotation(90);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell9);
            PdfPCell cell10;
            cell10 = new PdfPCell(new Phrase("%PASS",font3));
            cell10.setRotation(90);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell10);
            
            PdfPCell cell11;
            cell11 = new PdfPCell(new Phrase("%FAIL",font3));
            cell11.setRotation(90);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell11);
            PdfPCell cell12;
            cell12 = new PdfPCell(new Phrase("0-29 F3",font3));
            cell12.setRotation(90);
            cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell12);
             PdfPCell cell13;
            cell13 = new PdfPCell(new Phrase("30-39 F2",font3));
            cell13.setRotation(90);
            cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell13);
             PdfPCell cell14;
            cell14 = new PdfPCell(new Phrase("40-49 F1",font3));
            cell14.setRotation(90);
            cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell14);
             PdfPCell cell15;
            cell15 = new PdfPCell(new Phrase("50-54 C",font3));
            cell15.setRotation(90);
            cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell15);
             PdfPCell cell16;
            cell16 = new PdfPCell(new Phrase("55-59 C+",font3));
            cell16.setRotation(90);
            cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell16);
            
             PdfPCell cell17;
            cell17 = new PdfPCell(new Phrase("60-64 B-",font3));
            cell17.setRotation(90);
            cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell17);
             PdfPCell cell18;
            cell18 = new PdfPCell(new Phrase("65-69 B",font3));
            cell18.setRotation(90);
            cell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell18);
            
             PdfPCell cell19;
            cell19 = new PdfPCell(new Phrase("70-74 B+",font3));
            cell19.setRotation(90);
            cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell19);
            
             PdfPCell cell20;
            cell20 = new PdfPCell(new Phrase("75-79 A-",font3));
            cell20.setRotation(90);
            cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell20);
            
             PdfPCell cell21;
            cell21 = new PdfPCell(new Phrase("80-89 A",font3));
            cell21.setRotation(90);
            cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell21);
           
             PdfPCell cell22;
            cell22 = new PdfPCell(new Phrase("90-100 A+",font3));
            cell22.setRotation(90);
            cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell22);
            document.add(table);
            Paragraph tableE = new Paragraph("Table 1. Course results summary",font);
            tableE.setAlignment(Element.ALIGN_CENTER);
            document.add(tableE);
            document.add( Chunk.NEWLINE );
            
            ByteArrayOutputStream  byteOutput = new ByteArrayOutputStream();

            ImageIO.write( SwingFXUtils.fromFXImage( image, null ), "png", byteOutput );

            com.itextpdf.text.Image  graph;
            graph = com.itextpdf.text.Image.getInstance( byteOutput.toByteArray() );
            graph.scaleToFit(500, 400);
      
            
             ByteArrayOutputStream  byteOutputPie = new ByteArrayOutputStream();

            ImageIO.write( SwingFXUtils.fromFXImage( imagePie, null ), "png", byteOutputPie );

            com.itextpdf.text.Image  pie;
            pie = com.itextpdf.text.Image.getInstance( byteOutputPie.toByteArray() );
            pie.scaleToFit(500, 400);
             //graph.setAlignment(Image.ALIGN_CENTER);
    
            document.add( Chunk.NEWLINE );
            //document.add(graph);
            document.add(new Chunk(graph,0,0,true));
            Paragraph barG = new Paragraph("Figure 1. Frequency distribution of course total (/100%) by grade and mark %",font);
            barG.setAlignment(Element.ALIGN_CENTER);
            document.add(barG);
            
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            PdfPTable pPic = new PdfPTable(1);
            PdfPCell pic;
            pic = new PdfPCell(pie);
            pPic.addCell(pic);
            //document.add(pie);
            //document.add(pPic);document.add(pPic);
            document.add(new Chunk(pie,0,0,true));
            Paragraph pieG = new Paragraph("Figure 2. Display of passed or faiure rate",font);
            pieG.setAlignment(Element.ALIGN_CENTER);
            document.add(pieG);
            document.add(Chunk.NEWLINE);
            
            
            
            
            document.add(new Paragraph("Course Components Summary & Trends:",font3));
            document.add( Chunk.NEWLINE );
             PdfPTable summaryTable = new PdfPTable(new float[] { 3, 1, 1,1});
             summaryTable.setWidthPercentage(100);
             PdfPCell comp;
             Phrase ph = new Phrase ("Course component");
             ph.add(Chunk.NEWLINE);
             ph.add(new Chunk("(% weighting)",font3));
             comp = new PdfPCell(ph);
             comp.setVerticalAlignment(Element.ALIGN_MIDDLE);
             comp.setRowspan(2);
             summaryTable.addCell(comp);
             
             PdfPCell weight;
             weight = new PdfPCell(new Phrase("Mean (+SD) as % of actual component weight",font3));
             weight.setColspan(3);
             summaryTable.addCell(weight);
             
             PdfPCell yr1;
              Phrase phyr1 = new Phrase ("year1",font3);
             phyr1.add(Chunk.NEWLINE);
             phyr1.add("N=");
             yr1 = new PdfPCell(phyr1);
             summaryTable.addCell(yr1);
             
            PdfPCell yr2;
            Phrase phyr2 = new Phrase ("year2",font3);
             phyr2.add(Chunk.NEWLINE);
             phyr2.add("N=");
             yr2 = new PdfPCell(phyr2);
             summaryTable.addCell(yr2);

            PdfPCell yr3;
            Phrase phyr3 = new Phrase ("year3",font3);
             phyr3.add(Chunk.NEWLINE);
             phyr3.add("N=");
             yr3 = new PdfPCell(phyr3);
             summaryTable.addCell(yr3);

             document.add(summaryTable);
             document.add( Chunk.NEWLINE );
            
             document.add(new Paragraph("General Issues & Recommendations:",font3));
            
            while (rs.next()) {
        	// add a country to the document as a Chunk
            document.add(new Chunk(rs.getString("CourseCode")));
            document.add(new Chunk(" "));
            Chunk id = new Chunk(rs.getString("CName"), font);
            // with a background color
            id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
            // and a text rise
            id.setTextRise(6);
            document.add(id);
            document.add(Chunk.NEWLINE);
        }



            document.close();

            file.close();
            
            display();


        } catch (Exception e) {


            e.printStackTrace();

        }  



    }
    
    public static void display() {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Report");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Document downloaded successfully");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void initCourse(String course,String name,String code){
       //this.name.setText(name);
       // System.out.print(name);
        this.course= course;
        this.name=name;
        this.code=code;
        System.out.print(course);
    }

    @FXML
    private void goToCourse(ActionEvent event) throws IOException {
         
    }

  /* @FXML
    public void dAction(ActionEvent event) throws Exception {
        Parent home_page_p = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene home_page_s = new Scene (home_page_p);
        home_page_s.getStylesheets().add(getClass().getResource("appcss.css").toExternalForm());
        
        Stage a_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        a_stage.setScene(home_page_s);
        a_stage.setTitle("Examination Assessment System");
        a_stage.show();

        //authenticate(event);
        
        
    }*/
         
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
    
    @FXML
    public void cAction(ActionEvent event) throws Exception {
       Scene scene = null;                
                         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DepartCourses.fxml"));
        loader.setControllerFactory((Class<?> controllerType) -> {
            if (controllerType ==  DepartCoursesController.class) {
                 DepartCoursesController controller = new  DepartCoursesController();
                controller.initData(name);
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
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.setTitle("Examination Assessment System");
        window.setMaximized(true);
        window.show();
        DepartCoursesController controller = loader.< DepartCoursesController>getController();
        controller.initData(name);
        
        
    } catch (Exception ex) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
                           

        //authenticate(event);
        
        
    }
    
    
    
   
    
         
    
}
