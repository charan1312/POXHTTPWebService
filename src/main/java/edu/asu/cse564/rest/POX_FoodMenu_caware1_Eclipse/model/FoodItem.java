package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "FoodItem")
@XmlType(propOrder = { "id", "name", "description", "category", "price"})
public class FoodItem {

    private String country;
    private int id;
    private String name;
    private String description;
    private String category;
    private double price;
    
    public FoodItem() {
        
    }

    public FoodItem(String country, int id, String name, String description,
                    String category, double price) {
        this.country = country;
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    @XmlAttribute
    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }
    
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    @XmlElement
    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    @XmlElement()
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "FoodItem: {" + 
                "Id:" + this.getId() + 
                ",Name:" + this.getName() +
                ",Description:" + this.getDescription() +
                ",Category:" + this.getCategory() +
                ",Price:" + this.getPrice() +
                "}";
    }
    
}
