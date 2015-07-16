package br.com.autocontrolbrasil.autocontrolbrasil.model.vo;

public class VeiculoVO {

    private Integer id;

    private String nome;

    private Integer ano_modelo;

    private String placa;

    private byte[] foto;

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
