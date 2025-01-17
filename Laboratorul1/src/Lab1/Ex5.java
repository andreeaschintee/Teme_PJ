package Lab1;

import java.util.Random;

public class Ex5 {

    public static boolean isPerfectSquare(int num) {
        int s = (int) Math.sqrt(num);
        return s * s == num;
    }

    public static boolean isFibonacci(int n) {
        return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(20);

        System.out.println("Random number: " + n);


        if (isFibonacci(n)) {
            System.out.println(n + " is a Fibonacci number.");
        } else {
            System.out.println(n + " is NOT a Fibonacci number.");
        }
    }
}