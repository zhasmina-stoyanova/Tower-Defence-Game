package towerdefence;

public class Balista extends Tower {

    //the position of the tower
    private int position;
    //the cost of the tower
    public static int cost = 3;

    //constructor
    public Balista(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //overriden method from tower class
    //every second step it fires
    public boolean willFire(int timeStep) {
        boolean result = false;
        int remainder = timeStep % 2;
        if (timeStep % 2 == 0) {
            result = true;
        }
        return result;
    }

    //overriden method from tower class
    public int getDamage() {
        int damage = 3;
        return damage;
    }
}
