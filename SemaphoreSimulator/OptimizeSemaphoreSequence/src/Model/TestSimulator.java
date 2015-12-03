package Model;

/**
 *
 * @author lala
 */
public class TestSimulator {

   

    public TestSimulator() {}

    public void test() {

       //una carretera horizontal
        horizontalTest();
       //una carretera vertical

    }

    private void horizontalTest() {
        Road h = new Road(RoadSense.Horizontal);
        
        
        for (int i = 0; i < 15; i++) {
            h.addCar();
            h.sem1NextStatus(true);
            h.sem2NextStatus(true);
            h.determineNextStatusAndUpdate();
            System.out.println(h.toString());
        }
    }
    
}
