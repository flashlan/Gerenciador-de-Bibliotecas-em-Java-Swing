/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedor;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class FornecedorDao {
    private Connection conexao;

    //conexao
    public FornecedorDao(Connection conexao) {
        this.conexao = new ConexaoBanco().pegarConexao();   
    }

    //construtor
    public FornecedorDao() {
        this.conexao = new ConexaoBanco().pegarConexao(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
        //metodo cadastrar Fornecedor
    public void cadastrarFornecedor(Fornecedor obj){
        try {
            //criar instrução SQL
            String sql = "insert into tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado )"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            //prepare o sql  
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            //execute
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso");

            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"erro!" + erro);
        }
    }
    
        //método editar
    public void alterarFornecedor(Fornecedor obj) {
        try {
            // 1 - instrucoes sql
            String sql = "update tb_fornecedores set nome = ?, cnpj = ?, email = ?,telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade  = ?, estado = ? where id = ? ";
            java.sql.PreparedStatement stmt =  conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            stmt.setInt(13, obj.getId());
            
            // 3 - executar
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }
    }
    
    //método excluir
    // TODO: implementar caixa de confirmação
    public void excluirFornecedor(Fornecedor obj) {
        try {
            String sql = "delete from tb_fornecedores where id = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
               stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }
    
    // função para unir botoes salvar e atualizar no mesmo botao, já detectando
    // TODO
    public void checkIdFornecedorExist(Fornecedor obj) {
        try {
            String sql = "SELECT 1 FROM tb_fornecedores WHERE id = ?";
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
    
    
     //buscar Fornecedores com botao
    public Fornecedor buscarFornecedor(String nome) {
        try {
            String sql = "select * from tb_fornecedores where nome = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Fornecedor obj = new Fornecedor();
            
            while(rs.next()){
                //obj.setId(rs.getInt("id"));
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
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
    public List<Fornecedor>pesquisarNomeFornecedores(String nome) {
        try {
            List<Fornecedor> lista = new ArrayList<>();
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            Fornecedor obj = new Fornecedor();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setCnpj(rs.getString("cnpj"));
            obj.setEmail(rs.getString("email"));
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
    public List<Fornecedor> listarFornecedores() {
        try {
            //criar uma lista para armazenar 
            List<Fornecedor> lista = new ArrayList<>();
            
            //instrucao sql
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            
            // resultSet representa um conjunto de dados do BD
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Fornecedor obj = new Fornecedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
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
    

   
}
