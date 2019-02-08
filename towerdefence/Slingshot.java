package towerdefence;

public class Slingshot extends Tower {

    //the position of the tower
    private int position;
    //the cost of the tower
    public static int cost = 1;

    public Slingshot(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean willFire(int timeStep) {
        boolean result = true;
        return result;
    }

    public int getDamage() {
        int damage = 1;
        return damage;
    }

}
