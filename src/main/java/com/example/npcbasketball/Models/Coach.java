package com.example.npcbasketball.Models;

import java.io.Serializable;

public class Coach extends BasketballPerson implements Serializable
{
    private String coach_pos; // Head, Asst, Shooting, Defensive
    private int years_of_exp;
    private double salary;

    // Constructor to initialize Coach object
    public Coach(String name, int age, String email, String coach_pos, int years_of_exp, double salary)
    {
        super(name, age, email);
        this.coach_pos = coach_pos;
        this.years_of_exp = years_of_exp;
        this.salary = salary;
    }

    // Getter and Setter methods for coach_pos
    public String getCoach_pos()
    {
        return coach_pos;
    }

    public void setCoach_pos(String coach_pos)
    {
        this.coach_pos = coach_pos;
    }

    // Getter and Setter methods for years_of_exp
    public int getYears_of_exp()
    {
        return years_of_exp;
    }

    public void setYears_of_exp(int years_of_exp)
    {
        this.years_of_exp = years_of_exp;
    }

    // Getter and Setter methods for salary
    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    // Override toString method to provide a formatted string representation of Coach
    @Override
    public String toString()
    {
        return getName() + ": " + getAge() + ": " + getEmail() + ": " + getCoach_pos() + ": " + getYears_of_exp() + ": " + getSalary();
    }
}
