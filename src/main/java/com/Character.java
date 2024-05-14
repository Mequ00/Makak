package com;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.enemies.Debuff;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Мария
 */
public class Character {

    private int level;
    private int health;
    private int maxHealth;
    private int damage;
    private ActionType currentAction;
    private final List<Debuff> debuffs = new ArrayList<>();

    public Character(int level, int health, int damage, ActionType currentAction) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.currentAction = currentAction;
        this.maxHealth = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void levelUp() {
        this.level++;
    }

    public void addHealth(int h) {
        this.health += h;
    }

    public void damage(int d) {
        Debuff vulnerability = findDebuff(Debuff.VULNERABILITY);
        if (vulnerability != null) {
            d = (int) (d * (1.0 + vulnerability.getValue()));
        }
        this.health = Math.max(this.health - d, 0);
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public void addDamage(int d) {
        this.damage += d;
    }

    public void setCurrentAction(ActionType a) {
        this.currentAction = a;
    }

    public void addMaxHealth(int h) {
        this.maxHealth += h;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        int damage = this.damage;
        Debuff weakness = findDebuff(Debuff.WEAKNESS);
        if (weakness != null) {
            damage = (int) (damage * (1.0 - weakness.getValue()));
        }
        return damage;
    }

    public ActionType getCurrentAction() {
        return this.currentAction;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public String getName() {
        return "";
    }

    private Debuff findDebuff(Debuff target) {
        for (Debuff debuff : debuffs) {
            if (debuff.getId() == target.getId()) {
                return debuff;
            }
        }
        return null;
    }

    public void addDebuff(Debuff newDebuff) {
        for (Debuff debuff : debuffs) {
            if (debuff.getId() == newDebuff.getId()) {
                if (debuff.getTurnsLeft() < newDebuff.getTurnsLeft()) {
                    debuff.setTurns(newDebuff.getTurnsLeft());
                }
                return;
            }
        }
        debuffs.add(newDebuff);
    }

    public void nextTurn() {
        debuffs.forEach(Debuff::nextTurn);
        debuffs.removeIf(debuff -> debuff.getTurnsLeft() <= 0);
    }

    public void cleanDebuffs() {
        debuffs.clear();
    }
}
