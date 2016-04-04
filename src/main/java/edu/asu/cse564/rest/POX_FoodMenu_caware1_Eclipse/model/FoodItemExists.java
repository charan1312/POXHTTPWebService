package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "FoodItemExists" ) //, namespace = "http://cse564.asu.edu/PoxAssignment")
//@XmlType(namespace = "http://cse564.asu.edu/PoxAssignment")
public class FoodItemExists {
    
    
    private  List<FoodItemId> existingFoodItems;


    public List<FoodItemId> getExistingFoodItems() {
        return existingFoodItems;
    }

    @XmlElement(name = "FoodItemId")
    public void setExistingFoodItems(List<FoodItemId> existingFoodItems) {
        this.existingFoodItems = existingFoodItems;
    }


    public FoodItemExists() {
        existingFoodItems = new ArrayList<FoodItemId>();
    }

}
