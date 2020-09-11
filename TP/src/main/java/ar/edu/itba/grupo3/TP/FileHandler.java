package ar.edu.itba.grupo3.TP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

}
