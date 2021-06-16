/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class Options {
    private int id;
    private String piso;
    private String corredor;
    private String posicao;
    private String secao;
    private String parent_id;
    private String Observacoes;
    private String disponibilidade;
    private String tipos_de_usuarios;
    private String server_ip;
    private String library_name;
    private String library_address;

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCorredor() {
        return corredor;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    
//    @Override
//    public String toString() {
//        return this.getPiso();
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String Observacoes) {
        this.Observacoes = Observacoes;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getTipos_de_usuarios() {
        return tipos_de_usuarios;
    }

    public void setTipos_de_usuarios(String tipos_de_usuarios) {
        this.tipos_de_usuarios = tipos_de_usuarios;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getLibrary_name() {
        return library_name;
    }

    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    public String getLibrary_address() {
        return library_address;
    }

    public void setLibrary_address(String library_address) {
        this.library_address = library_address;
    }
    
    @Override public String toString( ){ return this.getPiso(); }
    
}
