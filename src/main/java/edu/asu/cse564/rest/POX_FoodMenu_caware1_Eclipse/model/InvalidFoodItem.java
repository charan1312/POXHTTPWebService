package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InvalidFoodItem")
public class InvalidFoodItem {

    
    private List<FoodItemId>  invalidFoodItems;

    public List<FoodItemId> getInvalidFoodItems() {
        return invalidFoodItems;
    }

    @XmlElement(name="FoodItemId")
    public void setInvalidFoodItems(List<FoodItemId> invalidFoodItems) {
        this.invalidFoodItems = new ArrayList<FoodItemId>(invalidFoodItems);
    }

    public InvalidFoodItem() {}
}
