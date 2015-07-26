package br.com.autocontrolbrasil.autocontrolbrasil.model.vo;

/**
 * Created by Ronaldo on 20/07/2015.
 */
public class AbastecimentoVO {

    private int id;
    private Long kmAnterior;
    private Long kmAtual;
    private Double volume;
    private Long data;
    private Double valorTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getKmAnterior() {
        return kmAnterior;
    }

    public void setKmAnterior(Long kmAnterior) {
        this.kmAnterior = kmAnterior;
    }

    public Long getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(Long kmAtual) {
        this.kmAtual = kmAtual;
    }

    public Double getVolume() {
        return volume;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
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
            return 0.0;
        }

    }
}
