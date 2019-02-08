package towerdefence;

public class Trebuchet extends Tower {

    //the position of the tower
    private int position;
    //the cost of the tower
    public static int cost = 2;

    //constructor
    public Trebuchet(int position) {
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
        int remainder = timeStep % 5;
        if (timeStep % 5 == 0) {
            result = true;
        }
        return result;
    }

    //overriden method from tower class
    public int getDamage() {
        int damage = 2;
        return damage;
    }
}
