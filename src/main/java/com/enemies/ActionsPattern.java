package com.enemies;

import com.ActionType;

import java.util.Iterator;
import java.util.List;

public class ActionsPattern {
    private List<ActionType> actions;
    private double probability;

    public ActionsPattern(List<ActionType> actions, double probability) {
        this.actions = actions;
        this.probability = probability;
    }

    public List<ActionType> getActions() {
        return actions;
    }

    public void setActions(List<ActionType> actions) {
        this.actions = actions;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Iterator<ActionType> createIterator() {
        return actions.iterator();
    }
}
