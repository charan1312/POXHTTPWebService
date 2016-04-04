package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.service;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.FoodItem;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.FoodItemAdded;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.FoodItemData;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.FoodItemExists;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.FoodItemId;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.InvalidFoodItem;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.InvalidMessage;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.NewFoodItems;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.RetrievedFoodItems;
import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.model.SelectedFoodItems;

public class CopyOfFoodItemService {
    
    private static Map<Integer,FoodItem> foodItemsList = new HashMap<Integer,FoodItem>();

    private FoodItemAdded foodAdded = new FoodItemAdded();        //  ##### IS NEEDED TO WRITE THE ADDED ITEM TO MAIN FILE 
    private FoodItemExists foodExisting = new FoodItemExists();
    private InvalidMessage invalidMessage = new InvalidMessage();
    private RetrievedFoodItems foodRetrieved = new RetrievedFoodItems();
    
    //private static List<FoodItemId> listOfFoodAdded = new ArrayList<FoodItemId>();
    //private static List<FoodItemId> listOfFoodAlreadyPresent = new ArrayList<FoodItemId>();
    
    public CopyOfFoodItemService() {
        
        System.out.println("Called SERVICE AGAIN");
        File file = new File(this.getClass().getClassLoader().getResource("FoodItemData.xml").getFile());
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FoodItemData.class);
            //System.out.println(jaxbContext.toString());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
            FoodItemData foodItemData = (FoodItemData) jaxbUnmarshaller.unmarshal(file);
            
            //System.out.println(foodItemData.toString());
            
