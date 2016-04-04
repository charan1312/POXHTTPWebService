package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SelectedFoodItems", namespace = "http://cse564.asu.edu/PoxAssignment")
//@XmlType(namespace = "http://cse564.asu.edu/PoxAssignment")
public class SelectedFoodItems {

    
    private List<FoodItemId> selectedFoodItems;
    
    public SelectedFoodItems() {
        selectedFoodItems = new ArrayList<FoodItemId>();
    }

    public List<FoodItemId> getSelectedFoodItems() {
        return selectedFoodItems;
    }
    
    @XmlElement(name = "FoodItemId")
    public void setSelectedFoodItems(List<FoodItemId> selectedFoodItems) {
        this.selectedFoodItems = selectedFoodItems;
    }
}
