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
    private int[][] mask1, mask2, mask3;
    private final int populationSize;
    private final Simulator sim;
    private final double selection_p, mutation_p;

    public double[] getX() {
        return maxFitness;
    }

    public double[] getY() {
        return meanFitness;
    }

    public GeneticAlgorithmForSemaphoreOptimization(int populationSize,double selection_p, double mutation_p) {
        this.populationSize = populationSize;
        this.selection_p = selection_p;
        this.mutation_p = mutation_p;
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
        mask1 = generateRandomMatrixInModule3();
        mask2 = generateInverseOfMask(mask1);
        mask3 = generateInverseOfMask(mask2);
    }

    public void compute() {
        maxFitness = new double[populationSize];
        meanFitness = new double[populationSize];
        for (int i = 0; i < populationSize; i++) {
            this.fitness = computeFitness();
            saveData(i);
            this.population = probabilistic_tourneau_selection();
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

    private int[][] generateRandomMatrixInModule3() {
        int[][] randomMatrix = new int[12][4];
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                randomMatrix[i][j] = rand.nextInt(3);
            }
        }
        return randomMatrix;
    }

    private int[][] generateInverseOfMask(int[][] mask) {
        int[][] inverse = new int[12][4];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                inverse[i][j] = (mask[i][j]+ 1)% 3;
            }
        }
        return inverse;
    }

    private ArrayList<boolean[][]> probabilistic_tourneau_selection() {
        ArrayList<boolean[][]> result = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());
        
        int indexA, indexB,min;
        double fitnessA, fitnessB, r;
        
        for (int i = 0; i < fitness.length; i++) {
        
            indexA = rand.nextInt(fitness.length);
            indexB = rand.nextInt(fitness.length);
            if(indexA == indexB){
                indexB = (indexA + 1)% fitness.length;
            }
            
           r = rand.nextDouble();
           fitnessA = fitness[indexA];
           fitnessB = fitness[indexB];
           
           if((fitnessA < fitnessB && r < selection_p) || (fitnessA > fitnessB && r >= selection_p)){
               result.add(population.get(indexA));
           }
           else 
               result.add(population.get(indexB));
        }
        
        return result;
    }
}
