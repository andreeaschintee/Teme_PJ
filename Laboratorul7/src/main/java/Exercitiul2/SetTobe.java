package Exercitiul2;

public class SetTobe extends InstrumentMuzical{
    private TipTobe tip_tobe;
    private Integer nr_tobe;
    private Integer nr_cinele;

    public SetTobe() {}

    public SetTobe(String producator, Integer pret, TipTobe tip_tobe, Integer nr_tobe, Integer nr_cinele) {
        super(producator, pret);
        this.tip_tobe = tip_tobe;
        this.nr_tobe = nr_tobe;
        this.nr_cinele = nr_cinele;
    }

    public TipTobe getTip_tobe() {
        return tip_tobe;
    }

    public Integer getNr_tobe() {
        return nr_tobe;
    }

    public Integer getNr_cinele() {
        return nr_cinele;
    }

    public void setTip_tobe(TipTobe tip_tobe) {
        this.tip_tobe = tip_tobe;
    }

    public void setNr_tobe(Integer nr_tobe) {
        this.nr_tobe = nr_tobe;
    }

    public void setNr_cinele(Integer nr_cinele) {
        this.nr_cinele = nr_cinele;
    }

    @Override
    public String toString() {
        return super.toString()+", Tip tobe=" + tip_tobe +", nr_tobe=" + nr_tobe +", nr_cinele=" + nr_cinele;
    }
}
