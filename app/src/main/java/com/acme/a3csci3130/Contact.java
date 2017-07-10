package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public  String email;

    public String BusinessNumber;
    public String PrimaryBusiness;
    public String Address;
    public String Province;

    /**
     * new elements are added to the contact
     * address
     * province
     * business number
     * primary business
     */
    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid,String name,String email,String BusinessNumber,String PrimaryBusiness,String Address,String Province){
        this.uid = uid;
        this.name = name;
        this.email = email;

        this.Address = Address;
        this.Province = Province;
        this.BusinessNumber = BusinessNumber;
        this.PrimaryBusiness = PrimaryBusiness;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("email", email);

        result.put("business number", BusinessNumber);
        result.put("primary business", PrimaryBusiness);
        result.put("address", Address);
        result.put("province", Province);

        return result;
    }
}
