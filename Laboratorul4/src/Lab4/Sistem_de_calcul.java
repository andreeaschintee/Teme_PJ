package Lab4;

import java.io.Serializable;
enum OS{
    WINDOWS,
    LINUX
}
public class Sistem_de_calcul extends Echipament implements Serializable {
    private String tip_mon;
    private float vit_proc;
    private int c_hdd;
    private OS os;
    public Sistem_de_calcul(String denumire, int nr, float pret, Zona_mag zona_mag, Situatii situatie, Tipuri tip, String tip_mon, float vit_proc, int c_hdd, OS os)
    {
        super(denumire, nr, pret, zona_mag, situatie, tip);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.os = os;
    }
    @Override
    public String toString() {
        return super.toString()+", Tip monitor: "+tip_mon+", Vit_proc: "+vit_proc+", Capacitate_hdd: "+c_hdd+", OS: "+os;
    }
    public void setOs(OS os) {
        this.os = os;
    }
}

