package com.example.alamgir.digitaldyningsolution.model;

import java.io.Serializable;

/**
 * Created by Alamgir on 3/22/2018.
 */

public class Person implements Serializable {
    private int id;
    private String personName;
    private double persionMeal;
    private double personMoney;
    private double totalMoney;
    private double totalMeal;
    private double indivisualCost;
    private double mealRate;

    public Person() {
    }

    public Person(String personName, double persionMeal, double personMoney) {
        this.personName = personName;
        this.persionMeal = persionMeal;
        this.personMoney = personMoney;
    }

    public Person(int id, String personName, double persionMeal, double personMoney) {
        this.id = id;
        this.personName = personName;
        this.persionMeal = persionMeal;
        this.personMoney = personMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMealRate() {
        return mealRate;
    }

    public void setMealRate(double mealRate) {
        this.mealRate = mealRate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public double getPersionMeal() {
        return persionMeal;
    }

    public void setPersionMeal(double persionMeal) {
        this.persionMeal = persionMeal;
    }

    public double getPersonMoney() {
        return personMoney;
    }

    public void setPersonMoney(double personMoney) {
        this.personMoney = personMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getTotalMeal() {
        return totalMeal;
    }

    public void setTotalMeal(double totalMeal) {
        this.totalMeal = totalMeal;
    }

    public double getIndivisualCost() {
        return indivisualCost=mealRate*persionMeal;
    }

    public void setIndivisualCost(double indivisualCost) {
        this.indivisualCost = indivisualCost;
    }
}
