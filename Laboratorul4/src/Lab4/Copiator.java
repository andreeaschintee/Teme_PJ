package Lab4;

import Lab4.Echipament;

import java.io.Serializable;

enum Format{
    A3,
    A4
}

public class Copiator extends Echipament implements Serializable {
    private int p_ton;
    private Format format;

    public Copiator(String denumire, int nr, float pret, Zona_mag zona_mag, Situatii situatie, Tipuri tip, int p_ton, Format format)
    {
        super(denumire, nr, pret, zona_mag, situatie, tip);
        this.p_ton = p_ton;
        this.format = format;
    }
    @Override
    public String toString() {
        return super.toString()+", Pagini/toner: "+p_ton+", Format: "+format;
    }
    public void setFormat(Format format) {
        this.format = format;
    }
}

