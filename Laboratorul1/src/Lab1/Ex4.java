package Lab1;

import java.util.Random;
import java.util.Scanner;

public class Ex4 {
    public static void Cmmdc(int n, int m ) {
        while(n != m)
        {
            if( n > m)
            {
                n = n-m;
            }
            else if (m > n)
            {
                m = m - n;
            }
            if (m == n)
            {
                System.out.println("Cel mai mare divizor comun este: " + m);
                break;
            }
        }
    }

    public static void main(String[] args) {
        //cmmdc a doua numere natulra a caror valoare maxima este 30
        Random random = new Random();
        int n1 = random.nextInt(30);//nr random intre 0 si 29
        if(n1 == 0)
        {
            n1 += 1;
        }
        System.out.println(n1);
        int n2 = random.nextInt(30);// asta e pentru al doilea numar
        if(n2 == 0)
        {
            n2 += 1;
        }
        System.out.println(n2);
        Cmmdc(n1,n2);

    }
}
