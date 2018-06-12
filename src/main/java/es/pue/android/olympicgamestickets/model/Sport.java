package es.pue.android.olympicgamestickets.model;

import java.io.Serializable;

public class Sport implements Serializable{
    private int id;
    private String name;
    private double priceAdult;
    private double priceChild;

    public Sport(int id, String name, double priceAdult, double priceChild) {
        this.id = id;
        this.name = name;
        this.priceAdult = priceAdult;
        this.priceChild = priceChild;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPriceAdult() {
        return priceAdult;
    }

    public double getPriceChild() {
        return priceChild;
    }
}
