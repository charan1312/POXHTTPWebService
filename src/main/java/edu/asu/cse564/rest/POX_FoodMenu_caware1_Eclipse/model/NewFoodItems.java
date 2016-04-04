package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewFoodItems", namespace = "http://cse564.asu.edu/PoxAssignment")
//@XmlType(namespace = "http://cse564.asu.edu/PoxAssignment")
public class NewFoodItems {

    private List<FoodItem> newFoodItems;

    public List<FoodItem> getNewFoodItems() {
        return newFoodItems;
    }
    
    @XmlElement(name = "FoodItem")
    public void setNewFoodItems(List<FoodItem> newfoodItems) {
       this.newFoodItems = newfoodItems;
    }

    public NewFoodItems() {
        newFoodItems = new ArrayList<FoodItem>();
    }
}
