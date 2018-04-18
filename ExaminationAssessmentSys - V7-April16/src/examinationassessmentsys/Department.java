/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationassessmentsys;

import java.time.LocalDate;
import java.time.Period;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Home
 */
public class Department {
  private SimpleStringProperty departName;
   // private LocalDate birthday;
    //private Image photo;

    public Department(String departName) {
        this.departName = new SimpleStringProperty(departName);
        //this.lastName = new SimpleStringProperty(lastName);
        //this.birthday = birthday;
        //photo = new Image("defaultImage.png");
    }
    
   /* public Person(String firstName, String lastName, LocalDate birthday, Image photo) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.photo = photo;
    }*/
    
  /*  public Image getImage()
    {
        return photo;
    }

    public void setImage(Image newPicture)
    {
        this.photo = newPicture;
    }*/
    
    public String getDepartName() {
        return departName.get();
    }

    public void setDepartName(String firstName) {
        this.departName = new SimpleStringProperty(firstName);
    }
/*
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    
    public int getAge()
    {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }*/
    
    public String toString()
    {
        return String.format("%s", departName);
    }   
}
