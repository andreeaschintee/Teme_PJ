import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void scriere(List<Angajat> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            File file=new File("src/main/resources/Lab6/angajati.json");
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Angajat> citire() {
        try {
            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> persoane = mapper
                    .readValue(file, new TypeReference<List<Angajat>>(){});
            return persoane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        List<Angajat>a=new ArrayList<>();
        a.add(new Angajat("Andrei", "Manager", LocalDate.parse("2022-03-15"), 2800));
        a.add(new Angajat("Elena", "Analist Financiar", LocalDate.parse("2016-10-20"), 1700));
        a.add(new Angajat("Radu", "Dezvoltator Software", LocalDate.parse("2019-07-01"), 3200));
        a.add(new Angajat("Clara", "Director Executiv", LocalDate.parse("2020-06-12"), 5500));
        a.add(new Angajat("Bogdan", "Suport Tehnic", LocalDate.parse("2021-05-18"), 4200));
        a.add(new Angajat("Diana", "Manager Proiect", LocalDate.parse("2022-02-14"), 2600));
        a.add(new Angajat("George", "Consultant HR", LocalDate.parse("2023-01-05"), 2400));

        scriere(a);

        List<Angajat> Angajati=citire();
       // Angajati.forEach(System.out::println);

        Scanner sc=new Scanner(System.in);
        while(true)
        {
            System.out.println("\n");
            System.out.println("0.Iesire");
            System.out.println("1.Afisare angajati");
            System.out.println("2.Afisare peste 2500");
            System.out.println("3.Lista angajati anul trecut aprilie (sef sau director)");
            System.out.println("4.Afisare angajati !(sef sau director)");
            System.out.println("5.Extragerea din lista de angajați a unei liste de String-uri cu numele angajaților");
            System.out.println("6.Afisarea salariilor <3000 (doar salariile");
            System.out.println("7.Afisarea primului angajat din firma");
            System.out.println("8.Salariul min/max/mediu");
            System.out.println("9.Afisare daca exista cel putin un Ion in firma");
            System.out.println("10.Nr Persoane angajata vara anului precedent");
            System.out.println("Optiunea dvs:");
            int opt=sc.nextInt();
            switch (opt) {
                case 0:
                    System.exit(0);
                case 1:
                    Angajati.forEach(System.out::println);
                    break;

                case 2:
                    Angajati.stream()
                            .filter(p -> p.getSalariul() > 2500)
                            .forEach(System.out::println);
                    break;

                case 3:
                    List<Angajat> l = Angajati.stream()
                            .filter(p -> p.getData_angajarii().getYear() == LocalDate.now().getYear() - 1 && p.getData_angajarii().getMonthValue() == 4 && (p.getPostul().equals("Sef") || p.getPostul().equals("Director")))
                            .collect(Collectors.toList());
                    l.forEach(System.out::println);
                    break;

                case 4:
                    Angajati.stream()
                            .filter(p -> !(p.getPostul().equals("Sef") || p.getPostul().equals("Director")))
                            .forEach(System.out::println);
                    break;
                case 5:
                    List<String> nume = Angajati.stream()
                            .map(Angajat::getNumele)
                            .collect(Collectors.toList());
                    nume.forEach(System.out::println);
                    break;
                case 6:
                    Angajati.stream()
                            .filter(p -> p.getSalariul() < 3000)
                            .map(Angajat::getSalariul)
                            .forEach(System.out::println);
                    break;
                case 7:
                    Angajati.stream()
                            .min(Comparator.comparing(Angajat::getData_angajarii))
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Nu exista angajati"));
                    break;
                    case 8:
                    System.out.println("Salariul minim: "+Angajati.stream()
                            .map(Angajat::getSalariul)
                            .min(Comparator.comparing(c->c)).get());

                    System.out.println("Salariul maxim: "+Angajati.stream()
                            .map(Angajat::getSalariul)
                            .max(Comparator.comparing(c->c)).get());

                    System.out.println("Salariul mediu: "+Angajati.stream()
                            .mapToDouble(Angajat::getSalariul)
                            .sum()/Angajati.size());

                    break;
                case 9:
                    if(Angajati.stream().anyMatch(p->p.getNumele().equals("Ion")))
                        System.out.println("Firma are cel puțin un Ion angajat");
                    else
                        System.out.println("Firma nu are nici un Ion angajat");
                    break;
                case 10:
                    System.out.println("Numarul de angajati angajati vara anului precedent: "+Angajati.stream()
                            .filter(p->p.getData_angajarii().getYear()==LocalDate.now().getYear()-1 && (p.getData_angajarii().getMonthValue()>=6 && p.getData_angajarii().getMonthValue()<=8))
                            .count());
                    break;
                default:
                    System.out.println("Optiune invalida");
                    break;
            }
        }
    }
}
