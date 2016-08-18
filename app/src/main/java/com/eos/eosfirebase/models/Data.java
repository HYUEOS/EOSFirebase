package com.eos.eosfirebase.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzoroak on 2016-08-17.
 */
public class Data {

    private String name;
    private String contact;

    public Data(){

    }

    public Data(String name, String contact){
        this.name = name;
        this.contact = contact;
    }

    public String getName(){
        return name;
    }

    public String getContact(){
        return contact;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    //Data to Map
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("contact", contact);

        return result;
    }


}
