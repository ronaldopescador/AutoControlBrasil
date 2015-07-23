package br.com.autocontrolbrasil.autocontrolbrasil.model.vo;

public class VeiculoVO {

    private Integer id;

    private String nome;

    private Integer ano_modelo;

    private String placa;

    private byte[] foto;

    private Integer troca_oleo_filtro_previsao;

    private Integer troca_oleo_filtro_anterior;

    private Integer troca_pneu_freio_previsao;

    private Integer troca_pneu_freio_anterior;

    private Integer revisao_geral_previsao;

    private Integer revisao_geral_anterior;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno_modelo() {
        return ano_modelo;
    }

    public void setAno_modelo(Integer ano_modelo) {
        this.ano_modelo = ano_modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getTroca_oleo_filtro_previsao() {
        return troca_oleo_filtro_previsao;
    }

    public void setTroca_oleo_filtro_previsao(Integer troca_oleo_filtro_previsao) {
        this.troca_oleo_filtro_previsao = troca_oleo_filtro_previsao;
    }

    public Integer getTroca_oleo_filtro_anterior() {
        return troca_oleo_filtro_anterior;
    }

    public void setTroca_oleo_filtro_anterior(Integer troca_oleo_filtro_anterior) {
        this.troca_oleo_filtro_anterior = troca_oleo_filtro_anterior;
    }

    public Integer getTroca_pneu_freio_previsao() {
        return troca_pneu_freio_previsao;
    }

    public void setTroca_pneu_freio_previsao(Integer troca_pneu_freio_previsao) {
        this.troca_pneu_freio_previsao = troca_pneu_freio_previsao;
    }

    public Integer getTroca_pneu_freio_anterior() {
        return troca_pneu_freio_anterior;
    }

    public void setTroca_pneu_freio_anterior(Integer troca_pneu_freio_anterior) {
        this.troca_pneu_freio_anterior = troca_pneu_freio_anterior;
    }

    public Integer getRevisao_geral_previsao() {
        return revisao_geral_previsao;
    }

    public void setRevisao_geral_previsao(Integer revisao_geral_previsao) {
        this.revisao_geral_previsao = revisao_geral_previsao;
    }

    public Integer getRevisao_geral_anterior() {
        return revisao_geral_anterior;
    }

    public void setRevisao_geral_anterior(Integer revisao_geral_anterior) {
        this.revisao_geral_anterior = revisao_geral_anterior;
    }
}
