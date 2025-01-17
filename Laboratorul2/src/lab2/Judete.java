package lab2;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Judete {
    public static void main(String[] args)  throws FileNotFoundException {
        String[] judete = citesteJudeteDinFisier("judete_in.txt");

        if (judete != null) {
            // Sortarea vectorului meu
            Arrays.sort(judete);

            // Afișarea județelor ordonate
            System.out.println("Judetele ordonate:");
            for (String judet : judete) {
                System.out.println(judet);
            }

            // Citirea județului căutat de la tastatură
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceți numele județului: ");
            String judetCautat = scanner.nextLine();

            // Căutarea binară a județului introdus
            int pozitie = Arrays.binarySearch(judete, judetCautat);

            if (pozitie >= 0) {
                System.out.println("Judetul " + judetCautat + " se află pe poziția " + pozitie + " în lista ordonată.");
            } else {
                System.out.println("Judetul " + judetCautat + " nu a fost găsit.");
            }
        }
    }

    // Aici am metoda prin care citesc judetele din fisier
    public static String[] citesteJudeteDinFisier(String fisier) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fisier));
            String linie;
            StringBuilder continut = new StringBuilder();

            // Citirea fiecărei linii din fișier
            while ((linie = reader.readLine()) != null) {
                continut.append(linie).append("\n");
            }
            reader.close();

            // Împărțirea județelor pe baza separatorului de linii
            return continut.toString().split("\n");
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
        return null;
    }
}
