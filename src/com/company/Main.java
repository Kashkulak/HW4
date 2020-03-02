package com.company;


import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 30;
    public static String bossDeffenceType = "";

    public static int[] heroesHealth = {250, 250, 250, 350};
    public static int[] heroesDamage = {25, 25, 20, 10};
    public static String[] heroesAttackType = {"Physikal", "Magical", "Mental", "Medic"};

    public static void main(String[] args) {
        printStatistic();
        while (!isFinished()) {
            round();
        }
    }

    public static void round() {
        changeBossDefence();
        bossHit();
        healing();
        heroesHit();
        printStatistic();
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDeffenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes  won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                Random r = new Random();
                bossDamage = r.nextInt(110);
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length - 1; i++) {
            heroesHealth[i] = heroesHealth[i] - bossDamage;
            if (heroesHealth[i] > 0) {
                if (bossDeffenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(6) + 2;  //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            } else {
                heroesHealth[i] = 0;
            }
        }
    }


    public static void healing() {
        Random random = new Random();
        int r = random.nextInt(5) + 1;
        int y = random.nextInt(2);
        if (heroesHealth[y] > 0 && heroesHealth[y] <= 100 && heroesHealth[3] > 0) {
            heroesHealth[y] = heroesHealth[y] + heroesDamage[3] * r;
            System.out.println("Врач лечит " + heroesAttackType[y]);
        } else {
            System.out.println(heroesAttackType[y] + " уже сдох, не в состоянии лечить.");
        }
    }


    public static void printStatistic() {
        System.out.println("________________");
        System.out.println("Босс здоровье " + bossHealth);
        System.out.println("Воин здоровье " + heroesHealth[0]);
        System.out.println("Магия здоровье " + heroesHealth[1]);
        System.out.println("Кинетика здоровье " + heroesHealth[2]);
        System.out.println("Врач здоровье " + heroesHealth[3]);
        System.out.println("________________");
    }
}
