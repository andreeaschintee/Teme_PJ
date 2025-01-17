package lab2;
import java.util.Scanner;

public class Exercitiul3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citirea șirului de la tastatură
        System.out.print("Introdu șirul inițial: ");
        String inputString = scanner.nextLine();

        // Crearea unui StringBuilder din șirul inițial
        StringBuilder sb = new StringBuilder(inputString);

        // Citirea șirului care va fi inserat
        System.out.print("Introdu șirul care va fi inserat: ");
        String stringToInsert = scanner.nextLine();

        // Citirea poziției de inserare
        System.out.print("Introdu poziția la care să fie inserat șirul: ");
        int insertPosition = scanner.nextInt();
        scanner.nextLine(); // curățăm bufferul

        // Inserarea șirului
        sb.insert(insertPosition, stringToInsert);
        System.out.println("Șirul după inserare: " + sb.toString());

        // Citirea poziției de la care se va șterge
        System.out.print("Introdu poziția de la care să se șteargă: ");
        int deletePosition = scanner.nextInt();

        // Citirea numărului de caractere care vor fi șterse
        System.out.print("Introdu numărul de caractere care vor fi șterse: ");
        int numCharsToDelete = scanner.nextInt();

        // Ștergerea porțiunii specificate
        sb.delete(deletePosition, deletePosition + numCharsToDelete);
        System.out.println("Șirul după ștergere: " + sb.toString());

        scanner.close();
    }
}
