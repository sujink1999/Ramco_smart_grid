package com.sujin.ramco;

public class FoodItem {

    String name;
    int image;
    int quantity, cost;

    public FoodItem(String name, int quantity, int cost, int image){
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public int getImage() {
        return image;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}