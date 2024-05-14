package com;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Мария
 */
public class Player extends Character {


    private int points;
    private int experience;
    private int nextExperience;


    public Player(int level, int health, int damage, ActionType actionType) {
        super(level, health, damage, actionType);
        this.points = 0;
        this.experience = 0;
        this.nextExperience = 40;
    }

    public int getPoints() {
        return this.points;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getNextExperience() {
        return this.nextExperience;
    }

    public void addPoints(int p) {
        this.points += p;
    }

    public void addExperience(int e) {
        this.experience += e;
    }

    public void setNextExperience(int e) {
        this.nextExperience = e;
    }

    @Override
    public String getName() {
        return "You";
    }
}
