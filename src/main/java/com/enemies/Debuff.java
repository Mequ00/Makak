package com.enemies;

public enum Debuff {
    WEAKNESS(0, "Weakness", 0.5),
    VULNERABILITY(1, "Vulnerability", 0.25);

    private final int id;
    private final String name;
    private final double value;
    private double turnsLeft = 0;

    Debuff(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void setTurns(double maxTurns) {
        this.turnsLeft = maxTurns;
    }

    public double getTurnsLeft() {
        return turnsLeft;
    }

    public void nextTurn() {
        turnsLeft--;
    }
}
