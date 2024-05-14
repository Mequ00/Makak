/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enemies;


import com.ActionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Мария
 */
public class SonyaBlade extends Enemy {

    public SonyaBlade(int level, int health, int damage, ActionType currentAction) {
        super(level, health, damage, currentAction);
        List<ActionsPattern> patterns = new ArrayList<>();
        patterns.add(new ActionsPattern(new ArrayList<>(Arrays.asList(ActionType.DEFENCE, ActionType.ATTACK)), 0.25));
        patterns.add(new ActionsPattern(new ArrayList<>(Arrays.asList(ActionType.ATTACK, ActionType.DEFENCE)), 0.25));
        patterns.add(new ActionsPattern(new ArrayList<>(Arrays.asList(ActionType.DEFENCE, ActionType.DEFENCE, ActionType.ATTACK)), 0.5));
        setPatterns(patterns);
    }

    @Override
    public String getName() {
        return "Sonya Blade";
    }
}
