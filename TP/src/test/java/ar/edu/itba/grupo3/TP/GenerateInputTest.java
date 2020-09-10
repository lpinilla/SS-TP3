package ar.edu.itba.grupo3.TP;

import org.junit.Before;
import org.junit.Test;

public class GenerateInputTest {

    GenerateInput generateInput;

    @Before
    public void setup(){
        generateInput = new GenerateInput();
    }

    @Test
    public void randomInputTest(){
        generateInput.generateInputs(130, 6);
    }
}
