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

    @Override
    public String toString() {
        return "Product with id" + identifier + " and name " + name + " and price " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        if (identifier != product.identifier) return false;
        if (Double.compare(product.price, price) != 0) return false;
        return name.equals(product.name);
    }

}