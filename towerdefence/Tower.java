package towerdefence;

public abstract class Tower {

    public abstract int getDamage();

    public abstract int getPosition();

    public abstract boolean willFire(int timeStep);
}
