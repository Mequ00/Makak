/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enemies.fabrics;


import com.ActionType;
import com.enemies.Enemy;
import com.enemies.ShaoKahn;

/**
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface {

    @Override
    public Enemy create(int i) {
        Enemy enemy;
        if (i == 0) {
            enemy = new ShaoKahn(3, 100, 30, ActionType.SKIP);
        } else {
            enemy = new ShaoKahn(5, 145, 44, ActionType.SKIP);
        }
        return enemy;
    }
}
