package ar.edu.itba.grupo3.TP;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticleHit {

    private Double timeToHit;
    private Particle p1, p2;

    public ParticleHit(Double timeToHit, Particle p1, Particle p2){
        this.timeToHit = timeToHit;
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean containsParticle(Particle p){
        if(p2 == null) return p1.equals(p);
        return p1.equals(p) || p2.equals(p);
    }

}
