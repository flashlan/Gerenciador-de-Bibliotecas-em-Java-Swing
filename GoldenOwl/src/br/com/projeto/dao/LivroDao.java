/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedor;
import br.com.projeto.model.Livro;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.FileNotFoundException;
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
public class LivroDao {

    private Connection conexao;

    //conexao
    public LivroDao(Connection conexao) {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    //construtor
    public LivroDao() throws Exception {
        this.conexao = new ConexaoBanco().pegarConexao(); //To change body of generated methods, choose Tools | Templates.
    }

    //metodo cadastrar Livro
    public void cadastrarLivro(Livro obj) {
        try {
            String sql = "insert into tb_livros (titulo, autor, editora, isbn, ano, serie, "
                    + "edicao, idioma, tb_fornecedores_id, piso, corredor,posicao, secao, disponibilidade, observacoes)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try ( //prepare o sql
                    java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, obj.getTitulo());
                stmt.setString(2, obj.getAutor());
                stmt.setString(3, obj.getEditora());
                stmt.setString(4, obj.getIsbn());
                stmt.setString(5, obj.getAno());
                stmt.setString(6, obj.getSerie());
                stmt.setString(7, obj.getEdicao());
                stmt.setString(8, obj.getIdioma());
                stmt.setInt(9, obj.getFornecedor().getId());
                stmt.setString(10, obj.getPiso());
                stmt.setString(11, obj.getCorredor());
                stmt.setString(12, obj.getPosicao());
                stmt.setString(13, obj.getSecao());
                stmt.setInt(14, obj.getDisponibilidade());
                stmt.setString(15, obj.getObservacoes());

                stmt.execute(); //????????
                stmt.close();
            }
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro em cadastrar livro!" + erro);
        }
    }

    //método editar
    public void alterarLivro(Livro obj) throws FileNotFoundException, IOException {
        try {
            // 1 - instrucoes sql
            String sql = "update tb_livros set titulo = ?, autor = ?, editora = ?, isbn = ?, ano = ?, serie = ?, edicao = ?, idioma = ?, tb_fornecedores_id =?, piso = ?, corredor = ?, posicao = ?, secao = ?, disponibilidade = ?, observacoes = ? where id = ? ";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getTitulo());
            stmt.setString(2, obj.getAutor());
            stmt.setString(3, obj.getEditora());
            stmt.setString(4, obj.getIsbn());
            stmt.setString(5, obj.getAno());
            stmt.setString(6, obj.getSerie());
            stmt.setString(7, obj.getEdicao());
            stmt.setString(8, obj.getIdioma());
            try {
                stmt.setInt(9, obj.getFornecedor().getId());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Selecione um Fornecedor");
                return;
            }

            stmt.setString(10, obj.getPiso());
            stmt.setString(11, obj.getCorredor());
            stmt.setString(12, obj.getPosicao());
            stmt.setString(13, obj.getSecao());
            stmt.setInt(14, obj.getDisponibilidade());
            stmt.setString(15, obj.getObservacoes());

            stmt.setInt(16, obj.getId());

            // 3 - executar
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Livro alterado com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }
    }

    //método excluir
    // TODO: implementar caixa de confirmação
    public void excluirLivro(Livro obj) {
        try {
            String sql = "delete from tb_livros where id = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Livro excluido com sucesso! ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }

    // função para unir botoes salvar e atualizar no mesmo botao, já detectando
    // TODO
    public void checkIdLivroExist(Livro obj) {
        try {
            String sql = "SELECT 1 FROM tb_livros WHERE id = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // por funcao "editar" aqui
                    JOptionPane.showMessageDialog(null, "Id já existe" + rs);
                } else {
                    // por funcao  "novo" e "salvar" aqui
                    // implemetar um função para campos obrigatorios
                    JOptionPane.showMessageDialog(null, "Id não existe" + rs);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro! " + e);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
    }

    //buscar Livros com botao
    public Livro buscarLivro(String titulo) {
        try {
            String sql = "select * from tb_livros where titulo = ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();
            Livro obj = new Livro();

            while (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setTitulo(rs.getString("titulo"));
                obj.setAutor(rs.getString("autor"));
                obj.setEditora(rs.getString("editora"));
                obj.setIsbn(rs.getString("isbn"));
                obj.setAno(rs.getString("ano"));
                obj.setSerie(rs.getString("serie"));
                obj.setEdicao(rs.getString("edicao"));
                obj.setIdioma(rs.getString("idioma"));
                obj.setPiso(rs.getString("piso"));
                obj.setCorredor(rs.getString("corredor"));
                obj.setPosicao(rs.getString("posicao"));
                obj.setSecao(rs.getString("secao"));
                obj.setDisponibilidade(rs.getInt("disponibilidade"));
                obj.setObservacoes(rs.getString("observacoes"));

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }
        return null;

    }

    //filtrar usuarios
    public List<Livro> pesquisarNomeLivros(String titulo) {
        try {
            List<Livro> lista = new ArrayList<>();
            String sql = "select p.id, p.titulo, p.autor, p.editora, p.isbn, p.ano, p.serie,"
                    + " p.edicao, p.idioma, f.nome, p.piso, p.corredor,"
                    + " p.posicao, p.secao, p.disponibilidade, p.observacoes  from tb_livros as p inner join tb_fornecedores as "
                    + "f on(p.tb_fornecedores_id=f.id) where p.titulo like ?";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro obj = new Livro();
                Fornecedor f = new Fornecedor();
                obj.setId(rs.getInt("p.id"));
                obj.setTitulo(rs.getString("p.titulo"));
                obj.setAutor(rs.getString("p.autor"));
                obj.setEditora(rs.getString("p.editora"));
                obj.setIsbn(rs.getString("p.isbn"));
                obj.setAno(rs.getString("p.ano"));
                obj.setSerie(rs.getString("p.serie"));
                obj.setEdicao(rs.getString("p.edicao"));
                obj.setIdioma(rs.getString("p.idioma"));
                f.setNome(rs.getString("nome"));
                obj.setFornecedor(f);
                obj.setPiso(rs.getString("p.piso"));
                obj.setCorredor(rs.getString("p.corredor"));
                obj.setPosicao(rs.getString("p.posicao"));
                obj.setSecao(rs.getString("p.secao"));
                obj.setDisponibilidade(rs.getInt("disponibilidade"));
                obj.setObservacoes(rs.getString("observacoes"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //JOptionPane.showMessageDialog(null,"Erro! " +  e);
        }
        //return null;
    }

    public List<Livro> buscarLivros() {
        try {
            List<Livro> lista = new ArrayList<>();
            String sql = "select p.id, p.titulo, p.autor, p.editora, p.isbn, p.ano, p.serie,"
                    + " p.edicao, p.idioma, f.nome, p.piso, p.corredor,"
                    + " p.posicao, p.secao, p.disponibilidade, p.observacoes, p.is_emprestado  from tb_livros as p inner join tb_fornecedores as "
                    + "f on(p.tb_fornecedores_id=f.id)";
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                Livro obj = new Livro();
                obj.setId(rs.getInt("p.id"));
                obj.setTitulo(rs.getString("p.titulo"));
                obj.setAutor(rs.getString("p.autor"));
                obj.setEditora(rs.getString("p.editora"));
                obj.setIsbn(rs.getString("p.isbn"));
                obj.setAno(rs.getString("p.ano"));
                obj.setSerie(rs.getString("p.serie"));
                obj.setEdicao(rs.getString("p.edicao"));
                obj.setIdioma(rs.getString("p.idioma"));
                f.setNome(rs.getString("f.Nome"));
                obj.setFornecedor(f);
                obj.setPiso(rs.getString("p.piso"));
                obj.setCorredor(rs.getString("p.corredor"));
                obj.setPosicao(rs.getString("p.posicao"));
                obj.setSecao(rs.getString("p.secao"));
                obj.setDisponibilidade(rs.getInt("p.disponibilidade")); //adiconado p
                obj.setObservacoes(rs.getString("p.observacoes"));//adicionado p
                obj.setEmprestado(rs.getBoolean("p.is_emprestado"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //função seta  livro esta emprestado ou nao (boolean)
    public void setIsEmprestado(int data) throws SQLException {
        
        String sql = "UPDATE tb_livros SET is_emprestado = 1 where id =" + data;
        try {
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getLivroData(String table, int id) throws SQLException {
        String value = null;

        String sql = "select " + table + " from tb_livros where id = " + id; // substituir por ? e stmt.setInt(1,data dá erro, ver o pq
        try {
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);//createStatment nao suporta placeholders
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                value = rs.getString(table);
                //System.out.println("value dentro- " + value);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // System.out.println("value fora- " + value);
        return value;
    }
    
    public void addObservacoes(String data, int livroid) throws SQLException {
        
        String sql = "UPDATE tb_livros SET observacoes = '"+ data + "'  where id =" + livroid;
        try {
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getFornecedorIndex(String name) throws SQLException {
        int value = 0;
        String sql = "select * from  tb_fornecedores where nome =  '" + name + "'"; 
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);//createStatment nao suporta placeholders
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                value = Integer.parseInt(rs.getString("id"));
                //System.out.println("value dentro- " + value);
            }
            stmt.close();
            return value;
    }
    public int getLivroIndex(String name) throws SQLException {
        int value = 0;
        String sql = "select * from  tb_Livros where titulo =  '" + name + "'"; 
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);//createStatment nao suporta placeholders
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                value = Integer.parseInt(rs.getString("id"));
                //System.out.println("value dentro- " + value);
            }
            stmt.close();
            return value;
    }
    
}
