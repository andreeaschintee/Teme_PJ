package Lab4;

import java.io.Serializable;
enum Mod_tiparire{
    COLOR,
    NEGRU
}
public class Imprimanta extends Echipament implements Serializable {
    private int ppm;
    private String rezolutie;
    private int p_car;
    private Mod_tiparire mod_tip;
    public Imprimanta(String denumire, int nr, float pret, Zona_mag zona_mag, Situatii situatie, Tipuri tip, int ppm, String rezolutie, int p_car, Mod_tiparire mod_tip) {
        super(denumire, nr, pret, zona_mag, situatie, tip);
        this.ppm = ppm;
        this.rezolutie = rezolutie;
        this.p_car = p_car;
        this.mod_tip = mod_tip;
    }
    @Override
    public String toString() {
        return super.toString() + ", Ppm: "+ppm+", Rezolutie: "+rezolutie+", Pagini/cartus: "+p_car+", Mod tiparire: "+mod_tip;
    }
    public void setMod_tip(Mod_tiparire mod_tip) {
        this.mod_tip = mod_tip;
    }
}
