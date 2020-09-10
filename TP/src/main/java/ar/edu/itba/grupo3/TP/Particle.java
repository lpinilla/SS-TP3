package ar.edu.itba.grupo3.TP;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Particle implements Comparable<Particle> {
    private Integer id; //id of particle
    private Double x; //x position of particle
    private Double y; //y position of particle
    private Double vx; //x velocity of particle
    private Double vy; //y velocity of particle
    private Double radius; //radius of particle
    private Double mass; //mass of particle
    private Double angle; //value of angle
    private Set<Particle> neighbours; //list of neighbours
    private final double speed = 0.03;

    //Vamos a tener una lista de particulas general, la primer particula de la lista hace referencia a la particula "padre" ubicada en el casillero cero
    //la segunda particula del array hace referencia a la particula "padre" ubicada en el segundo casillero del tablero....
    //"padre" llamamos a la primer particula que esta ubicada en ese casillero. el next de esa particula, hace refernecia
    //a otra particula ubicada en el mismo casillero
    private List<Particle> particlesSameCellList;

    public Particle(Double x, Double y, Double vx, Double vy, Double radius, Double mass, Double angle) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
        this.radius = radius;
        this.angle = angle;
        this.neighbours = new TreeSet<>();
        this.particlesSameCellList = new LinkedList<>();
        this.particlesSameCellList.add(this);
    }

    public Particle(Double radius, Double angle, Double mass, Integer id){
        this.radius = radius;
        this.angle = angle;
        this.mass = mass;
        this.id = id;
        this.x = -1.0; //placeholder
        this.vx = 0.0;
        this.vy = 0.0;
        this.y = -1.0; //placeholder
        this.neighbours = new TreeSet<>();
        this.particlesSameCellList = new LinkedList<>();
        this.particlesSameCellList.add(this);
    }

    public String toString(){
        return this.getId().toString();
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        Particle p = (Particle) o;
        return this.id.equals(p.id);
    }

    @Override
    public int compareTo(Particle particle) {
        return this.getId().compareTo(particle.getId());
    }

    public List<Particle> getParticlesFromCell(){
        return particlesSameCellList;
    }

    public void moveToTime(double tc){
        this.x = this.x + this.vx * tc;
        this.y = this.y + this.vy * tc;
    }

    public List<Double> timeToHitWalls(int L){
        List<Double> ret = new ArrayList<>();
        if(vx > 0){
            //pared a la derecha
            ret.add( (L - this.radius - this.x) / this.vx);
        }else{
            //pared a la izq
            ret.add( (this.radius - this.x) / this.vx);
        }
        if(vy > 0){
            //pared de arriba
            ret.add( (L - this.radius - this.y) / this.vy);
        }else{
            //pared de abajo
            ret.add( (this.radius - this.y) / this.vy);
        }
        return ret;
    }

    public Double timeToHitParticle(Particle p){
        double[] deltaR = new double[] {p.x - x, p.y - y };
        double[] deltaV = new double[] {p.vx - vx, p.vy - vy };
        double deltaVxdeltaR = deltaV[0] * deltaR[0] + deltaV[1] * deltaR[1];
        //caso 1
        if(deltaVxdeltaR >= 0) return null;
        //caso 2
        double deltaRad = this.radius + p.radius;
        double deltaVSquared = deltaV[0] * deltaV[0] + deltaV[1] * deltaV[1];
        double deltaRSquared = deltaR[0] * deltaR[0] + deltaR[1] * deltaR[1];
        double d = deltaVxdeltaR * deltaVxdeltaR - deltaVSquared * (deltaRSquared - deltaRad * deltaRad);
        if(d < 0) return null;
        //caso 3
        return -( (deltaVxdeltaR + Math.sqrt(d)) / deltaVSquared);
    }

    public void moveAgent(float l){
        double xPos = (Math.cos(this.getAngle()) * speed) + this.getX();
        double yPos = (Math.sin(this.getAngle()) * speed) + this.getY();
        if(xPos < 0) xPos += l;
        if(yPos < 0) yPos += l;
        if(yPos > l) yPos %= l;
        if(xPos > l) xPos %= l;
        this.setX(xPos);
        this.setY(yPos);
    }


    public void calculateNewAngle(double randomVal){
        double sinAux = Math.sin(this.getAngle());
        double cosAux = Math.cos(this.getAngle());
        Set<Particle> neighbors = this.getNeighbours();
        for(Particle p : neighbors){
            sinAux += Math.sin(p.getAngle());
            cosAux += Math.cos(p.getAngle());
        }
        if(neighbors.size() > 0) {
            sinAux /= neighbors.size() + 1;
            cosAux /= neighbors.size() + 1;
        }
        double newProperty = Math.atan2(sinAux, cosAux);
        if(newProperty < 0) newProperty += Math.PI * 2;
        double finalProperty = newProperty + randomVal;
        if(finalProperty > Math.PI * 2) finalProperty -= 2 * Math.PI;
        if(finalProperty < 0) finalProperty += Math.PI * 2;
        this.setAngle(finalProperty);
    }

}
