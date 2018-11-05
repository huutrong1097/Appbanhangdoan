package com.example.phant.appfood.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class Order {
    String idCustomer;
    String name;
    String address;
    String phone;
    String note;
    int totalMoney;
    int status;
    List<Food> foodList;
    String date;
    String key;



    public Order(String idCustomer, String name, String address, String phone, String note, int totalMoney, int status, List<Food> foodList, String date) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.totalMoney = totalMoney;
        this.status = status;
        this.foodList = foodList;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order() {
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("idCustomer",idCustomer);
        result.put("name",name);
        result.put("address",address);
        result.put("phone",phone);
        result.put("note",note);
        result.put("totalMoney",totalMoney);
        result.put("status",status);
        result.put("foodList",foodList);
        result.put("date",date);
        return result;
    }
}
