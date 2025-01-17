package Lab4;

import java.io.Serializable;
enum Situatii{
    ACHIZITIONAT,
    EXPUS,
    VANDUT
}
enum Tipuri{
    IMPRIMANTA,
    COPIATOR,
    SISTEM_DE_CALCUL
}
enum Zona_mag{
    A,
    B,
    C,
    D
}
public class Echipament implements Serializable {
    private String denumire;
    private int nr;
    private float pret;
    private Zona_mag zona_mag;
    private Situatii situatie;
    private Tipuri tip;
    public Echipament(String denumire, int nr, float pret, Zona_mag zona_mag, Situatii situatie, Tipuri tip) {
        this.denumire = denumire;
        this.nr = nr;
        this.pret = pret;
        this.zona_mag = zona_mag;
        this.situatie = situatie;
        this.tip = tip;
    }
    public String toString() {
        return "Denumire: "+denumire + ", Nr: "+ nr + ", Pret: " + pret + ", Zona_mag: " + zona_mag + ", Situatie: " + situatie;
    }
    public Tipuri getTipuri() {
        return tip;
    }
    public Situatii getSituatie() {
        return situatie;
    }
    public void setSituatie(Situatii situatie) {
        this.situatie = situatie;
    }


}
