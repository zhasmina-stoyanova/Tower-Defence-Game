package towerdefence;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Tower> towers = new ArrayList<Tower>();
    public static int gameStep = 0;
    public static int budget = 10;
    public static int corridorLength;
    private int numEnemiesKilled = 0;

    //constructor
    public Game(int corridorLength) {
        this.corridorLength = corridorLength;
    }

    //advance the game
    public void advance() {
        int enemyNumInList = 0;
        for (gameStep = 1;; gameStep++) {
            enemies.get(enemyNumInList).advance();
            //hit the enemy with all the towers if available
            for (int i = 0; i < towers.size(); i++) {
                enemies.get(enemyNumInList).hit(towers.get(i));
            }

            //if the health of the enemy is over
            if (enemies.get(enemyNumInList).getHealth() < 1) {
                printGameStepConfiguration(enemyNumInList);
                //add 10 coins with every killed enemy
                budget = budget + 10;
                enemyNumInList++;
                //checks if all enemies passed their turn
                if (enemyNumInList >= enemies.size()) {
                    System.out.println("\n\nCongratulations, you won the game in " + gameStep + " step(s) with " + budget + " coins left and " + getNumEnemiesKilled() + " killed enemies.");
                    return;
                } else {
                    configureTowers();
                    continue;
                }
            }
            //if the enemy gets to the end of the corridor the game ends and the enemy wins
            if (enemies.get(enemyNumInList).getPosition() > corridorLength) {
                printGameStepConfiguration(enemyNumInList);
                System.out.println("\n\nSorry, you lost the game in " + gameStep + " step(s) with " + budget + " coins left and " + getNumEnemiesKilled() + " killed enemies.");
                return;
            }
            printGameStepConfiguration(enemyNumInList);
        }
    }

    //printing the enemy and tower(s) configuration for each game step
    public void printGameStepConfiguration(int enemyNumInList) {
        String enemyType = "";
        if (enemies.get(enemyNumInList) instanceof Rat) {
            enemyType = "Rat";
        } else if (enemies.get(enemyNumInList) instanceof Dog) {
            enemyType = "Dog";
        } else if (enemies.get(enemyNumInList) instanceof Lizzard) {
            enemyType = "Lizzard";
        } else if (enemies.get(enemyNumInList) instanceof Elephant) {
            enemyType = "Elephant";
        }
        System.out.print("\n\n  @Game step: " + gameStep + "\n   Enemy: " + enemyType + "\n   health: " + enemies.get(enemyNumInList).getHealth() + "\n   position: " + enemies.get(enemyNumInList).getPosition());
        String towerType = "";
        for (int j = 0; j < towers.size(); j++) {
            if (towers.get(j) instanceof Catapult) {
                towerType = "Catapult";
            } else if (towers.get(j) instanceof Slingshot) {
                towerType = "Slingshot";
            } else if (towers.get(j) instanceof Trebuchet) {
                towerType = "Trebuchet";
            } else if (towers.get(j) instanceof Balista) {
                towerType = "Balista";
            }
            System.out.print("\n\n   Tower: " + towerType + "\n   damage: " + towers.get(j).getDamage() + "\n   position: " + towers.get(j).getPosition() + "\n   fire? " + (towers.get(j).willFire(gameStep) ? "YES" : "NO"));
        }

        if (enemies.get(enemyNumInList).getHealth() == 0) {
            setNumEnemiesKilled(getNumEnemiesKilled() + 1);
            System.out.print("\n\n   Enemy " + enemyType + " killed!");
        }
    }

    //choose a tower and position it
    public static void configureTowers() {
        System.out.println("\n\nChoose tower 1-4 for this amount or 0 to start the game:");
        System.out.println("1 - Slingshot 1 coin");
        System.out.println("2 - Trebuchet 2 coins");
        System.out.println("3 - Balista 3 coins");
        System.out.println("4 - Catapult 5 coins");
        Scanner input = new Scanner(System.in);
        int towerNumber = 0;
        int towerPosition = 0;
        int towerCost = 0;
        int newBudget = budget;
        configuration:
        do {
            //enter tower number between 1-4
            do {
                budget = newBudget;
                System.out.println("Current budget: " + budget + " coins");
                System.out.print("Choice: ");
                towerNumber = input.nextInt();

                if (towerNumber == 0) {
                    if (towers.isEmpty()) {
                        System.out.println("You must choose at least one tower!");
                        towerNumber = input.nextInt();
                    } else {
                        break configuration;
                    }
                }

                if (towerNumber > 4 || towerNumber < 1) {
                    System.out.println("The number must be between 1 and 4!");
                } else {
                    if (towerNumber == 1) {
                        towerCost = Slingshot.cost;
                    } else if (towerNumber == 2) {
                        towerCost = Trebuchet.cost;
                    } else if (towerNumber == 3) {
                        towerCost = Balista.cost;
                    } else if (towerNumber == 4) {
                        towerCost = Catapult.cost;
                    }

                    if (budget < towerCost) {
                        System.out.println("Your budget is not enough to buy this tower.!");
                    } else {
                        newBudget = budget - towerCost;
                    }
                }

            } while ((towerNumber > 4 || towerNumber < 1) || (budget < towerCost));

            System.out.print("Choose a position for the tower 1-" + corridorLength + ": ");
            do {
                System.out.print("Choice: ");
                towerPosition = input.nextInt();
                if (towerPosition > corridorLength || towerPosition < 1) {
                    System.out.println("The number must be between 1 and " + corridorLength + "!");
                }
            } while (towerPosition > corridorLength || towerPosition < 1);

            Tower tower = null;
            if (towerNumber == 1) {
                tower = new Slingshot(towerPosition);
            } else if (towerNumber == 2) {
                tower = new Trebuchet(towerPosition);
            } else if (towerNumber == 3) {
                tower = new Balista(towerPosition);
            } else if (towerNumber == 4) {
                tower = new Catapult(towerPosition);
            }

            towers.add(tower);
        } while (true);
    }

    //starting the game
    public static void main(String[] args) {
        System.out.print("\n*****WELCOME TO THE TOWER DEFENCE GAME*****");
        //parameter input
        String arg = args[0];
        int argValue = Integer.parseInt(arg);
        Game game = new Game(argValue);
        configureTowers();
        //add 4 diffrent types of enemies that come one after another - from the fastest to the slowest
        Enemy rat = new Rat();
        enemies.add(rat);
        Enemy dog = new Dog();
        enemies.add(dog);
        Enemy elephant = new Elephant();
        enemies.add(elephant);
        Enemy lizzard = new Lizzard();
        enemies.add(lizzard);

        game.advance();
    }

    public int getNumEnemiesKilled() {
        return numEnemiesKilled;
    }

    public void setNumEnemiesKilled(int numEnemiesKilled) {
        this.numEnemiesKilled = numEnemiesKilled;
    }
}
