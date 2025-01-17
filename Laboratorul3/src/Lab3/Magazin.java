package Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Magazin {

    private List<Ex2> produse = new ArrayList<>();

    public static void main(String[] args) {
        Magazin magazin = new Magazin();
        magazin.citesteProduseDinFisier("produse.csv");
        magazin.meniu();
    }

    public void citesteProduseDinFisier(String fisier) {
        try {
            List<String> linii = Files.readAllLines(Paths.get(fisier));
            for (String linie : linii) {
                String[] campuri = linie.split(",");
                String denumire = campuri[0].trim();
                double pret = Double.parseDouble(campuri[1].trim());
                int cantitate = Integer.parseInt(campuri[2].trim());
                LocalDate dataExpirarii = LocalDate.parse(campuri[3].trim());
                produse.add(new Ex2(denumire, pret, cantitate, dataExpirarii));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }

    public void meniu() {
        Scanner scanner = new Scanner(System.in);
        int optiune;

        do {
            System.out.println("Meniu:");
            System.out.println("1. Afișează toate produsele");
            System.out.println("2. Afișează produsele expirate");
            System.out.println("3. Vinde un produs");
            System.out.println("4. Afișează produsele cu prețul minim");
            System.out.println("5. Salvează produsele cu cantitate mai mică decât o valoare dată");
            System.out.println("6. Iesire");

            optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            switch (optiune) {
                case 1:
                    afiseazaToateProdusele();
                    break;
                case 2:
                    afiseazaProduseleExpirate();
                    break;
                case 3:
                    vindeProdus(scanner);
                    break;
                case 4:
                    afiseazaProduseCuPretMinim();
                    break;
                case 5:
                    salveazaProduseCuCantitateMica(scanner);
                    break;
                case 6:
                    System.out.println("Program terminat.");
                    break;
                default:
                    System.out.println("Optiune invalida.");
            }

        } while (optiune != 6);

        scanner.close();
    }

    public void afiseazaToateProdusele() {
        produse.forEach(System.out::println);
    }

    public void afiseazaProduseleExpirate() {
        LocalDate azi = LocalDate.now();
        produse.stream()
                .filter(p -> p.getDataExpirarii().isBefore(azi))
                .forEach(System.out::println);
    }

    public void vindeProdus(Scanner scanner) {
        System.out.println("Introdu denumirea produsului:");
        String denumire = scanner.nextLine();

        Optional<Ex2> optionalProdus = produse.stream()
                .filter(p -> p.getDenumire().equalsIgnoreCase(denumire))
                .findFirst();

        if (optionalProdus.isPresent()) {
            Ex2 produs = optionalProdus.get();

            System.out.println("Introdu cantitatea de vândut:");
            int cantitateDeVandut = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            if (cantitateDeVandut <= produs.getCantitate()) {
                produs.setCantitate(produs.getCantitate() - cantitateDeVandut);
                Ex2.adaugaIncasari(cantitateDeVandut * produs.getPret());
                System.out.println("Produs vândut cu succes. Încasări totale: " + Ex2.getIncasari());

                if (produs.getCantitate() == 0) {
                    produse.remove(produs);
                    System.out.println("Produsul a fost eliminat din stoc.");
                }
            } else {
                System.out.println("Cantitate insuficientă pe stoc.");
            }
        } else {
            System.out.println("Produsul nu există.");
        }
    }

    public void afiseazaProduseCuPretMinim() {
        OptionalDouble pretMinim = produse.stream()
                .mapToDouble(Ex2::getPret)
                .min();

        if (pretMinim.isPresent()) {
            produse.stream()
                    .filter(p -> p.getPret() == pretMinim.getAsDouble())
                    .forEach(System.out::println);
        } else {
            System.out.println("Nu există produse.");
        }
    }

    public void salveazaProduseCuCantitateMica(Scanner scanner) {
        System.out.println("Introdu valoarea de referință pentru cantitate:");
        int cantitateMaxima = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        List<Ex2> produseCuCantitateMica = produse.stream()
                .filter(p -> p.getCantitate() < cantitateMaxima)
                .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter(new FileWriter("produse_cu_cantitate_mica.csv"))) {
            for (Ex2 produs : produseCuCantitateMica) {
                writer.println(produs.getDenumire() + "," + produs.getPret() + "," + produs.getCantitate() + "," + produs.getDataExpirarii());
            }
            System.out.println("Produsele au fost salvate în fișier.");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea fișierului: " + e.getMessage());
        }
    }
}
