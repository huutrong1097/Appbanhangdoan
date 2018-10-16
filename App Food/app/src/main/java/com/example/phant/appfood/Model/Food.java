package com.example.phant.appfood.Model;

public class Food {
    String idFood;
    String name;
    String unitPrice;
    String linkImage;

    public Food() {
    }

    public Food(String idFood, String name, String unitPrice, String linkImage) {
        this.idFood = idFood;
        this.name = name;
        this.unitPrice = unitPrice;
        this.linkImage = linkImage;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
