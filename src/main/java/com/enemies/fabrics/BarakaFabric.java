/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enemies.fabrics;


import com.ActionType;
import com.enemies.Baraka;
import com.enemies.Enemy;

/**
 * @author Мария
 */
public class BarakaFabric implements EnemyFabricInterface {

    @Override
    public Enemy create(int i) {
        Enemy enemy;
        enemy = new Baraka(1, 100, 12, ActionType.SKIP);
        return enemy;
    }
}
