package br.com.autocontrolbrasil.autocontrolbrasil.model.vo;

public class VeiculoVO {

    private Integer id;

    private String nome;

    private Integer ano_modelo;

    private String placa;

    private byte[] foto;

    private Integer troca_oleo_filtro;

    private Integer troca_pneu_freio;

    private Integer revisao_geral;

    public Integer getTroca_oleo_filtro() {
        return troca_oleo_filtro;
    }

    public void setTroca_oleo_filtro(Integer troca_oleo_filtro) {
        this.troca_oleo_filtro = troca_oleo_filtro;
    }

    public Integer getTroca_pneu_freio() {
        return troca_pneu_freio;
    }

    public void setTroca_pneu_freio(Integer troca_pneu_freio) {
        this.troca_pneu_freio = troca_pneu_freio;
    }

    public Integer getRevisao_geral() {
        return revisao_geral;
    }

    public void setRevisao_geral(Integer revisao_geral) {
        this.revisao_geral = revisao_geral;
    }

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
}
