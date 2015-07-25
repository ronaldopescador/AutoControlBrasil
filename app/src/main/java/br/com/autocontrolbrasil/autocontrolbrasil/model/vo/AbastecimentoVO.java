package br.com.autocontrolbrasil.autocontrolbrasil.model.vo;

/**
 * Created by Ronaldo on 20/07/2015.
 */
public class AbastecimentoVO {

    private int id;
    private Double kmAnterior;
    private Double kmAtual;
    private Double volume;
    private Double valorTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getKmAnterior() {
        return kmAnterior;
    }

    public void setKmAnterior(Double kmAnterior) {
        this.kmAnterior = kmAnterior;
    }

    public Double getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(Double kmAtual) {
        this.kmAtual = kmAtual;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getKmMedia() {
        if (volume > 0) {
            return (kmAtual - kmAnterior) / volume;
        } else {
            return new Double(0);
        }

    }
}
