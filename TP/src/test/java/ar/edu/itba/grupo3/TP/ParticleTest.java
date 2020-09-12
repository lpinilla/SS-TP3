package ar.edu.itba.grupo3.TP;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParticleTest {

    @Test
    public void timeToHitWallsTest(){
        Particle p = new Particle(3.0, 3.0, 1.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> expected = new ArrayList<>();
        expected.add(3.0);
        List<Double> ret = p.timeToHitWalls(6);
        Assert.assertNotNull(ret);
        Assert.assertArrayEquals(expected.toArray(), ret.toArray());
    }


    @Test
    public void timeToHitWallsTest2(){
        Particle p = new Particle(3.0, 3.0, -1.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> expected = new ArrayList<>();
        expected.add(3.0);
        List<Double> ret = p.timeToHitWalls(6);
        Assert.assertNotNull(ret);
        Assert.assertArrayEquals(expected.toArray(), ret.toArray());
    }

    @Test
    public void timeToHitWallsTest3(){
        Particle p = new Particle(3.0, 3.0, 0.0, 1.0, 0.0, 0.0, 0.0);
        List<Double> expected = new ArrayList<>();
        expected.add(3.0);
        List<Double> ret = p.timeToHitWalls(6);
        Assert.assertNotNull(ret);
        Assert.assertArrayEquals(expected.toArray(), ret.toArray());
    }


    @Test
    public void timeToHitWallsTest4(){
        Particle p = new Particle(3.0, 3.0, 0.0, -1.0, 0.0, 0.0, 0.0);
        List<Double> expected = new ArrayList<>();
        expected.add(3.0);
        List<Double> ret = p.timeToHitWalls(6);
        Assert.assertNotNull(ret);
        Assert.assertArrayEquals(expected.toArray(), ret.toArray());
    }


    @Test
    public void timeToHitWallsTest5(){
        Particle p = new Particle(3.0, 3.0, 2.0, 1.0, 0.0, 0.0, 0.0);
        List<Double> expected = new ArrayList<>();
        expected.add(1.5d);
        expected.add(3.0);
        List<Double> ret = p.timeToHitWalls(6);
        Assert.assertNotNull(ret);
        Assert.assertArrayEquals(expected.toArray(), ret.toArray());
    }


    @Test
    public void timeToHitWallsTest6(){
        Particle p = new Particle(3.0, 3.0, 1.0, 2.0, 0.0, 0.0, 0.0);
        List<Double> expected = new ArrayList<>();
        expected.add(3.0);
        expected.add(1.5d);
        List<Double> ret = p.timeToHitWalls(6);
        Assert.assertNotNull(ret);
        Assert.assertArrayEquals(expected.toArray(), ret.toArray());
    }


    @Test
    public void timeToHitWallsTest7(){
        Particle p = new Particle(3.0, 3.0, 1.0, -2.0, 0.0, 0.0, 0.0);
        List<Double> expected = new ArrayList<>();
        expected.add(3.0);
        expected.add(1.5d);
        List<Double> ret = p.timeToHitWalls(6);
        Assert.assertNotNull(ret);
        Assert.assertArrayEquals(expected.toArray(), ret.toArray());
    }

}
