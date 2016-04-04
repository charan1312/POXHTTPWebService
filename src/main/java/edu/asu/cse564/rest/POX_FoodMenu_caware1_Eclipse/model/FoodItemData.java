package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FoodItemData")
public class FoodItemData {
    
    private List<FoodItem> foodItemsList;
    
    //@XmlElement
    public List<FoodItem> getFoodItemsList() {
        return foodItemsList;
    }
    
    @XmlElement(name = "FoodItem")
    public void setFoodItemsList(List<FoodItem> foodItemsList) {
        this.foodItemsList = foodItemsList;
    }

    public FoodItemData() {
        foodItemsList = new ArrayList<FoodItem>();
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Elements: " + foodItemsList.size();
    }
}
