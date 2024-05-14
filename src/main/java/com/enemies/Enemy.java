package com.enemies;

import com.ActionType;
import com.Character;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class Enemy extends Character {

    private List<ActionsPattern> patterns;
    private Iterator<ActionType> actionsIterator;

    public ActionType nextAction() {
        if (actionsIterator == null || !actionsIterator.hasNext()) {
            actionsIterator = getRandomActionIterator();
        }
        return actionsIterator.next();
    }

    private void normalizeProbabilities() {
        double totalProbability = patterns.stream().mapToDouble(ActionsPattern::getProbability).sum();
        if (totalProbability != 1.0) {
            for (ActionsPattern pattern : patterns) {
                double normalizedProbability = pattern.getProbability() / totalProbability;
                pattern.setProbability(normalizedProbability);
            }
        }
    }

    private Iterator<ActionType> getRandomActionIterator() {
        normalizeProbabilities();
        double p = new Random().nextDouble();
        double cumulativeProbability = 0.0;
        for (ActionsPattern pattern : patterns) {
            cumulativeProbability += pattern.getProbability();
            if (p <= cumulativeProbability) {
                return pattern.createIterator();
            }
        }
        return patterns.get(patterns.size() - 1).createIterator();
    }

    public Enemy(int level, int health, int damage, ActionType currentAction) {
        super(level, health, damage, currentAction);
    }

    public void setPatterns(List<ActionsPattern> patterns) {
        this.patterns = patterns;
    }

    public List<ActionsPattern> getPatterns() {
        return patterns;
    }
}
