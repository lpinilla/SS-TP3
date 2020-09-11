package ar.edu.itba.grupo3.TP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class BrownianMotion {

    private int L;
    private PriorityQueue<ParticleHit> hits;
    private List<Particle> allParticles;

    public BrownianMotion(){
        hits = new PriorityQueue<>(Comparator.comparing(ParticleHit::getTimeToHit));
        //cargar data
    }

    public void simulate(){
        //bruteforce agarrar todos los tc posibles
        generateAllTimes();
        //loop
        //agarrar el menor tc
        ParticleHit nextHit = hits.poll();
        //evolucionar el sistema hasta tc
        //reducir el tiempo en los demás
        hits.forEach(h -> h.setTimeToHit(h.getTimeToHit() - nextHit.getTimeToHit()));
        //guardar el sistema si el reloj lo dicta
        //colisionar
        //recalcular los tc de las partículas que chocaron
    }

    public void generateAllTimes(){
        Double aux;
        Particle p1, p2;
        for(int i = 0; i < allParticles.size(); i++){
            p1 = allParticles.get(i);
            //optimización j arranca en i+1 para no volver a calcular los que ya calculó
            for(int j = i+1; j < allParticles.size(); j++){
                p2 = allParticles.get(j);
                aux = p1.timeToHitParticle(p2);
                if(aux != null) hits.add(new ParticleHit(aux, p1, p2));
            }
            final Particle auxP1 = p1;
            hits.addAll(p1.timeToHitWalls(L)
                    .stream()
                    .map(t -> new ParticleHit(t, auxP1, null))
                    .collect(Collectors.toList()));
        }
    }

    public void recalculateTimesForParticles(Particle p1, Particle p2){
        //borrar todas las interacciones que tengan estas partículas
        hits.removeIf(h -> h.containsParticle(p1));
        if(p2 != null) hits.removeIf(h -> h.containsParticle(p2));
        //recalcular las interacciones para estas 2 partículas
        for(Particle p : allParticles){
            if(!p.equals(p1)){
                Double tc = p1.timeToHitParticle(p);
                if(tc != null) hits.add(new ParticleHit(tc, p, p1));
            }
            if(p2 != null) {
                if (!p.equals(p2)) {
                    Double tc = p2.timeToHitParticle(p);
                    if (tc != null) hits.add(new ParticleHit(tc, p, p2));
                }
            }
        }
        hits.addAll(p1.timeToHitWalls(L)
                .stream()
                .map(t -> new ParticleHit(t, p1, null))
                .collect(Collectors.toList()));
        if(p2 != null) {
            hits.addAll(p2.timeToHitWalls(L)
                    .stream()
                    .map(t -> new ParticleHit(t, p2, null))
                    .collect(Collectors.toList()));
        }
    }
}
