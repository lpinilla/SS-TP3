package ar.edu.itba.grupo3.TP;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertEquals(130, ret.getAllParticles().size());
        Assert.assertEquals(expP1.getRadius(), ret.getAllParticles().get(0).getRadius());
        Assert.assertEquals(expP1.getMass(), ret.getAllParticles().get(0).getMass());
        Assert.assertEquals(expP2.getRadius(), ret.getAllParticles().get(1).getRadius());
        Assert.assertEquals(expP2.getMass(), ret.getAllParticles().get(1).getMass());
    }

    @Test
    public void loadDynamicTest(){
        List<Particle> expectedParts = new ArrayList<>();
        expectedParts.add(new Particle(3.0, 3.0, 0.0, 0.0, 0.7, 2.0, 0.0));
        expectedParts.add(new Particle(0.48313799,2.7140073,-0.012226598,0.023783749, 0.2, 0.9, 0.0));
        expectedParts.add(new Particle(0.83454679,4.2652592,-0.44342112,-0.10132946,  0.2, 0.9, 0.0));
        SimInfo staticinfo = fileHandler.loadStaticFile("resources/RandomStaticInput.txt");
        SimInfo ret = fileHandler.loadDynamicFile("resources/RandomDynamicInput.txt", staticinfo);
        List<Particle> resultParts = new ArrayList<>(3);
        resultParts.add(ret.getAllParticles().get(0));
        resultParts.add(ret.getAllParticles().get(1));
        resultParts.add(ret.getAllParticles().get(98));
        Assert.assertNotNull(ret);
        Assert.assertEquals(130, ret.getN());
        for(int i = 0; i < 3; i++){
            Particle p1 = expectedParts.get(i);
            Particle p2 = resultParts.get(i);
            Assert.assertEquals(p1.getX(), p2.getX(), 0.000001);
            Assert.assertEquals(p1.getY(), p2.getY(), 0.000001);
            Assert.assertEquals(p1.getVx(), p2.getVx(), 0.000001);
            Assert.assertEquals(p1.getVy(), p2.getVy(), 0.000001);
            Assert.assertEquals(p1.getRadius(), p2.getRadius(), 0.000001);
            Assert.assertEquals(p1.getMass(), p2.getMass(), 0.000001);
        }
    }
}
