package Lab3;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDate;

public class Ex2 {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirarii;
    private static double incasari = 0.0;

    public Ex2(String denumire, double pret, int cantitate, LocalDate dataExpirarii) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirarii = dataExpirarii;
    }

    public String getDenumire() {
        return denumire;
    }

    public double getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public LocalDate getDataExpirarii() {
        return dataExpirarii;
    }

    public static double getIncasari() {
        return incasari;
    }

    public static void adaugaIncasari(double valoare) {
        incasari += valoare;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", data expirarii=" + dataExpirarii +
                '}';
    }
}

