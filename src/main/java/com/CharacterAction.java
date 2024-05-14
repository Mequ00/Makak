package com;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.enemies.Enemy;
import com.enemies.fabrics.EnemyFabric;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Мария
 */
public class CharacterAction {

    private final int[] experience_for_next_level = {40, 90, 180, 260, 410, 1000};

    EnemyFabric fabric = new EnemyFabric();

    private final Enemy[] enemiesPool = {
            fabric.create(0, 0),
            fabric.create(1, 0),
            fabric.create(2, 0),
            fabric.create(3, 0),
            fabric.create(4, 0),
    };

    private Enemy enemy = null;

    private Queue<Integer> enemiesIndexes = new LinkedList<>();

    private int currentLocation;
    private int totalLocations;

    public Enemy[] getEnemies() {
        return this.enemiesPool;
    }

    public Enemy ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        Integer i = enemiesIndexes.poll();
        if (i == null) return null;
        ImageIcon icon1 = null;
        switch (i) {
            case 0:
                enemy = enemiesPool[0];
                icon1 = new javax.swing.ImageIcon(new ImageIcon("src/main/resources/assets/Baraka.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                label2.setText("Baraka (танк)");
                break;
            case 1:
                enemy = enemiesPool[1];
                icon1 = new javax.swing.ImageIcon(new ImageIcon("src/main/resources/assets/SubZero.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                label2.setText("Sub-Zero (маг)");
                break;
            case 2:
                enemy = enemiesPool[2];
                icon1 = new javax.swing.ImageIcon(new ImageIcon("src/main/resources/assets/LiuKang.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                label2.setText("Liu Kang (боец)");
                break;
            case 3:
                enemy = enemiesPool[3];
                icon1 = new javax.swing.ImageIcon(new ImageIcon("src/main/resources/assets/Sonya.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                label2.setText("Sonya Blade (солдат)");
                break;
            case 4:
                icon1 = new javax.swing.ImageIcon(new ImageIcon("src/main/resources/assets/ShaoKahn.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                label2.setText("Shao Kahn (босс)");
                enemy = enemiesPool[4];
                break;
        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemy.getDamage()));
        label3.setText(enemy.getHealth() + "/" + enemy.getMaxHealth());
        return enemy;
    }

    public void AddPoints(Player player, Character[] enemies, JDialog dialog) {
        switch (player.getLevel()) {
            case 0:
                player.addExperience(20);
                player.addPoints(25 + player.getHealth() / 4);
                break;
            case 1:
                player.addExperience(25);
                player.addPoints(30 + player.getHealth() / 4);
                break;
            case 2:
                player.addExperience(30);
                player.addPoints(35 + player.getHealth() / 4);
                break;
            case 3:
                player.addExperience(40);
                player.addPoints(45 + player.getHealth() / 4);
                break;
            case 4:
                player.addExperience(50);
                player.addPoints(55 + player.getHealth() / 4);
                break;
        }
        checkForLevelUp(player, enemies, dialog);
    }

    public void AddPointsBoss(Player player, Character[] enemies, JDialog dialog) {
        switch (player.getLevel()) {
            case 2:
                player.addExperience(30);
                player.addPoints(45 + player.getHealth() / 2);
                break;
            case 4:
                player.addExperience(50);
                player.addPoints(65 + player.getHealth() / 2);
                break;
        }
        checkForLevelUp(player, enemies, dialog);
    }

    private void checkForLevelUp(Player player, Character[] enemies, JDialog dialog) {
        for (int level = 5; level >= 0; level--) {
            if (player.getLevel() <= level && experience_for_next_level[level] <= player.getExperience()) {
                player.levelUp();
                if (level + 1 < experience_for_next_level.length)
                    player.setNextExperience(experience_for_next_level[level + 1]);
                NewHealthPlayer(player);
                for (int j = 0; j < 4; j++) {
                    NewHealthEnemy(enemies[j], player);
                }
                dialog.setVisible(true);
                return;
            }
        }
    }

    public void AddItems(int k1, int k2, int k3, Item[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    public void NewHealthPlayer(Player player) {
        int hp = 0;
        int damage = 0;
        switch (player.getLevel()) {
            case 1:
                hp = 25;
                damage = 3;
                break;
            case 2:
                hp = 30;
                damage = 3;
                break;
            case 3:
                hp = 30;
                damage = 4;
                break;
            case 4:
                hp = 40;
                damage = 6;
                break;
        }
        player.addMaxHealth(hp);
        player.addDamage(damage);
    }

    public void NewHealthEnemy(Character enemy, Player player) {
        int hp = 0;
        int damage = 0;
        switch (player.getLevel()) {
            case 1:
                hp = 32;
                damage = 25;
                break;
            case 2:
                hp = 30;
                damage = 20;
                break;
            case 3:
                hp = 23;
                damage = 24;
                break;
            case 4:
                hp = 25;
                damage = 26;
                break;
        }
        enemy.addMaxHealth(enemy.getMaxHealth() * hp / 100);
        enemy.addDamage(enemy.getDamage() * damage / 100);
        enemy.levelUp();
    }

    public void UseItem(Character player, Item[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "jRadioButton1":
                if (items[0].getCount() > 0) {
                    player.addHealth((int) (player.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton2":
                if (items[1].getCount() > 0) {
                    player.addHealth((int) (player.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton3":
                dialog.setVisible(true);
                dialog.setBounds(300, 200, 400, 300);
                break;
        }

        if (!dialog.isVisible()) {
            dialog1.dispose();
        }
    }

    public void generateEnemies(int level) {
        Random random = new Random();
        int count = random.nextInt(2) + level + 1;
        Queue<Integer> enemiesIds = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            int j = random.nextInt(4);
            enemiesIds.add(j);
        }
        enemiesIds.add(4);
        enemiesIndexes = enemiesIds;
    }

    public void createLocations(int totalLocations) {
        this.totalLocations = totalLocations;
        this.currentLocation = 1;
        this.generateEnemies(0);
    }

    public boolean isFinalLocation() {
        return currentLocation == totalLocations;
    }

    public void nextLocation() {
        currentLocation++;
    }
}
