package com.example.demos.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class deals with the table of Order. It creates with Primary key
 * ID that will be the given order Id from the frontend.
 * This class has getters to be able to retrive the values and the method
 * {@link #addNewOrder(int, int, String)} alows the backend to add new values to 
 * the table using hibernate. 
 * @author Netanel Avraham Eklind
 * @version 0.0.1
 */

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private Integer credits;
    private String user;

    /**
     * Allows for a new order to be put forward.
     * @param id                Order id for the whole order
     * @param credits           The given credit to spend on the advertisment.
     * @param user              The user that ordered this advertisment.
     */
    public void addNewOrder(String id,int credits,String user){
        if(id ==null || credits < 0 || user == null) throw new IllegalArgumentException();
        this.id = id;
        this.credits = credits;
        this.user = user;
     }

     public String getID(){
         return id;
     }

     public Integer getCredits(){
         return credits;
     }

     public String getUser(){
         return user;
     }

      /**
      * @param credits the credits to set
      */
     public void setCredits(Integer credits) {
         this.credits = credits;
     }

     /**
      * @param id the id to set
      */
     public void setId(String id) {
         this.id = id;
     }

     /**
      * @param user the user to set
      */
     public void setUser(String user) {
         this.user = user;
     }
     
     @Override
     public String toString() {
         return "Order: [ OrderId: " + this.id + ", credits: " + this.credits + ", user: " + this.user + " ]";
     }

}