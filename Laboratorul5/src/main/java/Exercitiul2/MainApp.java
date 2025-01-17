package Exercitiul2;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static List<Mobilier> citire() {
        try {
            File file = new File("src/main/resources/mobilier.json");
            ObjectMapper mapper = new ObjectMapper();
            List<Mobilier> mobila = mapper.readValue(file, new TypeReference<List<Mobilier>>(){});
            return mobila;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        List<Mobilier> mobila=citire();
        Integer s,opt,arie_totala;
        Float nr;
        do {
            System.out.println("0.Exit");
            System.out.println("1.Afisare date extrase");
            System.out.println("2.Afisare el de mobilier din colectie si placile care le compun");
            System.out.println("3.Caracteristicile placilor care compun o piesa de mobilier");
            System.out.println("4.Nr coli de pal necesare pt realizarea unui mobilier la alegere");
            System.out.println("Dati optiunea dvs: ");
            s=scanner.nextInt();
            switch(s)
            {
                case 0:
                    System.out.println("Exit");
                    break;
                case 1:
                    System.out.println(mobila);
                    break;
                case 2:
                    for(Mobilier mobi:mobila)
                    {
                        System.out.println(mobi.getNume());
                        System.out.println("Format din: ");
                        for(int i=0;i<mobi.getPlaci().size();i++)
                        {
                            System.out.print(mobi.getPlaci().get(i).getDescriere()+", ");
                            if(i==mobi.getPlaci().size()-1)
                                System.out.print(mobi.getPlaci().get(i).getDescriere()+"\n");
                        }
                    }
                    break;
                case 3:
                    for(int i=0;i<mobila.size();i++)
                    {
                        System.out.println(i+")"+mobila.get(i).getNume());
                    }
                    System.out.println("Alegeti piesa a carei placi doresti sa afli mai multe despre:");
                    opt=scanner.nextInt();
                    for(int i=0;i<mobila.get(opt).getPlaci().size();i++)
                    {
                        System.out.println(mobila.get(opt).getPlaci().get(i));
                    }

                    break;
                case 4:
                    arie_totala=0;
                    for(int i=0;i<mobila.size();i++)
                    {
                        System.out.println(i+")"+mobila.get(i).getNume());
                    }
                    System.out.println("Alegeti tipul de mobilier pe care doriti sa il realizati:");
                    opt=scanner.nextInt();
                    for(int i=0;i<mobila.get(opt).getPlaci().size();i++)
                    {
                        arie_totala=arie_totala+mobila.get(opt).getPlaci().get(i).getLatime() * mobila.get(opt).getPlaci().get(i).getLungime() * mobila.get(opt).getPlaci().get(i).getNr_bucati();
                    }

                    nr= Float.valueOf(arie_totala/5796000);
                    if(nr>nr.intValue() || nr==0)
                        opt=nr.intValue()+1;
                    else
                        opt=nr.intValue();
                    System.out.println("Nr de placi necesare de dimensiunea 2800x2070: "+opt);
                    break;
                default:
                    System.out.println("Optiune gresita");
                    break;
            }
        }while(s!=0);

    }
}
