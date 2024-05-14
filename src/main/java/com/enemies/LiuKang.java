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
public class LiuKang extends Enemy {

    public LiuKang(int level, int health, int damage, ActionType currentAction) {
        super(level, health, damage, currentAction);
        List<ActionsPattern> patterns = new ArrayList<>();
        patterns.add(new ActionsPattern(new ArrayList<>(Arrays.asList(ActionType.DEFENCE, ActionType.ATTACK)), 0.13));
        patterns.add(new ActionsPattern(new ArrayList<>(Arrays.asList(ActionType.ATTACK, ActionType.DEFENCE)), 0.12));
        patterns.add(new ActionsPattern(new ArrayList<>(Arrays.asList(ActionType.ATTACK, ActionType.DEFENCE)), 0.1));
        patterns.add(new ActionsPattern(new ArrayList<>(Arrays.asList(ActionType.ATTACK, ActionType.ATTACK, ActionType.ATTACK, ActionType.ATTACK)), 0.65));
        setPatterns(patterns);
    }

    @Override
    public String getName() {
        return "Liu Kang";
    }
}
