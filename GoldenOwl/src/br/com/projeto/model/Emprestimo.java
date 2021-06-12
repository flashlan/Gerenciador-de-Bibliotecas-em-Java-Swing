/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.sql.Timestamp;

import java.sql.Date;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class Emprestimo {
    private int id;
    private Timestamp data_emprestimo;
    private Timestamp data_devolucao;
    private String observacoes;
    private Funcionario tb_funcionarios_id;
    private Livro tb_livros_id;
    private Usuario tb_leitores_id;
    private Timestamp data_entrega_agendada;
    private int atraso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Timestamp data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Timestamp getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Timestamp data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

   

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Funcionario getTb_funcionarios_id() {
        return tb_funcionarios_id;
    }

    public void setTb_funcionarios_id(Funcionario tb_funcionarios_id) {
        this.tb_funcionarios_id = tb_funcionarios_id;
    }

    public Livro getTb_livros_id() {
        return tb_livros_id;
    }

    public void setTb_livros_id(Livro tb_livros_id) {
        this.tb_livros_id = tb_livros_id;
    }

    public Usuario getTb_leitores_id() {
        return tb_leitores_id;
    }

    public void setTb_leitores_id(Usuario tb_leitores_id) {
        this.tb_leitores_id = tb_leitores_id;
    }

    public Timestamp getData_entrega_agendada() {
        return data_entrega_agendada;
    }

    public void setData_entrega_agendada(Timestamp data_entrega_agendada) {
        this.data_entrega_agendada = data_entrega_agendada;
    }

    public int getAtraso() {
        return atraso;
    }

    public void setAtraso(int atraso) {
        this.atraso = atraso;
    }

   
    
    
    
}
