package ar.edu.itba.grupo3.TP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class GenerateInput {

    public static void inputGenerator(int N,int L) {
        generateInputs(N,L);
    }


    //N = #particles
    //L = length board
    private static void generateInputs(int N,int L) {
        Random r = new Random();
        //particles radius between 0 and 1;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("resources/RandomStaticInput.txt")));
            writer.write(Integer.toString(N));
            writer.newLine();
            writer.write(Integer.toString(L));
            writer.newLine();

            //creating ratios of particles
            for (int i = 0; i < N; i++) {
                for(int j=0;j<2;j++){
                    double rad = r.nextDouble() * L;
                    double prop = r.nextDouble() * (Math.PI * 2);
                    if(j!=1){
                        writer.write(String.format(Locale.US, "%.4f", rad)+"   ");
                    }
                    else{
                        //write another property, 1 in this case because its an example
                        writer.write(String.format(Locale.US, "%.4f", prop));
                    }
                }
                writer.newLine();
            }
            writer.flush();
            writer.close();
        }catch (IOException e) {
            System.out.println("Error creating Static Input");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("resources/RandomDynamicInput.txt")));

            writer.write("0"); //time of dynamic particles.
            writer.newLine();

            for (int i = 0; i < N; i++) {
                double ran= r.nextDouble() * L;
                double ran2 =r.nextDouble() * L;
                writer.write(String.format(Locale.US, "%6.7e", ran)
                                + "   "
                                + String.format(Locale.US, "%6.7e", ran2)
                );
                writer.newLine();
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println("Error creating Dynamic Input");
        }

    }
}
