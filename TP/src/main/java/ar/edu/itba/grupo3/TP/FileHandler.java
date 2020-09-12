package ar.edu.itba.grupo3.TP;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class FileHandler {

    private String staticInputFile;
    private String dynamicInputfile;
    private String dynamicFile;
    private String hitFreqFile;
    private String dcmFile;

    public FileHandler(){
        staticInputFile = dynamicInputfile = dynamicFile = hitFreqFile = dcmFile = "";
    }

    public FileHandler(String staticfile, String dynamicInputfile, String dynamicFile, String hitFreqFile, String dcmFile){
        this.staticInputFile = staticfile;
        this.dynamicInputfile = dynamicInputfile;
        this.dynamicFile = dynamicFile;
        this.hitFreqFile = hitFreqFile;
        this.dcmFile = dcmFile;
    }

    public SimInfo loadData(){
        SimInfo info = loadStaticFile();
        return loadDynamicFile(info);
    }

    public SimInfo loadStaticFile()  {
        SimInfo ret = new SimInfo();
        List<Particle> allParticles = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(staticInputFile)));
            String s;
            ret.setN(Integer.parseInt(br.readLine()));
            ret.setL(Integer.parseInt(br.readLine()));
            //particles
            int index = 0;
            while ((s = br.readLine()) != null) {
                String[] rad_prop = s.split("\t");
                allParticles.add(
                        new Particle(
                                Double.parseDouble(rad_prop[0]),
                                Double.parseDouble(rad_prop[1]),
                                index));
                index++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ret.setAllParticles(allParticles);
        return ret;
    }

    public SimInfo loadDynamicFile(SimInfo info) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(dynamicInputfile)));
            String s;
            int time = Integer.parseInt(br.readLine()); //ignore first line
            //particles
            int index = 0;
            Particle aux;
            while ((s = br.readLine()) != null) {
                String[] position = s.split("\t");
                aux = info.getAllParticles().get(index);
                aux.setX(Double.parseDouble(position[0]));
                aux.setY(Double.parseDouble(position[1]));
                aux.setVx(Double.parseDouble(position[2]));
                aux.setVy(Double.parseDouble(position[3]));
                index++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return info;
    }

    public void saveDynamic(List<Particle> particles, int n) {
        String fileOutputPath = "resources/dynamic.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileOutputPath), true) );
            writer.write(Integer.toString(n));
            writer.newLine();
            for(Particle p : particles){
                String builder = String.format(Locale.US, "%6.7e", p.getX()) + "    " +
                        String.format(Locale.US, "%6.7e", p.getY()) + "    " +
                        String.format(Locale.US, "%6.7e", p.getVx()) + "    " +
                        String.format(Locale.US, "%6.7e", p.getVy());
                writer.write(builder);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveHitFreq(int hitCount){
        String fileOutputPath = hitFreqFile;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileOutputPath), true));
            writer.write(Integer.toString(hitCount));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void saveDCM(double displacement){
        String fileOutputPath = dcmFile;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileOutputPath), true));
            writer.write(String.format(Locale.US, "%6.7e", displacement));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
