package Exercitiul2;

import java.lang.reflect.Array;
public class Placa {
    private String descriere;
    private Integer lungime;
    private Integer latime;
    private Orientare orientare;
    private boolean []canturi;
    private Integer nr_bucati;
    public Placa() {}
    public Placa(String descriere, Integer lungime, Integer latime, Orientare orientare, boolean[] canturi, Integer nr_bucati) {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.canturi = canturi;
        this.nr_bucati = nr_bucati;
    }
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    public void setLungime(Integer lungime) {
        this.lungime = lungime;
    }
    public void setLatime(Integer latime) {
        this.latime = latime;
    }
    public void setOrientare(Orientare orientare) {
        this.orientare = orientare;
    }
    public void setCanturi(boolean[] canturi) {
        this.canturi = canturi;
    }
    public void setNr_bucati(Integer nr_bucati) {
        this.nr_bucati = nr_bucati;
    }
    public String getDescriere() {
        return descriere;
    }
    public Integer getLungime() {
        return lungime;
    }
    public Integer getLatime() {
        return latime;
    }
    public Orientare getOrientare() {
        return orientare;
    }
    public boolean[] getCanturi() {
        return canturi;
    }
    public Integer getNr_bucati() {
        return nr_bucati;
    }
    public String toString() {
        return "Descriere: "+descriere+", "+"Lungime: "+lungime+", "+"Latime: "+latime+", "+"Orientare: "+orientare+", "+"Muchie 1: "+canturi[0]+", "+"Muchie 2: "+canturi[1]+", "+"Muchie 3: "+canturi[2]+", "+"Muchie 4: "+canturi[3]+", "+"Nr. bucati: "+nr_bucati;
    }
}
