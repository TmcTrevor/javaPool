package fr._42.reflexions.classes;
import java.util.StringJoiner;

public class Car {
    private String brand;
    private String model;
    private Integer year;
    private Double price;

    public Car() {
        this.brand = "Default brand";
        this.model = "Default model";
        this.year = 2020;
        this.price = 10000.0;
    }

    public Car(String brand, String model, Integer year, Double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public void accelerate(int speed) {
        System.out.println("Accelerating to " + speed + " km/h");
    }

    public Double calculateDepreciation(Double rate) {
        return this.price * (1 - rate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + "'")
                .add("model='" + model + "'")
                .add("year=" + year)
                .add("price=" + price)
                .toString();
    }
}