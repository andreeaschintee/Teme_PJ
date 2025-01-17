package Exercitiul2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MainApp {

    public static void Adaugare_manuala(Set<InstrumentMuzical> instrumente)
    {
        Scanner scanner= new Scanner(System.in);
        Integer pret,nr_corzi,nr_tobe,nr_cinele;
        String producator;
        TipChitara tip_chitara;
        TipTobe tip_tobe;
        for(int i=0;i<3;++i) {
            System.out.println("Chitara "+i+":\n");
            System.out.println("Producator: ");
            producator = scanner.next();
            System.out.println("Pret: ");
            pret = scanner.nextInt();
            System.out.println("Tip chitara: 0)ELECTRICA 1)ACUSTICA 2)CLASICA: ");
            tip_chitara = TipChitara.values()[scanner.nextInt()];
            System.out.println("Nr_corzi: ");
            nr_corzi = scanner.nextInt();
            instrumente.add(new Chitara(producator,pret,tip_chitara,nr_corzi));
        }
        for(int i=0;i<3;++i) {
            System.out.println("Set de tobe "+i+":\n");
            System.out.println("Producator: ");
            producator = scanner.next();
            System.out.println("Pret: ");
            pret = scanner.nextInt();
            System.out.println("Tip tobe: 0)ELECTRONICE 1)ACUSTICE: ");
            tip_tobe= TipTobe.values()[scanner.nextInt()];
            System.out.println("Nr_tobe: ");
            nr_tobe = scanner.nextInt();
            System.out.println("Nr_cinele: ");
            nr_cinele = scanner.nextInt();
            instrumente.add(new SetTobe(producator,pret,tip_tobe,nr_tobe,nr_cinele));
        }
    }

    public static void scriere(Set<InstrumentMuzical> instrumente) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            File file=new File("src/main/resources/instrumente.json");
            mapper.writeValue(file,instrumente);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> citire() {
        try {
            File file = new File("src/main/resources/instrumente.json");
            ObjectMapper mapper = new ObjectMapper();
            Set<InstrumentMuzical> instrumente = mapper.readValue(file, new TypeReference<Set<InstrumentMuzical>>(){});
            return instrumente;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Set<InstrumentMuzical> instrumente=new HashSet<>();
        Integer opt,pret,nr_corzi,nr_tobe,nr_cinele;
        String producator;
        TipChitara tip_chitara;
        TipTobe tip_tobe;

        do {
            System.out.println("0.Exit");
            System.out.println("1.Introducere 3 chitare si 3 tobe");
            System.out.println("2.Salvare colectie in fisier json");
            System.out.println("3.Incarcare date din fisier json");
            System.out.println("4.Afisare implementare utilizata");
            System.out.println("5.Introducere duplicat");
            System.out.println("6.Stergere elemente cu pret >3000");
            System.out.println("7.Afisare date chitari cu instanceof");
            System.out.println("8.Afisare date tobe cu getClass");
            System.out.println("9.Afisare date despre chitara cu cele mai multe corzi");
            System.out.println("10.Afissarea datele tobelor acustice, ordonate după numărul de tobe din set");
            System.out.println("Optiunea dvs este:");
            opt=scanner.nextInt();

            switch (opt) {
                case 0:
                    System.out.println("La revedere!");
                    break;
                case 1:
                    Adaugare_manuala(instrumente);
                    break;
                case 2:
                    scriere(instrumente);
                    break;
                case 3:
                    instrumente=citire();
                    break;
                case 4:
                    System.out.println("Implementare utilizata: "+instrumente.getClass().getSimpleName());
                    break;
                case 5:
                    InstrumentMuzical aux=instrumente.iterator().next();
                    if(!instrumente.add(aux))
                        System.out.println("Elementul exista deja");
//                    if(instrumente.contains(aux))
//                        System.out.println("Elementul exista deja");
                    break;
                case 6:
                    instrumente.removeIf(i->i.getPret()>3000);
                    break;
                case 7:
                    instrumente.stream().filter(i->i instanceof Chitara).forEach(System.out::println);
                    break;
                case 8:
                    instrumente.stream().filter(i->i.getClass()==SetTobe.class).forEach(System.out::println);
                    break;
                case 9:
                    instrumente.stream().filter(i->i instanceof Chitara).max((i1,i2)->((Chitara) i1).getNr_corzi().compareTo(((Chitara) i2).getNr_corzi())).ifPresentOrElse(System.out::println, ()-> System.out.println("Nu exista un element maxim"));
                    break;
                case 10:
                    instrumente.stream().filter(i->i instanceof SetTobe).filter(i->((SetTobe) i).getTip_tobe()==TipTobe.ACUSTICE).sorted((i1,i2)->((SetTobe) i1).getNr_tobe().compareTo(((SetTobe)i2).getNr_tobe())).forEach(System.out::println);
                    break;
            }


        }while(opt!=0);


    }
}