            for (FoodItem fi :  foodItemData.getFoodItemsList()) {
                foodItemsList.put(fi.getId(), fi);
                //System.out.println(fi);
            }
                
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public int prepareOutput(String input) {
        
        boolean foodAddedFlag = false, foodExistingFlag = false, newFoodItemsExceptionFlag = false ,selecteFoodItemExceptionFlag =  false;
        System.out.println("INPUT FROM REQ: "+ input);
        if(input.substring(1,13).compareTo("NewFoodItems") == 0) {
            System.out.println("Reading Food ITems to add");
            List<FoodItemId> listOfFoodAdded = new ArrayList<FoodItemId>();
            List<FoodItemId> listOfFoodAlreadyPresent = new ArrayList<FoodItemId>();
            
            try {
                JAXBContext jc = JAXBContext.newInstance(NewFoodItems.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
                
                NewFoodItems newFoodItems = (NewFoodItems) unmarshaller.unmarshal(new StringReader(input));
                
                for (FoodItem fi : newFoodItems.getNewFoodItems()) {
                    //getMaxidFoodItem();
                    String name = fi.getName();
                    String category = fi.getCategory();
                    if(!isFoodItemAlreadyPresent(name, category)) {
                        fi.setId(getMaxFoodItemId() + 1);
                        System.out.println(fi);
                        foodItemsList.put(fi.getId(), fi); 
                        System.out.println("ELements after:" + foodItemsList.size());
                        FoodItemId fid = new FoodItemId();
                        fid.setIndividualFoodId(fi.getId());
                        listOfFoodAdded.add(fid);
                        foodAddedFlag = true; 
                    } else {
                        FoodItemId fid = new FoodItemId();
                        fid.setIndividualFoodId(getFoodItemId(name,category));
                        listOfFoodAlreadyPresent.add(fid);
                        foodExistingFlag = true;
                    }
                }
                foodAdded.setAddedFoodItems(listOfFoodAdded);
                foodExisting.setExistingFoodItems(listOfFoodAlreadyPresent);
                //Write to file
            } catch (JAXBException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                newFoodItemsExceptionFlag = true;
            }
            
            if(foodAddedFlag == true && foodExistingFlag == true && newFoodItemsExceptionFlag == true){
                return 1;
            } else if(foodAddedFlag == true && foodExistingFlag == true && newFoodItemsExceptionFlag == false) {
                System.out.println("Returned 2");
                return 2;
            } else if (foodAddedFlag == true && foodExistingFlag == false && newFoodItemsExceptionFlag == false) {
                System.out.println("Returned 3");
                return 3;
            } else if (foodAddedFlag == false && foodExistingFlag == true && newFoodItemsExceptionFlag == false) {
                System.out.println("Returned 4");
                return 4;
            } else if(foodAddedFlag == false && foodExistingFlag == false && newFoodItemsExceptionFlag == true) {
                System.out.println("Returned 5");
                return 5;
            } else if (foodAddedFlag == true && foodExistingFlag == false && newFoodItemsExceptionFlag == true) {
                return 6;
            } else if(foodAddedFlag == false && foodExistingFlag == true && newFoodItemsExceptionFlag == true) {
                return 7;
            }
            
        } else if(input.substring(1,18).compareTo("SelectedFoodItems") == 0) {
            
            System.out.println("Reading Selected Food Items");
            List<FoodItem> listOfFoodItem = new ArrayList<FoodItem>();
            InvalidFoodItem invalidFoodItem = new InvalidFoodItem();
            List<FoodItemId> listOfInvalidFoodItem = new ArrayList<FoodItemId>();
            
            try {
                JAXBContext jc = JAXBContext.newInstance(SelectedFoodItems.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
                
                SelectedFoodItems selectedFoodItems = (SelectedFoodItems) unmarshaller.unmarshal(new StringReader(input));
                
                for (FoodItemId foodItemId : selectedFoodItems.getSelectedFoodItems()) {
                    //getMaxidFoodItem();
                    int fid = foodItemId.getIndividualFoodId();
                    System.out.println("Food ID: "+ fid);
                    if(isFoodItemWithIdPresent(fid)) {
                        System.out.println("Inside if :" + listOfFoodItem.size());
                        listOfFoodItem.add(getFoodItemWithID(fid));
                        //foodAddedFlag = true; 
                    } else {
                        System.out.println("Inside else:"+ listOfInvalidFoodItem.size());
                        listOfInvalidFoodItem.add(foodItemId);
                        //foodExistingFlag = true;
                    }
                }
                System.out.println("Outside food item size:"+ listOfFoodItem.size());
                System.out.println("Outside invalid size:"+ listOfInvalidFoodItem.size());
                if(listOfFoodItem.size() > 0)
                    foodRetrieved.setRetrievedFoodItems(listOfFoodItem);
                if(listOfInvalidFoodItem.size() > 0 ) {
                    invalidFoodItem.setInvalidFoodItems(listOfInvalidFoodItem);
                    foodRetrieved.setInvalidFoodItems(invalidFoodItem);
                }
                
                //Write to file
            } catch (JAXBException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                selecteFoodItemExceptionFlag = true;
            }
            
            if(selecteFoodItemExceptionFlag == true){
                System.out.println("Returned 10");
                return 10;
            } else if(selecteFoodItemExceptionFlag == false) {
                System.out.println("Returned 11");
                return 11;
            }
        }
        return 0;
    }

    
    private FoodItem getFoodItemWithID(int fid) {
        // TODO Auto-generated method stub
        List<FoodItem> allFood = new ArrayList<FoodItem>(foodItemsList.values());
        Iterator<FoodItem> it = allFood.iterator();
        while(it.hasNext()) {
            FoodItem fi = it.next();
            if( fi.getId() == fid ) 
                return fi;
        }
        return null;
    }

    private boolean isFoodItemWithIdPresent(int fid) {
        List<FoodItem> allFood = new ArrayList<FoodItem>(foodItemsList.values());
        Iterator<FoodItem> it = allFood.iterator();
        while(it.hasNext()) {
            FoodItem fi = it.next();
            if( fi.getId() == fid ) 
                return true;
        }
        return false;
    }

    public Response getResponse(int ch) {
        if(ch == 2 ) {
            return Response.status(200)
                           .entity(marshalAddedFoodToXML(foodAdded))
                           .entity(marshalFoodItemExistsToXML(foodExisting))
                           .build();
        } else if(ch == 3 ) {
            //marshalAddedFoodToXML(foodAdded);
            return Response.status(200).entity(marshalAddedFoodToXML(foodAdded)).build();
        } else if( ch==4 ) {
            return Response.status(200).entity(marshalFoodItemExistsToXML(foodExisting)).build();
        } else if( ch==5 || ch==10 ) {
            return Response.status(200).entity(marshalInvalidMessageToXML()).build();
        } else if( ch==11 ) {
            return Response.status(200).entity(marshalRetrievedMessageToXML(foodRetrieved)).build();
        }
        return null;
    }
    
    private Object marshalRetrievedMessageToXML(RetrievedFoodItems retrievedFoodItems) {
        try {
            Marshaller m = JAXBContext.newInstance(RetrievedFoodItems.class).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            final StringWriter w = new StringWriter();
            
            m.marshal(retrievedFoodItems, w);
            //foodRetrieved = null;                           //#####################################################################################//
            return w.toString();
            
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }

    private String marshalFoodItemExistsToXML(FoodItemExists foodItemExists) {
        try {
            Marshaller m = JAXBContext.newInstance(FoodItemExists.class).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            final StringWriter w = new StringWriter();
            
            m.marshal(foodItemExists, w);
            
            return w.toString();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }

    private String marshalInvalidMessageToXML() {
        try {
            Marshaller m = JAXBContext.newInstance(InvalidMessage.class).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            final StringWriter w = new StringWriter();
            
            m.marshal(invalidMessage, w);
            
            return w.toString();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }

    
    private String marshalAddedFoodToXML(FoodItemAdded foodAdded) {
        // TODO Auto-generated method stub
        try {
            Marshaller m = JAXBContext.newInstance(FoodItemAdded.class).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            final StringWriter w = new StringWriter();
            
            m.marshal(foodAdded, w);
            
            return w.toString();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }

    private int getFoodItemId(String name, String cate) {
        // TODO Auto-generated method stub
        List<FoodItem> allFood = new ArrayList<FoodItem>(foodItemsList.values());
        Iterator<FoodItem> it = allFood.iterator();
        while(it.hasNext()) {
            FoodItem fi = it.next();
            if( fi.getName().compareTo(name)==0 && fi.getCategory().compareTo(cate)==0 ) 
                return fi.getId();
        }
        return 0;
    }

    private boolean isFoodItemAlreadyPresent(String name, String cate) {
        // TODO Auto-generated method stub
        List<FoodItem> allFood = new ArrayList<FoodItem>(foodItemsList.values());
        Iterator<FoodItem> it = allFood.iterator();
        while(it.hasNext()) {
            FoodItem fi = it.next();
            if( fi.getName().compareTo(name)==0 && fi.getCategory().compareTo(cate)==0 ) 
                return true;
        }
        return false;
    }

    private int getMaxFoodItemId() {
        // TODO Auto-generated method stub
        return Collections.max(foodItemsList.keySet());
       
    } 

    public static void main(String[] args) {
        System.out.println("Main Method");
        //FoodItemService fs = new FoodItemService();
        System.out.println("After");
    }
}
