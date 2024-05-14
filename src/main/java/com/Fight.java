package com;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//ADD IMAGE!!!

import com.enemies.Debuff;
import com.enemies.Enemy;
import com.enemies.ShaoKahn;
import com.gui.ChangeTexts;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Мария
 */
public class Fight {

    ChangeTexts change = new ChangeTexts();
    int round = 1;
    boolean stun = false;

    public void Move(Character p1, Character p2, JLabel l, JLabel l2) {
        if (stun) p1.setCurrentAction(ActionType.SKIP);

        switch (p1.getCurrentAction()) {
            case SKIP:
                l.setText(p1.getName() + " was stunned");
                stun = false;
                switch (p2.getCurrentAction()) {
                    case ATTACK:
                        p1.damage(p2.getDamage());
                        l2.setText(p2.getName() + " attacked");
                        break;
                    case DEFENCE:
                        l2.setText(p2.getName() + " didn't attacked");
                        break;
                    case WEAKEN:
                        if (Math.random() > 0.75) {
                            l2.setText(p2.getName() + " didn't attacked");
                            break;
                        }
                        Debuff d1 = Debuff.WEAKNESS;
                        Debuff d2 = Debuff.VULNERABILITY;
                        d1.setTurns(p2.getLevel());
                        d2.setTurns(p2.getLevel());
                        p1.addDebuff(d1);
                        p1.addDebuff(d2);
                        l2.setText(p2.getName() + " weakened " + p1.getName());
                        break;
                    case HEAL:
                        p2.addHealth((int) ((p2.getMaxHealth() - p2.getHealth()) * 0.5));
                        l2.setText(p2.getName() + " healed");
                        break;
                }
                break;
            case ATTACK:
                switch (p2.getCurrentAction()) {
                    case DEFENCE:
                        if (p1 instanceof ShaoKahn & Math.random() < 0.15) {
                            p2.damage((int) (p1.getDamage() * 0.5));
                            l2.setText("Your block is broken");

                        } else {
                            p1.damage((int) (p2.getDamage() * 0.5));
                            l2.setText(p2.getName() + " counterattacked");
                        }
                        break;
                    case ATTACK:
                        p2.damage(p1.getDamage());
                        l2.setText(p1.getName() + " attacked");
                        break;
                    case WEAKEN:
                        p2.damage((int) (1.15 * p1.getDamage()));
                        l2.setText(p2.getName() + " critically damaged");
                        break;
                    case HEAL:
                        p2.damage(2 * p1.getDamage());
                        l2.setText(p2.getName() + " critically damaged");
                        break;
                }
                break;
            case DEFENCE:
                switch (p2.getCurrentAction()) {
                    case ATTACK:
                        l2.setText(p1.getName() + " didn't attacked");
                        break;
                    case DEFENCE:
                        if (Math.random() <= 0.5) {
                            stun = true;
                        }
                        l2.setText("Both defended themselves");
                        break;
                    case WEAKEN:
                        if (Math.random() > 0.75) {
                            l2.setText(p2.getName() + " didn't attacked");
                            break;
                        }
                        Debuff d1 = Debuff.WEAKNESS;
                        Debuff d2 = Debuff.VULNERABILITY;
                        d1.setTurns(p2.getLevel());
                        d2.setTurns(p2.getLevel());
                        p1.addDebuff(d1);
                        p1.addDebuff(d2);
                        l2.setText(p2.getName() + " weakened " + p1.getName());
                        break;
                    case HEAL:
                        p2.addHealth((int) ((p2.getMaxHealth() - p2.getHealth()) * 0.5));
                        l2.setText(p2.getName() + " healed");
                        break;
                }
                break;
            case WEAKEN:
                switch (p2.getCurrentAction()) {
                    case DEFENCE:
                    case WEAKEN:
                    case HEAL:
                        if (Math.random() > 0.75) {
                            l2.setText(p2.getName() + " didn't attacked");
                            break;
                        }
                        Debuff d1 = Debuff.WEAKNESS;
                        Debuff d2 = Debuff.VULNERABILITY;
                        d1.setTurns(p1.getLevel());
                        d2.setTurns(p1.getLevel());
                        p2.addDebuff(d1);
                        p2.addDebuff(d2);
                        l2.setText(p1.getName() + " weakened " + p2.getName());
                        break;
                    case ATTACK:
                        p1.damage((int) (1.15 * p2.getDamage()));
                        l2.setText(p1.getName() + " critically damaged");
                        break;
                }
                if (p2.getCurrentAction() == ActionType.HEAL) {
                    p2.addHealth((int) ((p2.getMaxHealth() - p2.getHealth()) * 0.5));
                }
                break;
            case HEAL:
                switch (p2.getCurrentAction()) {
                    case DEFENCE:
                    case WEAKEN:
                        p1.addHealth((int) ((p1.getMaxHealth() - p1.getHealth()) * 0.5));
                        l2.setText(p1.getName() + " healed");
                        break;
                    case ATTACK:
                        p1.damage(2 * p2.getDamage());
                        l2.setText(p1.getName() + " critically damaged");
                        break;
                }
                if (p2.getCurrentAction() == ActionType.WEAKEN) {
                    if (Math.random() > 0.75) {
                        break;
                    }
                    Debuff d1 = Debuff.WEAKNESS;
                    Debuff d2 = Debuff.VULNERABILITY;
                    d1.setTurns(p2.getLevel());
                    d2.setTurns(p2.getLevel());
                    p1.addDebuff(d1);
                    p1.addDebuff(d2);
                    break;
                }
                break;
        }
    }

