/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enemies.fabrics;


import com.ActionType;
import com.enemies.Enemy;
import com.enemies.SonyaBlade;

/**
 *
 * @author Мария
 */
public class SonyaBladeFabric implements EnemyFabricInterface {

    @Override
    public Enemy create(int i) {
        Enemy enemy;
        enemy = new SonyaBlade(1, 80, 16, ActionType.SKIP);
        return enemy;
    }

}
