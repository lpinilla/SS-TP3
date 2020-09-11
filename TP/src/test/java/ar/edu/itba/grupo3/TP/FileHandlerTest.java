package ar.edu.itba.grupo3.TP;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileHandlerTest {

    FileHandler fileHandler;

    @Before
    public void setup(){
        this.fileHandler = new FileHandler();
    }

    @Test
    public void loadStaticTest(){
        Particle expP1 = new Particle(0.7, 2.0, 0);
        Particle expP2 = new Particle(0.2, 0.9, 1);
        SimInfo ret = fileHandler.loadStaticFile("resources/RandomStaticInput.txt");
        Assert.assertNotNull(ret);
        Assert.assertEquals(6, ret.getL());
        Assert.assertEquals(130, ret.getN());
        Assert.assertEquals(expP1.getRadius(), ret.getAllParticles().get(0).getRadius());
        Assert.assertEquals(expP1.getMass(), ret.getAllParticles().get(0).getMass());
        Assert.assertEquals(expP2.getRadius(), ret.getAllParticles().get(1).getRadius());
        Assert.assertEquals(expP2.getMass(), ret.getAllParticles().get(1).getMass());
    }
}
