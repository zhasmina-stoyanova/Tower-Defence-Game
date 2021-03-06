package towerdefence;

public class Lizzard extends Enemy {

    private int position = 0;
    private int health = 2;
    private int counter = 1;

    //changing position in every 4 game steps with 1 step
    public void advance() {
        if (getCounter() % 4 == 0) {
            setPosition(getPosition() + 1);
        }
        setCounter(getCounter() + 1);
    }

    public void hit(Tower t) {
        int towerPosition = t.getPosition();
        int towerDamage = t.getDamage();
        boolean towerWillFire = t.willFire(Game.gameStep);
        if (getPosition() <= towerPosition && towerWillFire) {
            if (t instanceof Catapult) {
                setHealth(getHealth() - 5);
            } else if (t instanceof Slingshot) {
                setHealth(getHealth() - 1);
            } else if (t instanceof Balista) {
                setHealth(getHealth() - 3);
            } else if (t instanceof Trebuchet) {
                setHealth(getHealth() - 2);
            }
        }

        if (getHealth() < 0) {
            setHealth(0);
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
