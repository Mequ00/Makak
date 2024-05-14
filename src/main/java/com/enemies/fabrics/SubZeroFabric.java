/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enemies.fabrics;


import com.ActionType;
import com.enemies.Enemy;
import com.enemies.SubZero;

/**
 *
 * @author Мария
 */
public class SubZeroFabric implements EnemyFabricInterface {

    @Override
    public Enemy create(int i) {
        Enemy enemy;
        enemy = new SubZero(1, 60, 16, ActionType.SKIP);
        return enemy;
    }
}
