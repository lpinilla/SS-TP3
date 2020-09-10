package ar.edu.itba.grupo3.TP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BrownianMotion {

    PriorityQueue<ParticleHit> hits;
    List<Particle> allParticles;

    public BrownianMotion(){
        hits = new PriorityQueue<>(Comparator.comparing(ParticleHit::getTimeToHit));
        //cargar data
    }

    public void simulate(){
        //agarrar el menor tc
        ParticleHit nextHit = hits.poll();
        //evolucionar el sistema hasta tc
        //reducir el tiempo en los demás
        hits.forEach(h -> h.setTimeToHit(h.getTimeToHit() - nextHit.getTimeToHit()));
        //guardar el sistema si el reloj lo dicta
        //colisionar
        //recalcular los tc de las partículas que chocaron
    }
}
