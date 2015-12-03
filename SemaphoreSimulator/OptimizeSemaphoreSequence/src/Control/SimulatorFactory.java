/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Simulator;

/**
 *
 * @author Laura
 */
public class SimulatorFactory {

    private final Simulator sim;
    
    public SimulatorFactory() {
        this.sim = new Simulator();
    }
    
    public double simulate(double[][] matlabMatrix){
        return sim.simulate(parseMatLabMatrix(matlabMatrix));
    }

    private boolean[][] parseMatLabMatrix(double[][] d) {
         boolean[][] result = new boolean[d.length][d[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if( d[i][j]==1)
                    result[i][j] = true;
                else
                    result[i][j] = false;
            }
        }
        return result;
    }
    
    public double reset(){
        this.sim.reset();
        return 0;
    }
}
