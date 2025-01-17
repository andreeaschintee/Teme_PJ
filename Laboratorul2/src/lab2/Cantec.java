package lab2;
import java.io.*;
import java.util.*;

class Vers {
    private String vers;

    // Constructor
    public Vers(String vers) {
        this.vers = vers;
    }

    // Metoda pentru a număra cuvintele din vers
    public int numaraCuvinte() {
        String[] cuvinte = vers.trim().split("\\s+");
        return cuvinte.length;
    }

    // Metoda pentru a număra vocalele din vers
    public int numaraVocale() {
        int numarVocale = 0;
        for (char c : vers.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                numarVocale++;
            }
        }
        return numarVocale;
    }

    // Metoda pentru a verifica dacă versul se termină cu o anumită secvență
    public boolean seTerminaCu(String secventa) {
        return vers.endsWith(secventa);
    }

    // Metoda pentru a transforma versul în majuscule
    public void transformaInMajuscule() {
        vers = vers.toUpperCase();
    }

    // Metoda pentru a returna versul procesat
    public String getVers() {
        return vers;
    }
}

public class Cantec {
    public static void main(String[] args) {
        List<Vers> versuri = citesteVersuriDinFisier("cantec_in.txt");

        if (versuri != null) {
            String secventaAleasa = "aleasa";  // Secvența cu care trebuie să se încheie versurile pentru a adăuga *
            Random random = new Random();

            // Scrierea rezultatelor în fișierul de ieșire
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("cantec_out.txt"))) {
                for (Vers vers : versuri) {
                    // Verificăm dacă trebuie să transformăm în majuscule
                    if (random.nextDouble() < 0.1) {
                        vers.transformaInMajuscule();
                    }

                    // Numărăm cuvintele și vocalele
                    int numarCuvinte = vers.numaraCuvinte();
                    int numarVocale = vers.numaraVocale();

                    // Construim linia pentru fișierul de ieșire
                    StringBuilder linie = new StringBuilder();
                    linie.append(vers.getVers())
                            .append(" [Cuvinte: ").append(numarCuvinte)
                            .append(", Vocale: ").append(numarVocale).append("]");

                    // Adăugăm steluță dacă versul se termină cu secvența aleasă
                    if (vers.seTerminaCu(secventaAleasa)) {
                        linie.append(" *");
                    }

                    // Scriem linia în fișier
                    writer.write(linie.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Eroare la scrierea în fișier: " + e.getMessage());
            }
        }
    }

    // Metoda pentru citirea versurilor din fișier
    public static List<Vers> citesteVersuriDinFisier(String fisier) {
        List<Vers> versuri = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fisier))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                versuri.add(new Vers(linie));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
            return null;
        }
        return versuri;
    }
}
