package towerdefence;

public class Catapult extends Tower {

    //the position of the tower
    private int position;
    //the cost of the tower
    public static int cost = 5;

    //constructor
    public Catapult(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //overriden method from tower class
    public boolean willFire(int timeStep) {
        boolean result = false;
        int remainder = timeStep % 3;
        if (timeStep % 3 == 0) {
            result = true;
        }
        return result;
    }

    //overriden method from tower class
    public int getDamage() {
        int damage = 5;
        return damage;
    }
}
