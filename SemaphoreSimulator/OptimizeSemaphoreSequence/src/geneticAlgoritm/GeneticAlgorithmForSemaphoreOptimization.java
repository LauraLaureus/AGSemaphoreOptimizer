/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticAlgoritm;

import Model.Simulator;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Laura
 */
public class GeneticAlgorithmForSemaphoreOptimization {

    private ArrayList<boolean[][]> population;
    private double[] fitness;
    private double[] maxFitness, meanFitness;
    private boolean[][] mask1, mask2, mask3;
    private final int populationSize;
    private final Simulator sim;

    public double[] getX() {
        return maxFitness;
    }

    public double[] getY() {
        return meanFitness;
    }

    public GeneticAlgorithmForSemaphoreOptimization(int populationSize) {
        this.populationSize = populationSize;
        population = new ArrayList<>();
        this.sim = new Simulator();
        generateInitialPopulation();
        generateMaskForThreeParentCrossover();
    }

    private void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            population.add(generateRandomBidimensionalMatrix());
        }
    }

    private boolean[][] generateRandomBidimensionalMatrix() {
        boolean[][] randomMatrix = new boolean[12][4];
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                randomMatrix[i][j] = rand.nextBoolean();
            }
        }
        return randomMatrix;
    }

    private void generateMaskForThreeParentCrossover() {
        mask1 = generateRandomBidimensionalMatrix();
        mask2 = generateRandomBidimensionalMatrix();
        mask3 = generateRandomBidimensionalMatrix();
    }

    public void compute() {
        maxFitness = new double[populationSize];
        meanFitness = new double[populationSize];
        for (int i = 0; i < populationSize; i++) {
            this.fitness = computeFitness();
            saveData(i);
            //population = probabilistic_tourneau_selection();
            //population = three_parent_crossover();
        }
    }

    private double[] computeFitness() {
        double[] result = new double[population.size()];
        for (int i = 0; i < population.size(); i++) {
            result[i] = sim.simulate(population.get(i));
        }
        return result;
    }

    private void saveData(int i) {
        maxFitness[i] = calculateMaxFitness();
        meanFitness[i] = calculateMeanFitness();
    }

    private double calculateMaxFitness() {
        double max = this.fitness[0];

        for (int counter = 1; counter < fitness.length; counter++) {
            if (fitness[counter] > max) {
                max = fitness[counter];
            }
        }
        return max;
    }

    private double calculateMeanFitness() {
        double sum = 0;
        for (int i = 0; i < fitness.length; i++) {
            sum += fitness[i];
        }
        return sum / fitness.length;
    }
}
