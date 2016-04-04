package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RetrievedFoodItems") //, namespace = "http://cse564.asu.edu/PoxAssignment")
@XmlType(propOrder = { "retrievedFoodItems" , "invalidFoodItems" })
public class RetrievedFoodItems {

    private List<FoodItem>  retrievedFoodItems;
    private InvalidFoodItem invalidFoodItems;

    public RetrievedFoodItems() {
        //this.retrievedFoodItems = new ArrayList<FoodItem>();
        //this.invalidFoodItems = new InvalidFoodItem();
    }

    public List<FoodItem> getRetrievedFoodItems() {
        return retrievedFoodItems;
    }

    @XmlElement(name = "FoodItem")
    public void setRetrievedFoodItems(List<FoodItem> retrievedFoodItems) {
        //this.retrievedFoodItems = retrievedFoodItems;
        this.retrievedFoodItems = new ArrayList<FoodItem>(retrievedFoodItems);
    }

    public InvalidFoodItem getInvalidFoodItems() {
        return invalidFoodItems;
    }

    @XmlElement(name="InvalidFoodItem", required = false)
    public void setInvalidFoodItems(InvalidFoodItem invalidFoodItems) {
        //this.invalidFoodItems = invalidFoodItems;
        this.invalidFoodItems = new InvalidFoodItem();       //-- COMMENTED THIS WHICH WAS UNCOMMNTED
        this.invalidFoodItems = invalidFoodItems;
    }
    
    
    
}
