package com.example.alamgir.digitaldyningsolution.model;

/**
 * Created by Alamgir on 4/4/2018.
 */

public class Cost {
    private int id;
    private double totalCost;
    private double meaRate;

    public Cost() {
    }

    public Cost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Cost(int id, double totalCost) {
        this.id = id;
        this.totalCost = totalCost;
    }

    public Cost(int id, double totalCost, double meaRate) {
        this.id=id;
        this.totalCost = totalCost;
        this.meaRate = meaRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getMeaRate() {
        return meaRate;
    }

    public void setMeaRate(double meaRate) {
        this.meaRate = meaRate;
    }
}
