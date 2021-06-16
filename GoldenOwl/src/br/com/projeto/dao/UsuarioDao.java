/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Usuario;
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
public class UsuarioDao {

    private Connection conexao;

    //construtor
    public UsuarioDao() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    //metodo cadastrar usuario
    public void cadastrarUsuario(Usuario obj) {
        try {
            //criar instrução SQL
            String sql = "insert into tb_leitores (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado, curso, curso_ano, qtd_emprestimos, emprestmax, observacoes, tipo, is_locked )"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try ( //prepare o sql
                    java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());
                stmt.setString(5, obj.getTelefone());
                stmt.setString(6, obj.getCelular());
                stmt.setString(7, obj.getCep());
                stmt.setString(8, obj.getEndereco());
                stmt.setString(9, obj.getNumero());
                stmt.setString(10, obj.getComplemento());
                stmt.setString(11, obj.getBairro());
                stmt.setString(12, obj.getCidade());
                stmt.setString(13, obj.getUf());
                stmt.setString(14, obj.getCurso());
                stmt.setString(15, obj.getSerie());
                stmt.setInt(16, obj.getQtd_emprestimos());//qtd_emprestimos
                stmt.setInt(17, obj.getEmprestmax());
                stmt.setString(18, obj.getObservacoes());
                stmt.setString(19, obj.getTipo());
                stmt.setBoolean(20, obj.isIs_locked());

                //execute
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
            // https://www.guj.com.br/t/exemplo-de-preparedstatement/33609/5

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    //método editar
    public void alterarUsuario(Usuario obj) {
        try {
            // 1 - instrucoes sql
            String sql = "update tb_leitores set nome = ?, rg = ?, cpf = ?, email = ?, telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade  = ?, estado = ?, curso = ?, curso_ano = ?, emprestmax=?, observacoes=?, tipo = ?  where id = ? ";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setString(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            stmt.setString(14, obj.getCurso());
            stmt.setString(15, obj.getSerie());
            stmt.setInt(16, obj.getEmprestmax());
            stmt.setString(17, obj.getObservacoes());
            stmt.setString(18, obj.getTipo());

            stmt.setInt(19, obj.getId());

            // 3 - executar
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }
    }

    //método excluir
    // TODO: implementar caixa de confirmação
    public void excluirUsuario(Usuario obj) {
        try {
            String sql = "delete from tb_leitores where id = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }

    //buscar usuarios com botao
    public Usuario buscarUsuario(String nome) {
        try {
            String sql = "select * from tb_leitores where nome = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Usuario obj = new Usuario();
            while (rs.next()) {
                //obj.setId(rs.getInt("id"));
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
                obj.setCurso(rs.getString("curso"));
                obj.setSerie(rs.getString("curso_ano"));
                obj.setEmprestmax(rs.getInt("emprestmax"));
                obj.setObservacoes(rs.getString("observacoes"));
                obj.setTipo(rs.getString("tipo"));
            }
            return obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    //filtrar usuarios
    public List<Usuario> pesquisarNome(String nome) {
        try {
            List<Usuario> lista = new ArrayList<>();
            String sql = "select * from tb_leitores where nome like ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
                obj.setCurso(rs.getString("curso"));
                obj.setSerie(rs.getString("curso_ano"));
                obj.setEmprestmax(rs.getInt("emprestmax"));
                obj.setObservacoes(rs.getString("observacoes"));
                obj.setTipo(rs.getString("tipo"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    // tabela listando usuarios
    public List<Usuario> listarUsuarios() {
        try {
            //criar uma lista para armazenar 
            List<Usuario> lista = new ArrayList<>();

            //instrucao sql
            String sql = "select * from tb_leitores";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);

            // resultSet representa um conjunto de dados do BD
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
                obj.setCurso(rs.getString("curso"));
                obj.setSerie(rs.getString("curso_ano"));
                obj.setEmprestmax(rs.getInt("emprestmax"));
                obj.setObservacoes(rs.getString("observacoes"));
                obj.setTipo(rs.getString("tipo"));

                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;
    }

    public int pegaUserIdpeloNome(String nome) throws SQLException {
        int id = 0;
        String sql = "select * from tb_leitores where nome = ?";
        java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) { 
            id = rs.getInt("id");
        }
        stmt.close();
        return id;
    }

}
