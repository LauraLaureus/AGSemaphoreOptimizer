package Control;

import Model.Simulator;
import Model.TestSimulator;
import View.simulatorView;
//import View.TestFrame;

/**
 *
 * @author Laura
 */
public class SemaphoreSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*TestSimulator test = new TestSimulator();
        test.test();*/
        new simulatorView().setVisible(true);
    }

    public static double maine(double[][] d) {
        
        boolean[][] result = new boolean[d.length][d[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if( d[i][j]==1)
                    result[i][j] = true;
                else
                    result[i][j] = false;
            }
        }
        
        
        return new Simulator().simulate(result) ;
    }

}
