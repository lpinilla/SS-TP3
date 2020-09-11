package ar.edu.itba.grupo3.TP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileHandler {

    public SimInfo loadData(String pathStatic, String pathDynamic){
        SimInfo info = loadStaticFile(pathStatic);
        return loadDynamicFile(pathDynamic, info);
    }

    public SimInfo loadStaticFile(String path)  {
        if (path.isEmpty()) return null;
        SimInfo ret = new SimInfo();
        List<Particle> allParticles = new ArrayList<>();
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            ret.setN(Integer.parseInt(br.readLine()));
            ret.setL(Integer.parseInt(br.readLine()));
            //particles
            int index = 0;
            while ((s = br.readLine()) != null) {
                String[] rad_prop = s.split(" {3}");
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

    public SimInfo loadDynamicFile(String path, SimInfo info) {
        if (path.isEmpty()) return null;
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            int time = Integer.parseInt(br.readLine()); //ignore first line
            //particles
            int index = 0;
            Particle aux;
            while ((s = br.readLine()) != null) {
                String[] position = s.split(" {3}");
                aux = info.getAllParticles().get(index);
                aux.setX(Double.parseDouble(position[0]));
                aux.setY(Double.parseDouble(position[1]));
                aux.setVx(Double.parseDouble(position[2]));
                aux.setVy(Double.parseDouble(position[3]));
                //putInCell(aux);
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
        String fileOutputPath = "resources/hitFreq.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileOutputPath), true) );
            writer.write(Integer.toString(hitCount));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
