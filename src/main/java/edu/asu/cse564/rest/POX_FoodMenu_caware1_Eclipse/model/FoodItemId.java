package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "FoodItemId")
public class FoodItemId {
    
    
    private int individualFoodId;

    public int getIndividualFoodId() {
        return individualFoodId;
    }

    @XmlValue
    public void setIndividualFoodId(int individualFoodId) {
        this.individualFoodId = individualFoodId;
    }
    
    
}
