package com.example.phant.appfood.Model;

import java.util.List;

public class Order {
    String idCustomer;
    String name;
    String address;
    String phone;
    String note;
    String totalMoney;
    int status;
    List<Food> foodList;

    public Order(String idCustomer, String name, String address, String phone, String note, String totalMoney, int status, List<Food> foodList) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.totalMoney = totalMoney;
        this.status = status;
        this.foodList = foodList;
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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
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
}
