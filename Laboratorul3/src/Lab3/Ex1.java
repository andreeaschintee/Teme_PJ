package Lab3;
import java.util.*;
import java.io.*;
import java.lang.Math;

class Parabola {
    private int a, b, c;

    // Constructorul
    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Metoda pentru calcularea vârfului parabolei
    public double[] calculareVarf() {
        double x = -b / (2.0 * a);
        double y = (a * x * x) + (b * x) + c;
        return new double[]{x, y};
    }

    // Redefinirea metodei toString
    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }

    // Metoda pentru calcularea coordonatelor mijlocului segmentului între vârfurile a două parabole
    public double[] calculareMijlocVarf(Parabola p) {
        double[] varfCurent = this.calculareVarf();
        double[] varfAlt = p.calculareVarf();
        double xMijloc = (varfCurent[0] + varfAlt[0]) / 2.0;
        double yMijloc = (varfCurent[1] + varfAlt[1]) / 2.0;
        return new double[]{xMijloc, yMijloc};
    }

    // Metoda statică pentru calcularea coordonatelor mijlocului segmentului între două parabole
    public static double[] calculareMijlocStatic(Parabola p1, Parabola p2) {
        double[] varfP1 = p1.calculareVarf();
        double[] varfP2 = p2.calculareVarf();
        double xMijloc = (varfP1[0] + varfP2[0]) / 2.0;
        double yMijloc = (varfP1[1] + varfP2[1]) / 2.0;
        return new double[]{xMijloc, yMijloc};
    }

    // Metoda pentru calcularea lungimii segmentului între vârfurile a două parabole
    public double calculareLungimeVarf(Parabola p) {
        double[] varfCurent = this.calculareVarf();
        double[] varfAlt = p.calculareVarf();
        return Math.hypot(varfCurent[0] - varfAlt[0], varfCurent[1] - varfAlt[1]);
    }

    // Metoda statică pentru calcularea lungimii segmentului între două parabole
    public static double calculareLungimeStatic(Parabola p1, Parabola p2) {
        double[] varfP1 = p1.calculareVarf();
        double[] varfP2 = p2.calculareVarf();
        return Math.hypot(varfP1[0] - varfP2[0], varfP1[1] - varfP2[1]);
    }

    public static void main(String[] args) {
        try {
            List<Parabola> parabole = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            String linie;

            // Citirea din fișier și crearea obiectelor Parabola
            while ((linie = br.readLine()) != null) {
                String[] coeficienti = linie.split(" ");
                int a = Integer.parseInt(coeficienti[0]);
                int b = Integer.parseInt(coeficienti[1]);
                int c = Integer.parseInt(coeficienti[2]);
                parabole.add(new Parabola(a, b, c));
            }

            br.close();

            // Traversarea colecției și afișarea fiecărei parabole și a vârfului acesteia
            for (Parabola p : parabole) {
                System.out.println(p);
                double[] varf = p.calculareVarf();
                System.out.println("Vârful: (" + varf[0] + ", " + varf[1] + ")");
            }

            // Afișarea mijlocului și lungimii segmentului dintre două parabole (de exemplu, primele două din colecție)
            if (parabole.size() >= 2) {
                Parabola p1 = parabole.get(0);
                Parabola p2 = parabole.get(1);

                double[] mijloc = Parabola.calculareMijlocStatic(p1, p2);
                System.out.println("Mijlocul segmentului dintre primele două parabole: (" + mijloc[0] + ", " + mijloc[1] + ")");

                double lungime = Parabola.calculareLungimeStatic(p1, p2);
                System.out.println("Lungimea segmentului dintre primele două parabole: " + lungime);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
