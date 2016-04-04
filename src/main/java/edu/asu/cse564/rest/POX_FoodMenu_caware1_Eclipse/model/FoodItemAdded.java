package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "FoodItemAdded") // , namespace = "http://cse564.asu.edu/PoxAssignment")
public class FoodItemAdded {
    
    
    private  List<FoodItemId> addedFoodItems;

    public List<FoodItemId> getAddedFoodItems() {
        return addedFoodItems;
    }

    @XmlElement(name="FoodItemId")
    public void setAddedFoodItems(List<FoodItemId> addedFoodItems) {
        this.addedFoodItems = addedFoodItems;
    }

    public FoodItemAdded() {
        addedFoodItems = new ArrayList<FoodItemId>();
    }
}
