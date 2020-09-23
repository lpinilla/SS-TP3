package ar.edu.itba.grupo3.TP;

import org.junit.Before;
import org.junit.Test;

public class AllTest {
    GenerateInput generateInput;
    private BrownianMotion brownianMotion;


    @Test
    public void randomInputTest() {
        generateInput = new GenerateInput();

        generateInput.generateInputs(5, 6);
        for (int i = 0; i < 1; i++) {

            System.out.println(i);
            brownianMotion = new BrownianMotion(i);
            brownianMotion.simulate();
        }

    }
}
