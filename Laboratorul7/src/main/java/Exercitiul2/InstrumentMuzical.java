package Exercitiul2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
abstract class InstrumentMuzical {
    private String producator;
    private Integer pret;

    public InstrumentMuzical() {}

    public InstrumentMuzical(String producator, Integer pret) {
        this.producator = producator;
        this.pret = pret;
    }

    public String getProducator() {
        return producator;
    }

    public Integer getPret() {
        return pret;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstrumentMuzical that = (InstrumentMuzical) o;
        return Objects.equals(producator, that.producator) && Objects.equals(pret, that.pret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producator, pret);
    }

    @Override
    public String toString() {
        return"producator=" + producator + ", pret=" + pret;
    }
}

