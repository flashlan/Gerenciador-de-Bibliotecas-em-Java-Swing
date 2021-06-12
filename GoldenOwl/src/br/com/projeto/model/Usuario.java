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
public class Usuario extends GlobalUser{
    private String cpf;
    private String rg;
    private String curso;
    private String serie;
    private int emprestmax;
    private String observacoes;
    private String tipo;
    private int qtd_emprestimos;
    private boolean is_locked;

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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getEmprestmax() {
        return emprestmax;
    }

    public void setEmprestmax(int emprestmax) {
        this.emprestmax = emprestmax;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtd_emprestimos() {
        return qtd_emprestimos;
    }

    public void setQtd_emprestimos(int qtd_emprestimos) {
        this.qtd_emprestimos = qtd_emprestimos;
    }

    public boolean isIs_locked() {
        return is_locked;
    }

    public void setIs_locked(boolean is_locked) {
        this.is_locked = is_locked;
    }

    
    
  
    
 
    
    
}
