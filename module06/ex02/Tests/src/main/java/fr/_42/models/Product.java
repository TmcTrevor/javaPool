package fr._42.models;

public class Product {

    private int identifier;
    private String name;
    private double price;

    public Product(int identifier, String name, double price) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
    }
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public int getIdentifier() {
        return identifier;
    }
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


}