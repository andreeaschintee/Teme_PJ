
package Lab1;

import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dati un numar: ");
        int count_dividers = 0;
        int n = scanner.nextInt();
        System.out.println("Ati introdus numarul: " + n);
        System.out.println(("Divizorii numarului sunt: "));
        //pentru divizori
        for (int i = 1; i <= n; i++)
        {
            if (n % i == 0)
            {
                System.out.println(i);
                count_dividers++;
            }
        }
        //sa vad daca e prim
        if(count_dividers == 2)
        {
            System.out.println("Numarul introdus este prim.");
        }
    }
}


