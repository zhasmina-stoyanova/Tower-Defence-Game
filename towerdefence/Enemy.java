package towerdefence;

public abstract class Enemy {

    public abstract int getHealth();

    public abstract int getPosition();

    public abstract void hit(Tower t);

    public abstract void advance();
}
