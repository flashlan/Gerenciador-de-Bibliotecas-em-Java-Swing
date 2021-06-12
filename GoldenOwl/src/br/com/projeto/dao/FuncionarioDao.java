/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Funcionario;
import br.com.projeto.view.FormMenu;
import br.com.projeto.view.FormLogin;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class FuncionarioDao {
    private Connection conexao;

    //conexao
    public FuncionarioDao(Connection conexao) {
        this.conexao = new ConexaoBanco().pegarConexao();   
    }

    //construtor
    public FuncionarioDao() {
        this.conexao = new ConexaoBanco().pegarConexao(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
        //metodo cadastrar Funcionario
    public void cadastrarFuncionario(Funcionario obj){
        try {
            String sql = "insert into tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado )"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso");

            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"erro!" + erro);
        }
    }
            //método editar
    public void alterarFuncionario(Funcionario obj) {
        try {
            // 1 - instrucoes sql
            String sql = "update tb_funcionarios set nome = ?, rg = ?, cpf = ?, email = ?, senha = ?, cargo = ?, nivel_acesso = ?,telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade  = ?, estado = ? where id = ? ";
            java.sql.PreparedStatement stmt =  conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            stmt.setInt(17, obj.getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }
    }
    
    // TODO: implementar caixa de confirmação
    public void excluirFuncionario(Funcionario obj) {
        try {
            String sql = "delete from tb_funcionarios where id = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
               stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }
    
    // TODO: função para unir botoes salvar e atualizar no mesmo botao, já detectando
    public void checkIdFuncionarioExist(Funcionario obj) {
        try {
            String sql = "SELECT 1 FROM tb_funcionarios WHERE id = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    // por funcao "editar" aqui
                    JOptionPane.showMessageDialog(null, "Id já existe" + rs);
                    } else {
                    // por funcao  "novo" e "salvar" aqui
                    // implemetar um função para campos obrigatorios
                    JOptionPane.showMessageDialog(null, "Id não existe" + rs);
                    }
                }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro! " + e);
                    }
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
            }
        }

     //buscar Funcionarios com botao
    public Funcionario buscarFuncionario(String nome) {
        try {
            String sql = "select * from tb_funcionarios where nome = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionario obj = new Funcionario();
            
            while(rs.next()){
                //obj.setId(rs.getInt("id"));
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }
            return obj;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
        
        
    }
        //filtrar usuarios
    public List<Funcionario>pesquisarNomeFuncionarios(String nome) {
        try {
            List<Funcionario> lista = new ArrayList<>();
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            Funcionario obj = new Funcionario();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setRg(rs.getString("rg"));
            obj.setCpf(rs.getString("cpf"));
            obj.setEmail(rs.getString("email"));
            obj.setSenha(rs.getString("senha"));
            obj.setCargo(rs.getString("cargo"));
            obj.setNivel_acesso(rs.getString("nivel_acesso"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setCep(rs.getString("cep"));
            obj.setEndereco(rs.getString("endereco"));
            obj.setNumero(rs.getString("numero"));
            obj.setComplemento(rs.getString("complemento"));
            obj.setBairro(rs.getString("bairro"));
            obj.setCidade(rs.getString("cidade"));
            obj.setUf(rs.getString("estado"));

            lista.add(obj);  
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro! " +  e);
        }
        return null;
    }
    
    // tabela listando usuarios
    public List<Funcionario> listarFuncionarios() {
        try {
            //criar uma lista para armazenar 
            List<Funcionario> lista = new ArrayList<>();
            
            //instrucao sql
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            
            // resultSet representa um conjunto de dados do BD
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    public void efetuarLogin(String email, String senha) throws IOException {
        try {
            String sql = "select * from tb_funcionarios where email=? and senha=?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
             
            if(rs.next()) {
                if(rs.getString("nivel_acesso").equals("Administrador")) {
                    
                    FormMenu menu = new FormMenu();
                    menu.usuarioLogado = rs.getString("nome");
                    menu.idLogado = rs.getInt("id");
            
                    menu.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Seja Bem Vindo ao Sistema, " + menu.usuarioLogado + "!");
                } else if (rs.getString("nivel_acesso").equals("Atendente")) {
                    FormMenu menu = new FormMenu();
                    menu.usuarioLogado = rs.getString("nome");
                    menu.idLogado = rs.getInt("id");
                    menu.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Seja Bem Vindo ao Sistema, " + menu.usuarioLogado + "!");
                } else if(rs.getString("nivel_acesso").equals("Usuario")) {
                    FormMenu menu = new FormMenu();
                    menu.usuarioLogado = rs.getString("nome");
                    menu.idLogado = rs.getInt("id");
                    menu.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Seja Bem Vindo ao Sistema, " + menu.usuarioLogado + "!");
                }
                
                
            }else {
                   
                    FormLogin tlogin = new FormLogin();
                    tlogin.pack();
                    tlogin.setLocationRelativeTo(null);
                    JOptionPane.showMessageDialog(null, "Dados Inválidos! Tente Novamente!");
                    tlogin.setVisible(true);
                    
                    
                   }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
