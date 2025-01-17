package Exercitiul1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    public static HashMap<Integer,Carte> citire() {
        try {
            File file = new File("src/main/resources/carti.json");
            ObjectMapper mapper = new ObjectMapper();
            HashMap<Integer,Carte> carti = mapper.readValue(file, new TypeReference<HashMap<Integer,Carte>>(){});
            return carti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void scriere(HashMap<Integer,Carte> carti) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/carti.json");
            mapper.writeValue(file,carti);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void afisare(HashMap<Integer,Carte> carti)
    {
        var entryset = carti.entrySet();
        var it=entryset.iterator();
        while(it.hasNext())
        {
            var entry=it.next();
            Integer idCarte=entry.getKey();
            Carte carte=entry.getValue();
            System.out.println("Cheie: "+idCarte+" Valoare: "+carte.toString());
        }
    }

    public static void stergere(HashMap<Integer,Carte> carti,Integer id)
    {
        var entryset = carti.entrySet();
        var it=entryset.iterator();
        while(it.hasNext())
        {
            var entry=it.next();
            Integer idCarte=entry.getKey();
            Carte carte=entry.getValue();
            if(idCarte.equals(id))
                it.remove();
        }
    }

    public static void adaugare(HashMap<Integer,Carte> carti, String titlu, String autor, Integer an)
    {
        var entryset = carti.entrySet();
        var it=entryset.iterator();
        Integer ct=0;
        Boolean ok=false;
        while(it.hasNext() && ok==false)
        {
            var entry=it.next();
            Integer idCarte=entry.getKey();
            if(idCarte.equals(ct)==false) {
                carti.putIfAbsent(ct, new Carte(titlu, autor, an));
                ok=true;
            }
            ++ct;
        }
        if(ok==false)
            carti.putIfAbsent(ct, new Carte(titlu, autor, an));
    }

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        HashMap<Integer,Carte> carti=citire();
        Integer opt,id,an;
        String titlu,autor;
        Set<Carte> carte_set;

        do {
            System.out.println("0.Exit");
            System.out.println("1.Afisare colectie");
            System.out.println("2.Stergere carte din colectia Map");
            System.out.println("3.Adaugare carte la colectie");
            System.out.println("4.Salvare in fisier JSON");
            System.out.println("5.Afisare cartile autorului Yual Noah Harari");
            System.out.println("6.Afisare ordonata dupa titlu ale autorului Harari");
            System.out.println("7.Afisare cea mai veche carte a lui Harari");
            System.out.println("Optiunea dvs este:");
            opt=scanner.nextInt();

            switch (opt) {
                case 0:
                    System.out.println("Exit");
                    break;
                case 1:
                    afisare(carti);
                    break;
                case 2:
                    afisare(carti);
                    System.out.println("Alegeti id-ul cartii pe care doriti sa o stergeti");
                    id=scanner.nextInt();
                    stergere(carti,id);
                    break;
                case 3:
                    System.out.println("Titlul cartii: ");
                    titlu=scanner.next();
                    System.out.println("Autorul cartii: ");
                    autor=scanner.next();
                    System.out.println("Anul publicarii cartii: ");
                    an=scanner.nextInt();
                    adaugare(carti,titlu,autor,an);
                    break;
                case 4:
                    scriere(carti);
                    break;
                case 5:
                    //Set<Map.Entry<Integer, Carte>> carte_set=carti.entrySet().stream().filter(a->a.getValue().autorul().equalsIgnoreCase("Yuval Noah Harari")).collect(Collectors.toSet());
                    carte_set=carti.values().stream().filter(a->a.autorul().equalsIgnoreCase("Yuval Noah Harari")).collect(Collectors.toSet());
                    carte_set.forEach(System.out::println);
                    break;
                case 6:
                    carte_set=carti.values().stream().filter(a->a.autorul().equalsIgnoreCase("Yuval Noah Harari")).collect(Collectors.toSet());
                    carte_set.stream().sorted((a1,a2)->a1.titlul().compareToIgnoreCase(a2.titlul())).forEach(System.out::println);
                    break;
                case 7:
                    carte_set=carti.values().stream().filter(a->a.autorul().equalsIgnoreCase("Yuval Noah Harari")).collect(Collectors.toSet());
                    carte_set.stream().min((a1,a2)->a1.anul().compareTo(a2.anul())).ifPresentOrElse(System.out::println,()-> System.out.println("Valoare lipsa"));
                    break;
            }


        }while(opt!=0);


    }
}
