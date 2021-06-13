/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Multa;
import br.com.projeto.model.Usuario;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class MultaDao {

    private Connection conexao;

    //conexao
    public MultaDao(Connection conexao) {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    //construtor
    public MultaDao() throws Exception {
        this.conexao = new ConexaoBanco().pegarConexao(); //To change body of generated methods, choose Tools | Templates.
    }

    public void cadastrarMulta(Multa obj) throws SQLException {
        String sql = "insert into tb_multa (dias_atraso, valor_multa, tb_leitores_id , tb_emprestimos_id, esta_pago) "
                + " values(?,?,?,?,?)";
        java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, obj.getDias_atraso());
        stmt.setDouble(2, obj.getValor_multa());
        stmt.setInt(3, obj.getTb_leitores_id());
        stmt.setInt(4, obj.getTb_emprestimos_id());
        stmt.setBoolean(5, false);
        stmt.execute();
    }
    
    public List<Multa> listaMulta(int idDoEmprestimo) throws SQLException {
      
            List<Multa> lista = new ArrayList<>();
            String sql = "select * from tb_multa where tb_emprestimos_id = ?";
            com.mysql.jdbc.PreparedStatement stmt = (com.mysql.jdbc.PreparedStatement) conexao.prepareStatement(sql);
            stmt.setInt(1, idDoEmprestimo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Multa obj = new Multa();
                obj.setId(rs.getInt("idmulta"));
                obj.setDias_atraso(rs.getInt("dias_atraso"));
                obj.setValor_multa(rs.getInt("valor_multa"));
                obj.setEsta_pago(rs.getBoolean("esta_pago"));
                obj.setTb_leitores_id(rs.getInt("tb_leitores_id"));
                obj.setTb_emprestimos_id(rs.getInt("tb_emprestimos_id"));
                

                lista.add(obj);
            }
            return lista;
    }
    
    public boolean seJaExiste(int idDoEmprestimo) throws SQLException {
        boolean Empduplicado = false; //veirifique se o id do emprestimo ja existe em multas
       String sql = "select * from tb_multa where tb_emprestimos_id = " + idDoEmprestimo;
        com.mysql.jdbc.PreparedStatement stmt = (com.mysql.jdbc.PreparedStatement) conexao.prepareStatement(sql);
        String key = String.valueOf(idDoEmprestimo);
        ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String result = rs.getString("tb_emprestimos_id");//comparar pra ver se ja exite  em multa
//                
                if (result == key) {//!result.equals("")) {
                    System.out.println("result"+ result + " ==  key" +  key);
                    Empduplicado = false;
                } else {
                    System.out.println("result"+ result + " !=  key" +  key);
                    Empduplicado = true;
                }
            }return Empduplicado;
        }
        
    public void zeraMulta ( int multaId) throws SQLException {
        String sql = "update tb_multa set valor_multa = ?  where idmulta = ? ";
        java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, 0);// pode mudar funcao a partir daqui para setar valor a ser pago (parcelado)
        stmt.setInt(2, multaId);
        stmt.execute();
        stmt.close();
    }
        
        
        
        
        
  

}
