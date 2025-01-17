package com.application;

import com.application.repository.AutovehiculJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class VehicleManagementApplication implements CommandLineRunner {

    @Autowired
    AutovehiculJpaRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(VehicleManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Afișare autovehicule");
            System.out.println("2. Adăugare autovehicul");
            System.out.println("3. Ștergere autovehicul");
            System.out.println("4. Căutare autovehicul după număr de înmatriculare");
            System.out.println("5. Listare autovehicule");
            System.out.println("6. Număr autovehicule pentru o anumită marcă");
            System.out.println("7. Număr autovehicule cu kilometraj sub 100k");
            System.out.println("8. Extragere autovehicule mai noi de 5 ani");
            System.out.println("0. Ieșire");
            System.out.print("Alegeți o opțiune: ");
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Lista completă de autovehicule:");
                    repo.findAll().forEach(System.out::println);
                    logger.info("Toate autovehiculele: " + repo.findAll());
                    break;
                case 2:
                    System.out.println("Introduceți detaliile autovehiculului:");
                    System.out.print("Număr de înmatriculare: ");
                    String numarInmatriculare = scanner.next();
                    System.out.print("Marcă: ");
                    String marca = scanner.next();
                    System.out.print("An fabricație: ");
                    int anFabricatie = scanner.nextInt();
                    System.out.print("Culoare: ");
                    String culoare = scanner.next();
                    System.out.print("Kilometraj: ");
                    long kilometraj = scanner.nextLong();
                    repo.insert(new Autovehicul(numarInmatriculare, marca, anFabricatie, culoare, kilometraj));
                    repo.findAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Introduceți numărul de înmatriculare al autovehiculului de șters: ");
                    String inmatriculareStergere = scanner.next();
                    repo.deleteById(inmatriculareStergere);
                    repo.findAll().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Introduceți numărul de înmatriculare al autovehiculului căutat: ");
                    String inmatriculareCautare = scanner.next();
                    System.out.println(repo.findById(inmatriculareCautare));
                    break;
                case 5:
                    System.out.println("Listă de autovehicule:");
                    List<Autovehicul> autovehicule = repo.findAll();
                    autovehicule.forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Introduceți marca: ");
                    String marcaFiltrare = scanner.next();
                    long numarMarca = repo.findAll()
                            .stream()
                            .filter(vehicul -> vehicul.getMarca().equals(marcaFiltrare))
                            .count();
                    System.out.println("Număr de autovehicule marca " + marcaFiltrare + ": " + numarMarca);
                    break;
                case 7:
                    long sub100k = repo.findAll()
                            .stream()
                            .filter(vehicul -> vehicul.getKilometraj() < 100000)
                            .count();
                    System.out.println("Număr de autovehicule cu kilometraj sub 100k: " + sub100k);
                    break;
                case 8:
                    System.out.println("Autovehicule mai noi de 5 ani:");
                    repo.findAll()
                            .stream()
                            .filter(vehicul -> vehicul.getAnFabricatie() + 5 >= LocalDate.now().getYear())
                            .forEach(System.out::println);
                    break;
                default:
                    System.out.println("Opțiune invalidă.");
                    break;
            }
        }
    }
}
