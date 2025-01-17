package Exercitiul2;

public class Chitara extends InstrumentMuzical{
    private TipChitara tip_chitara;
    private Integer nr_corzi;

    public Chitara(){}

    public Chitara(String producator, Integer pret, TipChitara tip_chitara, Integer nr_corzi) {
        super(producator, pret);
        this.tip_chitara = tip_chitara;
        this.nr_corzi = nr_corzi;
    }

    public TipChitara getTip_chitara() {
        return tip_chitara;
    }

    public Integer getNr_corzi() {
        return nr_corzi;
    }

    public void setTip_chitara(TipChitara tip_chitara) {
        this.tip_chitara = tip_chitara;
    }

    public void setNr_corzi(Integer nr_corzi) {
        this.nr_corzi = nr_corzi;
    }

    @Override
    public String toString() {
        return super.toString()+", Tip_chitara=" + tip_chitara +", nr_corzi=" + nr_corzi;
    }
}
