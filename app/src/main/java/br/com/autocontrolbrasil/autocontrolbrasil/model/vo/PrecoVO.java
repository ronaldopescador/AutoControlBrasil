package br.com.autocontrolbrasil.autocontrolbrasil.model.vo;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Ronaldo on 23/07/2015.
 */
public class PrecoVO implements Serializable {

    private Integer id;
    private String nome;
    private String nomeSlug;
    private String endereco;
    private Double lat;
    private Double lng;
    private String bandeira;
    private Double Gasolina;
    private Double Alcool;
    private Double Diesel;
    private String dataGasolina;
    private Long idGasolina;
    private String dataAlcool;
    private Long idAlcool;
    private String dataDiesel;
    private Long idDiesel;

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

    public String getNomeSlug() {
        return nomeSlug;
    }

    public void setNomeSlug(String nomeSlug) {
        this.nomeSlug = nomeSlug;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public Double getGasolina() {
        return Gasolina;
    }

    public void setGasolina(Double gasolina) {
        Gasolina = gasolina;
    }

    public Double getAlcool() {
        return Alcool;
    }

    public void setAlcool(Double alcool) {
        Alcool = alcool;
    }

    public Double getDiesel() {
        return Diesel;
    }

    public void setDiesel(Double diesel) {
        Diesel = diesel;
    }

    public String getDataGasolina() {
        return dataGasolina;
    }

    public void setDataGasolina(String dataGasolina) {
        this.dataGasolina = dataGasolina;
    }

    public Long getIdGasolina() {
        return idGasolina;
    }

    public void setIdGasolina(Long idGasolina) {
        this.idGasolina = idGasolina;
    }

    public String getDataAlcool() {
        return dataAlcool;
    }

    public void setDataAlcool(String dataAlcool) {
        this.dataAlcool = dataAlcool;
    }

    public Long getIdAlcool() {
        return idAlcool;
    }

    public void setIdAlcool(Long idAlcool) {
        this.idAlcool = idAlcool;
    }

    public String getDataDiesel() {
        return dataDiesel;
    }

    public void setDataDiesel(String dataDiesel) {
        this.dataDiesel = dataDiesel;
    }

    public Long getIdDiesel() {
        return idDiesel;
    }

    public void setIdDiesel(Long idDiesel) {
        this.idDiesel = idDiesel;
    }
}
