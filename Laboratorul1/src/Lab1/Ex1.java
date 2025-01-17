package Lab1;

import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        int perimetru = 0;
        int arie = 0;
        Scanner scanner = new Scanner(System.in);//creeaza obiectul scanner ca sa citeasca de la tastatura
        System.out.println("Dati lungimea: ");
        int lungime = scanner.nextInt();//pentru introd. lungimii de la tast.
        System.out.println("Dati latimea: ");
        int latime = scanner.nextInt();//pentru latime
        if(lungime != 0 && latime != 0)
        {
            perimetru = (2 * lungime) + (2 * latime);
            arie = lungime * latime;
            System.out.println("Perimetrul dreptunghiului: " + perimetru);
            System.out.println("Aria dreptunghiului: " + arie);
        }
    }
}