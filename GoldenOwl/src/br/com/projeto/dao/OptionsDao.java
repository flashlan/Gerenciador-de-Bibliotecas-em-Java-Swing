/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Options;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class OptionsDao {

    private Connection conexao;

    //conexao
    public OptionsDao(Connection conexao) throws SQLException {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    //construtor
    public OptionsDao()  {
        this.conexao = new ConexaoBanco().pegarConexao(); //To change body of generated methods, choose Tools | Templates.
    }

    //Tabela está usando Adjacency List Model aqui em option    
    //função retorna qualquer campo unico na tabela  de options de acordo com o id na tabela
    public String retornaOption(int data) throws SQLException {
        String value = null;
        String sql = "select data from tb_opcoes where id = " + data; // substituir por ? e stmt.setInt(1,data dá erro, ver o pq
        try {
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);//createStatment nao suporta placeholders
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                value = rs.getString("data");
            }
            System.out.println("data = " + data + "e value = " + value);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    //metodo cadastrar 
    public void cadastrarPiso(Options obj) {
        try {
            String sql = "insert into tb_opcoes (data, parentid)"
                    + "values(?,1)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getPiso());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Piso cadastrado com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    //método Listar
    public List<Options> listarPiso() {
        try {
            List<Options> lista = new ArrayList<>();
            String sql = "select * from tb_opcoes where parentid = 1";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Options obj = new Options();
                obj.setPiso(rs.getString("data"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    //metodo excluir
    public void excluirPiso(Options obj) { //esta pegando a id da box nao do banco
        try {
            String sql = "delete from tb_opcoes where data = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, obj.getPiso());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Piso excluido com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }
    //############################################################################

    //metodo cadastrar 
    public void cadastrarCorredor(Options obj) {
        try {
            String sql = "insert into tb_opcoes (data, parentid)"
                    + "values(?,2)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getCorredor());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Corredor cadastrado com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    //método Listar
    public List<Options> listarCorredor() {
        try {
            List<Options> lista = new ArrayList<>();
            String sql = "select * from tb_opcoes where parentid = 2";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Options obj = new Options();
                obj.setCorredor(rs.getString("data"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    //metodo excluir
    public void excluirCorredor(Options obj) {
        try {
            String sql = "delete from tb_opcoes where data = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, obj.getCorredor());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Corredor excluido com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }
    // #########################################################################

    //metodo cadastrar 
    public void cadastrarPosicao(Options obj) {
        try {
            String sql = "insert into tb_opcoes (data, parentid)"
                    + "values(?,3)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getPosicao());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Posição cadastrado com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    //método Listar
    public List<Options> listarPosicao() {
        try {
            List<Options> lista = new ArrayList<>();
            String sql = "select * from tb_opcoes where parentid = 3";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Options obj = new Options();
                obj.setPosicao(rs.getString("data"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    //metodo excluir
    public void excluirPosicao(Options obj) {
        try {
            String sql = "delete from tb_opcoes where data = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, obj.getPosicao());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Posicao excluida com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }
    //############################################################################

    //metodo cadastrar 
    public void cadastrarSecao(Options obj) {
        try {
            String sql = "insert into tb_opcoes (data, parentid)"
                    + "values(?,4)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getSecao());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Seção cadastrada com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    //método Listar
    public List<Options> listarSecao() {
        try {
            List<Options> lista = new ArrayList<>();
            String sql = "select * from tb_opcoes where parentid = 4";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Options obj = new Options();
                obj.setSecao(rs.getString("data"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    //metodo excluir
    public void excluirSecao(Options obj) {
        try {
            String sql = "delete from tb_opcoes where data = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, obj.getSecao());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Seçào excluida com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }
    //############################################################################

    public void cadastrarDisponibilidade(Options obj) {
        try {
            String sql = "insert into tb_opcoes (data, parentid)"
                    + "values(?,5)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getDisponibilidade());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Disponibilidade de Livro cadastrada com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    //método Listar
    public List<Options> listarDisponibilidade() {
        try {
            List<Options> lista = new ArrayList<>();
            String sql = "select * from tb_opcoes where parentid = 5";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Options obj = new Options();
                obj.setDisponibilidade(rs.getString("data"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    //metodo excluir
    public void excluirDisponibilidade(Options obj) {
        try {
            String sql = "delete from tb_opcoes where data = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, obj.getDisponibilidade());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Disponibilidade excluida com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }
    //###############################################################################

    //Tipos_de_usuarios
    public void cadastrarTipos_de_usuarios(Options obj) {
        try {
            String sql = "insert into tb_opcoes (data, parentid)"
                    + "values(?,9)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getTipos_de_usuarios());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Tipo de Usuário de Livro cadastrado com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    //método Listar
    public List<Options> listarTipos_de_usuarios() {
        try {
            List<Options> lista = new ArrayList<>();
            String sql = "select * from tb_opcoes where parentid = 9";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Options obj = new Options();
                obj.setTipos_de_usuarios(rs.getString("data"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    //metodo excluir
    public void excluirTipos_de_usuarios(Options obj) {
        try {
            String sql = "delete from tb_opcoes where data = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, obj.getTipos_de_usuarios());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Tipos de Usuário excluida com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }

    //################################################################
    public void setIp(Options obj) {
        String sql = "update tb_opcoes set data = ? , parentid = 12 where id = 23";
        java.sql.PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getServer_ip());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro!" + ex);
        }
    }

    public void setLibraryName(Options obj) {
        String sql = "update tb_opcoes set data = ? , parentid = 11 where id = 15";
        java.sql.PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getLibrary_name());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro!" + ex);
        }
    }

    public void setLibraryAddress(Options obj) {
        String sql = "update tb_opcoes set data = ? , parentid = 12 where id = 16";
        java.sql.PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getLibrary_address());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro!" + ex);
        }
    }
;
}
