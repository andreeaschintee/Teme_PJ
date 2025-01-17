package lab2;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

class Persoana {
    private String nume;
    private String cnp;

    // Constructor cu parametri
    public Persoana(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }

    // Getter pentru nume
    public String getNume() {
        return nume;
    }

    // Getter pentru CNP
    public String getCnp() {
        return cnp;
    }

    // Metoda pentru calcularea vârstei
    public int getVarsta() {
        // Extragem data nașterii din CNP
        int anul = 0;
        int luna = Integer.parseInt(cnp.substring(3, 5));
        int ziua = Integer.parseInt(cnp.substring(5, 7));

        // Prima cifră indică secolul și sexul
        char primaCifra = cnp.charAt(0);
        if (primaCifra == '1' || primaCifra == '2') {
            anul = 1900 + Integer.parseInt(cnp.substring(1, 3));
        } else if (primaCifra == '5' || primaCifra == '6') {
            anul = 2000 + Integer.parseInt(cnp.substring(1, 3));
        }

        // Calculăm vârsta folosind LocalDate
        LocalDate dataNasterii = LocalDate.of(anul, luna, ziua);
        LocalDate dataCurenta = LocalDate.now();
        return Period.between(dataNasterii, dataCurenta).getYears();
    }

    // Metodă pentru a verifica validitatea CNP-ului
    public static boolean esteValidCnp(String cnp) {
        if (cnp.length() != 13) {
            return false;
        }

        if (!cnp.matches("\\d+")) { // Verificăm dacă toate caracterele sunt cifre
            return false;
        }

        char primaCifra = cnp.charAt(0);
        if (primaCifra != '1' && primaCifra != '2' && primaCifra != '5' && primaCifra != '6') {
            return false;
        }

        // Verificare cifră de control
        int[] multiplicatori = {2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
        int suma = 0;
        for (int i = 0; i < 12; i++) {
            suma += Character.getNumericValue(cnp.charAt(i)) * multiplicatori[i];
        }
        int cifraControl = suma % 11;
        if (cifraControl == 10) {
            cifraControl = 1;
        }

        return cifraControl == Character.getNumericValue(cnp.charAt(12));
    }
}

public class VarstaCnp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citirea numărului de persoane
        System.out.print("Introdu numărul de persoane: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // curățăm bufferul

        // Crearea unui vector pentru a stoca persoanele
        Persoana[] persoane = new Persoana[n];

        // Citirea datelor pentru fiecare persoană
        for (int i = 0; i < n; i++) {
            System.out.print("Introdu numele persoanei " + (i + 1) + ": ");
            String nume = scanner.nextLine();

            String cnp;
            while (true) {
                System.out.print("Introdu CNP-ul persoanei " + (i + 1) + ": ");
                cnp = scanner.nextLine();
                if (Persoana.esteValidCnp(cnp)) {
                    break;
                } else {
                    System.out.println("CNP-ul introdus este invalid. Te rog reintrodu!");
                }
            }

            // Adăugăm persoana în vector
            persoane[i] = new Persoana(nume, cnp);
        }

        // Afișarea informațiilor despre fiecare persoană
        System.out.println("\nInformații persoane:");
        for (Persoana persoana : persoane) {
            System.out.println(persoana.getNume() + ", CNP: " + persoana.getCnp() + ", Vârsta: " + persoana.getVarsta());
        }

        scanner.close();
    }
}
