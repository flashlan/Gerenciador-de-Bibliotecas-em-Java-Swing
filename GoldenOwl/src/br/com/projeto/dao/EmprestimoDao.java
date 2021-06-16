/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Emprestimo;
import br.com.projeto.model.Funcionario;
import br.com.projeto.model.Livro;
import br.com.projeto.model.Usuario;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class EmprestimoDao {

    private Connection conexao;

    //conexao
    public EmprestimoDao(Connection conexao) {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    //construtor
    public EmprestimoDao() throws Exception {
        this.conexao = new ConexaoBanco().pegarConexao(); //To change body of generated methods, choose Tools | Templates.
    }

    public void cadastrarEmprestimo(Emprestimo obj) throws SQLException {
        try {
            String sql = "INSERT INTO tb_emprestimos (data_emprestimo, data_entrega_agendada, "
                    + "observacoes, tb_funcionarios_id, tb_livros_id, tb_leitores_id) "
                    + "VALUES (?,?,?,?,?,?)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setTimestamp(1, obj.getData_emprestimo());
            stmt.setTimestamp(2, obj.getData_entrega_agendada()); //calculo de dada aqui
            stmt.setString(3, obj.getObservacoes());
            stmt.setInt(4, obj.getTb_funcionarios_id().getId());
            stmt.setInt(5, obj.getTb_livros_id().getId());
            stmt.setInt(6, obj.getTb_leitores_id().getId());// aqui extende 
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar emprestimo: " + e);
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Emprestimo realizado com sucesso");
    }

    //retorna o cvalor de  quaquer campo passando a id de usuaraio)
    public String getUserData(String table, int id) throws SQLException {
        String value = null;
        String sql = "select " + table + " from tb_leitores where id = " + id; // substituir por ? e stmt.setInt(1,data dá erro, ver o pq
        try {
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);//createStatment nao suporta placeholders
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                value = rs.getString(table);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;

    }

    public void SomaEmprestimo(int value, int id) throws SQLException {
        String sql = "update tb_leitores set qtd_emprestimos = ?  where id = ? ";
        java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, value + 1);
        stmt.setInt(2, id);
        stmt.execute();
        stmt.close();
    }

    public void DiminuiEmprestimo(int value, int id) throws SQLException {
        String sql = "update tb_leitores set qtd_emprestimos = ?  where id = ? ";
        java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, value);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
    }

    //lixeira : excluir
    public void AddEmprestimoToUser(int data) throws SQLException {
        String sql = "select emprestmax, qtd_emprestimos from tb_leitores where id = ?";
        PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
        stmt.setInt(1, data);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario obj = new Usuario();
            obj.setEmprestmax(rs.getInt("emprestmax"));
            obj.setQtd_emprestimos(rs.getInt("qtd_emprestimos"));
            int limite = obj.getEmprestmax();
            int emprestados = obj.getQtd_emprestimos();
            int restantes = limite - emprestados;
            if (restantes > 0) {
                int newEmprestados = emprestados + 1;
                String sql2 = "UPDATE tb_leitores set QTD_EMPRESTIMOS = ? where ID= ?";
                java.sql.PreparedStatement prepstmt = conexao.prepareStatement(sql2);
                prepstmt.setInt(1, newEmprestados);
                prepstmt.setInt(2, data);
                System.out.println("newemprestados = " + newEmprestados);
                prepstmt.executeUpdate();
                prepstmt.close();
            } else {
            }
        }
        rs.close();
        stmt.close();
    }

    public List<Emprestimo> buscarDevolucoes() throws SQLException {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "select  e.id, u.nome, l.titulo, f.nome, e.data_emprestimo, e.data_entrega_agendada, l.disponibilidade, e.observacoes, e.data_devolucao "
                + "from tb_emprestimos as e "
                + "inner join tb_leitores  as u on(e.tb_leitores_id = u.id) "
                + "inner join tb_livros as l on(e.tb_livros_id = l.id) "
                + "inner join tb_funcionarios as f on(e.tb_funcionarios_id = f.id)";
        //alterar 
        PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Emprestimo e = new Emprestimo();
            Usuario u = new Usuario();
            Livro l = new Livro();
            Funcionario f = new Funcionario();
            int idEmprest = rs.getInt("e.id");
            e.setId(idEmprest);//----------------------------------------------------------id
            u.setNome(rs.getString("u.nome"));
            e.setTb_leitores_id(u); //-----------------------------------------------------nome do leitor
            l.setTitulo(rs.getString("l.titulo"));
            e.setTb_livros_id(l);//--------------------------------------------------------titulo livro
            f.setNome(rs.getString("f.nome"));
            e.setTb_funcionarios_id(f);// -------------------------------------------------nome do funcionario
            e.setData_emprestimo(rs.getTimestamp("e.data_emprestimo")); // ----------------data emrpestimo
            e.setData_entrega_agendada(rs.getTimestamp("e.data_entrega_agendada"));// -----data entraga
            e.setAtraso(this.calculaAtraso(idEmprest));//----------------------------------atraso
            e.setObservacoes(rs.getString("e.observacoes"));//-----------------------------observacoes
            e.setData_devolucao(rs.getTimestamp("e.data_devolucao"));//--------------------data devolucao
            lista.add(e);
        }
        return lista;
    }

    public int calculaAtraso(int id) throws SQLException {
        int i = 0;
        String sql = "select data_entrega_agendada, data_devolucao from tb_emprestimos where id = ?";
        try {
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);//createStatment nao suporta placeholders
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { //add daata entrega para fazer 0 se for null (ja entregue)
                Timestamp tmstEnt = rs.getTimestamp("data_devolucao");
                if (tmstEnt == null) {
                    Timestamp tmsp = rs.getTimestamp("data_entrega_agendada");
                    Timestamp now = new Timestamp(System.currentTimeMillis());//tempo agora
                    long _timeGap = now.getTime() - tmsp.getTime();
                    long tempo = _timeGap / 1000 / 60 / 60 / 24;
                    Long l = new Long(tempo);
                    i = l.intValue();
                } else {
                    i = 0;
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public Timestamp addDays(Timestamp date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return new Timestamp(cal.getTime().getTime());
    }

    public String displayData(Timestamp timestamp) {
        String s = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(timestamp);
        return s;
    }

    public double calculaMulta(int dias) {
        double taxa = 0;
        String sql = "select * from tb_opcoes where  parentid = ?";
        try {
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);//createStatment nao suporta placeholders
            stmt.setInt(1, 25);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                taxa = rs.getInt("data");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("taxa: " + taxa);
        return (taxa / 100) * dias;
    }

    public void reemsprestaLivro(String disponibilidade, int iddoemprestimo) throws SQLException {
        String sql = "update tb_emprestimos set data_entrega_agendada = ? where id = " + iddoemprestimo;
        java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
        
       //get livro idponibilidade
        
        Timestamp now = new Timestamp(System.currentTimeMillis());//tempo agora
         Timestamp newdata = this.addDays(now, Integer.parseInt(disponibilidade));
        stmt.setString (1, String.valueOf(newdata));
//        long _timeGap = now.getTime() - dataentregaagendada.getTime();
//            long tempo = _timeGap / 1000 / 60 / 60 / 24;
//            Long l = new Long(tempo);
//            i = l.intValue();

        stmt.execute();
        stmt.close();
    }

    public void devolveLivro(int emprestimoId) throws SQLException, IOException {// não passa como objeto pois saida dos campos teve pós-formatação dos dados
        //esta sobreescrevendo nome de funcionario pelo numero precisa gravar tb_funcionarios_iddevol
        //implemet limpa tela
        String sql = "update tb_emprestimos as e "
                + " INNER JOIN tb_leitores AS u ON (e.tb_leitores_id = u.id) "
                // + " INNER JOIN tb_funcionarios AS f ON(e.tb_funcionarios_id = f.id) "
                + " INNER JOIN tb_livros AS l ON(e.tb_livros_id = l.id) "
                + " set e.data_devolucao = ?, e.tb_funcionarios_iddevol = ?,  l.is_emprestado = ? "
                + " where e.id = ? ";
        java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
        Timestamp tmsp = new Timestamp(System.currentTimeMillis());
        String now = String.valueOf(tmsp);
        String contentid;
        contentid = new String(Files.readAllBytes(Paths.get("C:\\GoldenOwl\\LoggedIn")));
        Funcionario fnc = new Funcionario();
        fnc.setId(Integer.parseInt(contentid));
        stmt.setString(1, now);
        stmt.setInt(2, fnc.getId());
        stmt.setInt(3, 0);

        int leitorId = this.getEmprestimoFKeyData("tb_leitores_id", emprestimoId);//pega o id do usor
        int qtdEmprestimos = Integer.parseInt(this.getUserData("qtd_emprestimos", leitorId));//pega qtd emrpestimos
        int valorsubtraido = qtdEmprestimos - 1;
        this.DiminuiEmprestimo(valorsubtraido, leitorId);//coloca o valor subtraido valor no db
        stmt.setInt(4, emprestimoId);
        System.out.println("now =" + now + "/ valorsubtraido=" + valorsubtraido + "/ contentid=" + contentid);
        stmt.execute();
        stmt.close();
        if (contentid != null) {
            JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso");
        }
        //checkbox gravar options
    }

    public String campoStatusColor(Timestamp data_devolucao, int atraso) throws Exception {
        if (atraso < -15) {
            String d = "+ de 15 dias atrasado";
            return d;
        } else if (data_devolucao == null && atraso == 0) {
            String d = "Vence Hoje";
            return d;
        } else if (data_devolucao != null && atraso == 0) {
            String d = "Devolvido";
            return d;
        } else if (atraso > 0 && atraso < 15) {
            String d = String.valueOf(atraso) + " dias de atraso";
            return d;
        } else if (atraso > 15) {
            String d = "+de 15 dias restantes ou ilimitado";
            return d;
        } else {
            int x = Math.abs(atraso);
            String d = String.valueOf(x) + " dias restantes";
            return d;
        }
    }

    public String campoStatus(Timestamp data_devolucao, int atraso) {
        if (atraso > 0) {
            String d = String.valueOf(atraso) + " dias de atraso";
            return d;
        } else if (data_devolucao == null && atraso == 0) {
            String d = "Vence Hoje";
            return d;
        } else if (data_devolucao != null && atraso == 0) {
            String d = "Devolvido";
            return d;
        } else {
            int x = Math.abs(atraso);
            String d = String.valueOf(x) + " dias restantes";
            return d;
        }
    }

    public Timestamp getEmprestData(int id_do_emprestimo) throws SQLException {
        Timestamp timestamp = null;
        String value = "";
        String sql = "select * from tb_emprestimos where id = " + id_do_emprestimo; // substituir por ? e stmt.setInt(1,data dá erro, ver o pq
        try (java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                value = rs.getString("data_devolucao");
                if (value == null) {
                    timestamp = null;
                } else {
                    timestamp = Timestamp.valueOf(value);
                }
            }
        }
        return timestamp;
    }

    public int getEmprestimoFKeyData(String tabelaInt, int id_do_emprestimo) throws SQLException {
        int value = 0;
        String sql = "select * from tb_emprestimos where id = " + id_do_emprestimo; // substituir por ? e stmt.setInt(1,data dá erro, ver o pq
        try (java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                value = Integer.parseInt(rs.getString(tabelaInt));
            }
        }
        return value;
    }

    public int calculaemprestimosrestantes(int userid) throws SQLException {
        int emprestimosrestantes = 0;
        String sql = "select * from tb_leitores where id = " + userid; // substituir por ? e stmt.setInt(1,data dá erro, ver o pq
        try (java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int limitedeemprestimos = Integer.parseInt(rs.getString("emprestmax"));
                int livrosemprestados = Integer.parseInt(rs.getString("qtd_emprestimos"));
                emprestimosrestantes = limitedeemprestimos - livrosemprestados;
            }
        }
        return emprestimosrestantes;
    }

    public Timestamp livroStatusEmprestimo(int idDoLivro) throws SQLException { //pode restoornar varios valores por ex: ja edevolvidos
        Timestamp ts = null;
        String value = null;
        String sql = "select * from tb_emprestimos where tb_livros_id = " + idDoLivro + " and data_devolucao IS NULL"; // substituir por ? e stmt.setInt(1,data dá erro, ver o pq
        System.out.println("iddolivro=" + idDoLivro);
        try (java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value = rs.getString("data_entrega_agendada");
                if (value == null) {
                    ts = null;
                } else {
                    ts = Timestamp.valueOf(value);
                }
            }
        }
        System.out.println("saida de livroStatusEmprestimo:" + ts);
        return ts;
    }

    public String campoStatusLista(int livroid) throws Exception {
        String d = "";
        LivroDao livro = new LivroDao();
        Timestamp dataentregaagendada = this.livroStatusEmprestimo(livroid);
        String emprestimo = livro.getLivroData("disponibilidade", livroid);
        String estaemprestado = livro.getLivroData("is_emprestado", livroid);
        Timestamp now = new Timestamp(System.currentTimeMillis());//tempo agora
        System.out.println("now= " + now.getTime() + "dataentregaagendada.getTime() = " + dataentregaagendada);
        int i;
        String emprestimoFormat = "";
        if (emprestimo.equals("1")) {
            //emprestimo /24
            emprestimoFormat = "1 dia";
        } else {
            //emprestimo /24
            emprestimoFormat = emprestimo + " dias";
        }
        if (dataentregaagendada != null) {
            long _timeGap = now.getTime() - dataentregaagendada.getTime();
            long tempo = _timeGap / 1000 / 60 / 60 / 24;
            Long l = new Long(tempo);
            i = l.intValue();
            System.out.println("i = diferenca de dias = " + i + "/estaemprestado = " + estaemprestado);

            if (estaemprestado.equals("1") && i <= 0) {
                d = "emprestado até " + this.displayData(dataentregaagendada) + ". empréstimo é de " + emprestimoFormat;
            }
            if (estaemprestado.equals("1") && i > 0) {
                d = "emprestimo atrasado " + i + " dias ! empréstimo é de até " + emprestimoFormat;
            }
        } else {
            if (!emprestimo.equals("0")) {
                d = "Disponível para empréstimo de até " + emprestimoFormat;
            }
            if (emprestimo.equals("0")) {
                d = "Volume para leitura nas dependências apenas";
            }

        }

        {
            return d;
        }

    }
}