    public void Hit(Character player, Enemy enemy, ActionType a, JLabel label,
                    JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
                    JProgressBar pr1, JProgressBar pr2, JDialog dialog1,
                    JDialog dialog2, JFrame frame, ArrayList<Result> results,
                    JLabel label4, JLabel label5, JLabel label6, JLabel label7,
                    JLabel label8, Item[] items, JRadioButton rb, JDialog levelUpDialog) {
        label7.setText("");
        player.setCurrentAction(a);

        enemy.setCurrentAction(enemy.nextAction());
        if (round % 2 == 1) {
            Move(player, enemy, label7, label8);
        } else {
            Move(enemy, player, label7, label8);
        }
        round++;
        player.nextTurn();
        enemy.nextTurn();
        change.RoundTexts(player, enemy, label, label2, round, label6);
        HP(player, pr1);
        HP(enemy, pr2);
        if (player.getHealth() <= 0 & items[2].getCount() > 0) {
            player.setHealth((int) (player.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            HP(player, pr1);
            label2.setText(player.getHealth() + "/" + player.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            label7.setText("Вы воскресли");
        }
        if (player.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (action.isFinalLocation() && enemy instanceof ShaoKahn) {
                EndFinalRound(((Player) player), action, results, dialog1, dialog2,
                        frame, label4, label5, levelUpDialog);
            } else {
                EndRound(player, enemy, dialog, label3, action, items, levelUpDialog);
            }
        }
    }

    public void EndRound(Character player, Character enemy, JDialog dialog, JLabel label,
                         CharacterAction action, Item[] items, JDialog levelUpDialog) {

        dialog.setVisible(true);
        dialog.setBounds(300, 150, 700, 600);
        if (player.getHealth() > 0) {
            label.setText("You win");

            if (enemy instanceof ShaoKahn) {
                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(((Player) player), action.getEnemies(), levelUpDialog);
                action.nextLocation();
                action.generateEnemies(player.getLevel());
            } else {
                action.AddItems(25, 15, 5, items);
                action.AddPoints(((Player) player), action.getEnemies(), levelUpDialog);
            }
        } else {
            label.setText(enemy.getName() + " win");
        }
        player.cleanDebuffs();
        enemy.cleanDebuffs();
        round = 1;
    }

    public void EndFinalRound(Player player, CharacterAction action,
                              ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
                              JLabel label1, JLabel label2, JDialog levelUpDialog) {
        String text = "Победа не на вашей стороне";
        if (player.getHealth() > 0) {
            action.AddPoints(player, action.getEnemies(), levelUpDialog);
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (Result result : results) {
                if (player.getPoints() < result.getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    public Enemy NewRound(Player player, JLabel label, JProgressBar pr1,
                          JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {
        Enemy enemy1 = action.ChooseEnemy(label, label2, text, label3);
        pr1.setMaximum(player.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        player.setHealth(player.getMaxHealth());
        enemy1.setHealth(enemy1.getMaxHealth());
        HP(player, pr1);
        HP(enemy1, pr2);
        return enemy1;
    }

    public void HP(Character enemy, JProgressBar progress) {
        progress.setValue(Math.max(enemy.getHealth(), 0));
    }
}
