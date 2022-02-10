/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        this.x = 0;
        this.y = 0;
        for (int i=0; i<m; i++) {
            randomMove();
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
         return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }


    public static void main(String[] args) {
        int[] arr = {4,8,10,15,20,25,30,35,40,45,50,60,70,80,90,100,200,300,400,500,750,1000}; // number of steps taken by drunkard each time
        for (int m : arr) {
            int n = 30;
            String avgMeanDistances = "";
            for (int i=0; i<15; i++) { //calculate and concatenate all 15 mean values into avgMeanDistances string
                if (i==0) {
                    avgMeanDistances = avgMeanDistances + " " + randomWalkMulti(m, n);
                } else {
                    avgMeanDistances = avgMeanDistances + ", " + randomWalkMulti(m, n);
                }
            }
            System.out.println(m + " steps over " + n + " experiments for 15 times: " + avgMeanDistances);
        }
    }
    
 
    


}