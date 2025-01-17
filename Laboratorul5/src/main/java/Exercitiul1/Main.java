package Exercitiul1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void scriere(List<PerecheNumere> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/perechi.json");
            mapper.writeValue(file,lista);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<PerecheNumere> citire() {
        try {
            File file = new File("src/main/resources/perechi.json");
            ObjectMapper mapper = new ObjectMapper();
            List<PerecheNumere> perechi = mapper.readValue(file, new TypeReference<List<PerecheNumere>>(){});
            return perechi;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {

        List<PerecheNumere> perechi=new ArrayList<PerecheNumere>();
        perechi.add(new PerecheNumere(5,8));
        perechi.add(new PerecheNumere(4,24));
        perechi.add(new PerecheNumere(121,4));

        for(PerecheNumere pair : perechi) {
            System.out.println(pair);
            System.out.println("Sunt nr consecutive Fibonacci: "+pair.consec_fibo());
            System.out.println("CMMMC-ul lor este: "+pair.cmmmc());
            System.out.println("Au suma cifrelor egale: "+pair.s_cif_equal());
            System.out.println("Au acelasi nr de cifre pare: "+pair.nr_cif_pare_equal());
        }

        scriere(perechi);

    }
}
