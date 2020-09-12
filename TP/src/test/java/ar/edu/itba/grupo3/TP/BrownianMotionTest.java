package ar.edu.itba.grupo3.TP;

import org.junit.Before;
import org.junit.Test;

public class BrownianMotionTest {

    private BrownianMotion brownianMotion;

    @Before
    public void setup(){
        brownianMotion = new BrownianMotion();
    }

    @Test
    public void brownianMotionTest(){
        brownianMotion.simulate();
    }
}
