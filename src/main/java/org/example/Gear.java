package org.example;

public enum Gear {

    ONE(1, 0, 1),
    TWO(2, 1, 1),
    THREE(3, 2, 1),
    FOUR(4, 3, 1),
    FIVE(5, 4, 1),
    SIX(6, 5, 1),
    SEVEN(7, 6, 1),
    EIGHT(8, 7, 1);

    private int gearLevel;
    private int gap;
    private double rev;

    Gear(int gearLevel, int gap, double rev) {
        this.gearLevel = gearLevel;
        this.gap = gap;
        this.rev = rev;
    }

    public Gear up() {
        return get(1);
    }

    public Gear down() {
        return get(-1);
    }

    private Gear get(int d) {
        int nextGear = this.gearLevel + d;
        for (Gear g: Gear.values()){
            if (g.gearLevel == nextGear)
                return g;
        }
        return null;
    }

    public int getGearLevel() {
        return gearLevel;
    }

    public double getRev() {
        return rev;
    }

    public int getGap() {
        return gap;
    }
}
