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
public class Funcionario extends GlobalUser{
    private String cpf;
    private String rg;
    private String cargo;
    private String nivel_acesso;
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(String nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
