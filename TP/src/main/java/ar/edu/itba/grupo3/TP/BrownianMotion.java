package ar.edu.itba.grupo3.TP;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class BrownianMotion {

    private SimInfo simulationInfo;
    private FileHandler fileHandler;
    private final float timeToSave;
    private final float deltaT;
    private float timer;
    private int savedCount;
    private PriorityQueue<ParticleHit> hits;

    public BrownianMotion(){
        hits = new PriorityQueue<>(Comparator.comparing(ParticleHit::getTimeToHit));
        fileHandler = new FileHandler();
        timeToSave = 10.0f;
        deltaT = 1.0f;
        timer = 0.0f;
        savedCount = 0;
        simulationInfo = fileHandler.loadData("resources/RandomStaticInput.txt",
                "resources/RandomDynamicInput.txt");
    }

    public void simulate(){
        //generar todos los tcs posibles
        generateAllTimes();
        while(true) {
            //agarrar el menor tc
            ParticleHit nextHit = hits.poll();
            simulationInfo.setHitsCount(simulationInfo.getHitsCount()+1);
            if(nextHit == null || nextHit.getTimeToHit() == null || nextHit.getP1() == null) continue;
            //evolucionar el sistema hasta tc
            forwardSystem(nextHit.getTimeToHit());
            //avanzar el reloj
            timer += deltaT;
            //guardar el sistema si el reloj lo dicta
            if(timer > timeToSave) saveState();
            //si el choque fue entre la partícula 0 y la pared, cortar
            if( nextHit.getP1().getId().equals(0) && nextHit.getP2() == null) break;
            //colisionar
            nextHit.getP1().elasticCollision(nextHit.getP2(), simulationInfo.getL());
            //recalcular los tc de las partículas que chocaron
            recalculateTimesForParticles(nextHit.getP1(), nextHit.getP2());
        }
    }

    public void saveState(){
        //save to file
        fileHandler.saveDynamic(simulationInfo.getAllParticles(), savedCount++);
        fileHandler.saveHitFreq(simulationInfo.getHitsCount());
        simulationInfo.setHitsCount(0);
        timer = 0;
    }

    public void forwardSystem(double tc){
        simulationInfo.getAllParticles().forEach(p -> p.moveToTime(tc));
        hits.forEach(h -> h.setTimeToHit(h.getTimeToHit() - tc));
    }

    public void generateAllTimes(){
        Double aux;
        Particle p1, p2;
        for(int i = 0; i < simulationInfo.getAllParticles().size(); i++){
            p1 = simulationInfo.getAllParticles().get(i);
            //optimización j arranca en i+1 para no volver a calcular los que ya calculó
            for(int j = i+1; j < simulationInfo.getAllParticles().size(); j++){
                p2 = simulationInfo.getAllParticles().get(j);
                aux = p1.timeToHitParticle(p2);
                if(aux != null) hits.add(new ParticleHit(aux, p1, p2));
            }
            final Particle auxP1 = p1;
            hits.addAll(p1.timeToHitWalls(simulationInfo.getL())
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
        for(Particle p : simulationInfo.getAllParticles()){
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
        hits.addAll(p1.timeToHitWalls(simulationInfo.getL())
                .stream()
                .map(t -> new ParticleHit(t, p1, null))
                .collect(Collectors.toList()));
        if(p2 != null) {
            hits.addAll(p2.timeToHitWalls(simulationInfo.getL())
                    .stream()
                    .map(t -> new ParticleHit(t, p2, null))
                    .collect(Collectors.toList()));
        }
    }

}
