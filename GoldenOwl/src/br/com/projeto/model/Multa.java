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
public class Multa {
    
    private int id;
    private int dias_atraso;
    private boolean esta_pago;
    private int tb_leitores_id;
    private int tb_emprestimos_id;
    private double valor_multa;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDias_atraso() {
        return dias_atraso;
    }

    public void setDias_atraso(int dias_atraso) {
        this.dias_atraso = dias_atraso;
    }

    public boolean isEsta_pago() {
        return esta_pago;
    }

    public void setEsta_pago(boolean esta_pago) {
        this.esta_pago = esta_pago;
    }

    public int getTb_leitores_id() {
        return tb_leitores_id;
    }

    public void setTb_leitores_id(int tb_leitores_id) {
        this.tb_leitores_id = tb_leitores_id;
    }

    public int getTb_emprestimos_id() {
        return tb_emprestimos_id;
    }

    public void setTb_emprestimos_id(int tb_emprestimos_id) {
        this.tb_emprestimos_id = tb_emprestimos_id;
    }

    public double getValor_multa() {
        return valor_multa;
    }

    public void setValor_multa(double valor_multa) {
        this.valor_multa = valor_multa;
    }
    
    
    
}
