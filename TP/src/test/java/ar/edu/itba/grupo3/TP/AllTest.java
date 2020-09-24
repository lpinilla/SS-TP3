package ar.edu.itba.grupo3.TP;

import org.junit.Before;
import org.junit.Test;

public class AllTest {
    GenerateInput generateInput;
    private BrownianMotion brownianMotion;


    @Test
    public void randomInputTest() {

        for (int i = 0; i < 10; i++) {
            generateInput = new GenerateInput();
            generateInput.generateInputs(130, 6);
            System.out.println(i);
            brownianMotion = new BrownianMotion(i);
            brownianMotion.simulate();
        }

    }
}
