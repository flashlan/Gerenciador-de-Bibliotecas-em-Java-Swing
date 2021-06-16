/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//mude para vizualizacao por projeto para adicionar action
package br.com.projeto.view;

import br.com.projeto.dao.EmprestimoDao;
import javax.swing.SwingConstants;
import br.com.projeto.dao.LivroDao;
import br.com.projeto.dao.MultaDao;
import br.com.projeto.dao.UsuarioDao;
import br.com.projeto.model.DateRenderer;
import br.com.projeto.model.Emprestimo;
import br.com.projeto.model.Funcionario;
import br.com.projeto.model.Livro;
import br.com.projeto.model.Multa;
import br.com.projeto.model.Usuario;
import br.com.projeto.model.Utilitarios;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class FormEmprestimos extends javax.swing.JFrame {
    //public String usuarioLogado;

    // fazer certo isso
    Usuario obj = new Usuario();
    Integer Id;
    String Usuario, Titulo, Prazo, Observacao;
    DefaultTableModel caixa;

    //metodo listar usuarios 
    public void listarUsers() {
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> lista = dao.listarUsuarios();
        DefaultTableModel dados = (DefaultTableModel) tabelaUsuario.getModel();
        dados.setNumRows(0);
        for (Usuario c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getRg(),
                c.getCpf(),
                c.getEmail(),
                c.getTelefone(),
                c.getCelular(),
                c.getCep(),
                c.getEndereco(),
                c.getNumero(),
                c.getComplemento(),
                c.getBairro(),
                c.getCidade(),
                c.getUf(),
                c.getCurso(),
                c.getSerie(),
                c.getObservacoes(),
                c.getTipo(),});
        }
    }

    public void listarLivros() throws Exception {
        LivroDao dao = new LivroDao();
        List<Livro> lista = dao.buscarLivros();
        DefaultTableModel dados = (DefaultTableModel) tabelaLivros.getModel();
        dados.setNumRows(0);
        for (Livro c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getTitulo(),
                c.getAutor(),
                c.getEditora(),
                c.getIsbn(),
                c.getAno(),
                c.getSerie(),
                c.getEdicao(),
                c.getIdioma(),
                c.getFornecedor().getNome(),
                c.getPiso(),
                c.getCorredor(),
                c.getPosicao(),
                c.getSecao(),
                c.getDisponibilidade(),
                c.getObservacoes()
            });
        }
    }

    public void listarDevolucoes() throws Exception {
        EmprestimoDao dao = new EmprestimoDao();
        List<Emprestimo> lista = dao.buscarDevolucoes();
        DefaultTableModel dados = (DefaultTableModel) tabelaDevolucoes.getModel();
        dados.setNumRows(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Emprestimo c : lista) {
            dados.addRow(new Object[]{
                dao.campoStatusColor(c.getData_devolucao(), c.getAtraso()),
                c.getId(),
                c.getTb_leitores_id().getNome(), //muda o setTetTb_leitores_id do Emprestimo.java para o tipo Usuario
                c.getTb_livros_id().getTitulo(),//
                c.getTb_funcionarios_id().getNome(),//
                formatter.format(c.getData_emprestimo()),
                formatter.format(c.getData_entrega_agendada()),
                c.getObservacoes(),
                c.getData_devolucao(),});
        }
    }

    public void listarDevolucoesCompact() throws Exception {
        EmprestimoDao dao = new EmprestimoDao();
        List<Emprestimo> lista = dao.buscarDevolucoes();
        DefaultTableModel dados1 = (DefaultTableModel) tabelaDevolucoes.getModel();
        dados1.setNumRows(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Emprestimo c : lista) {
            String atraso = dao.campoStatusColor(c.getData_devolucao(), c.getAtraso());
            if (!atraso.equals("Devolvido")) {

                dados1.addRow(new Object[]{
                    dao.campoStatusColor(c.getData_devolucao(), c.getAtraso()),
                    c.getId(),
                    c.getTb_leitores_id().getNome(), //muda o setTetTb_leitores_id do Emprestimo.java para o tipo Usuario
                    c.getTb_livros_id().getTitulo(),//
                    c.getTb_funcionarios_id().getNome(),//
                    formatter.format(c.getData_emprestimo()),
                    formatter.format(c.getData_entrega_agendada()),
                    c.getObservacoes(),
                    c.getData_devolucao(),});
            } else {
                continue;
            }
        }
    }

//    if (data_devolucao != null && atraso == 0) {
//            String d = "Devolvido";
//            return d;
    // #################################################################
    //add disponibilidade leitor localizacao e observações
    public void listarLivrosFiltro() throws Exception {
        LivroDao dao = new LivroDao();
        List<Livro> lista = dao.buscarLivros();
        DefaultTableModel dados = (DefaultTableModel) tabelaLivrosFiltro.getModel();
        dados.setNumRows(0);
        for (Livro c : lista) {
            dados.addRow(new Object[]{
                c.getDisponibilidade(),
                c.getTitulo(),
                //trocar para ususario que emprestou
                c.getObservacoes(),
                c.getSecao(),
                c.getId(),
                c.getIsbn(),
                c.isEmprestado(),});
        }

    }

    public void listarLivrosFiltroCompact() throws Exception {
        LivroDao dao = new LivroDao();
        List<Livro> lista = dao.buscarLivros();
        DefaultTableModel dados = (DefaultTableModel) tabelaLivrosFiltro.getModel();
        dados.setNumRows(0);
        for (Livro c : lista) {
            if (c.isEmprestado() == false) {
                dados.addRow(new Object[]{
                    c.getDisponibilidade(),
                    c.getTitulo(),
                    c.getObservacoes(),
                    c.getSecao(),
                    c.getId(),
                    c.getIsbn(),
                    c.isEmprestado(),});
            }
        }
    }

    /**
     * Creates new form formLeitor
     */
    public FormEmprestimos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        wdwEmprestimos = new javax.swing.JTabbedPane();
        tabConsultaUsuarios = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtPesquisaUsuario = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnPesquisar1 = new javax.swing.JButton();
        txtPesquisaLivros = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        tabCadastro = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaEmprestimos = new javax.swing.JTable();
        btnBuscaUser = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaLivrosFiltro = new javax.swing.JTable()
        /*
        {
            @Override
            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);
                Object value = getModel().getValueAt(rowIndex,columnIndex);

                System.out.println("value ===" +value);
                if(columnIndex == 6){
                    if(value.equals("true"))
                    {
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.GREEN);
                    }
                    if(value.equals("false")){
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.RED);
                    }
                }else {
                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }
                return componenet;
            }

        }
        */
        ;
        txtBuscaLivro = new javax.swing.JTextField();
        txtAgora = new javax.swing.JTextField();
        txtPrazoEntrega = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTituloSelect = new javax.swing.JTextField();
        brnAddLivro = new javax.swing.JButton();
        txtBookId = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        btnRemoveBook = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        cbOcultarEmprestados = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        lblImagem = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEmprestimosRestantes = new javax.swing.JTextField();
        lblimagemUser = new javax.swing.JLabel();
        btnCartao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        btnDetalhes = new javax.swing.JButton();
        btnBuscarLivrosAdvanced = new javax.swing.JButton();
        txtDisponibilidade = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelaDevolucoes = new javax.swing.JTable()

        {

            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 0){

                    if(value.equals("1 dias restantes") || value.equals("2 dias restantes") || value.equals("3 dias restantes") || value.equals("4 dias restantes") || value.equals("5 dias restantes") || value.equals("6 dias restantes") || value.equals("7 dias restantes") || value.equals("8 dias restantes") || value.equals("9 dias restantes") || value.equals("10 dias restantes") || value.equals("11 dias restantes") || value.equals("12 dias restantes") || value.equals("13 dias restantes") || value.equals("14 dias restantes"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);
                    }

                    if(value.equals("Vence Hoje")){
                        // if date  equal current date
                        componenet.setBackground(Color.YELLOW);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("1 dias de atraso") || value.equals("2 dias de atraso") || value.equals("3 dias de atraso") || value.equals("4 dias de atraso") || value.equals("5 dias de atraso") || value.equals("6 dias de atraso") || value.equals("7 dias de atraso") || value.equals("8 dias de atraso") || value.equals("9 dias de atraso") ){
                        componenet.setBackground(Color.PINK);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("+ de 15 dias de atraso")){
                        // if date  equal current date
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.WHITE);
                    }
                    if(value.equals("Devolvido")){
                        // if date  equal current date
                        componenet.setBackground(Color.WHITE);
                        componenet.setForeground(Color.GRAY);
                    }
                }
                else {
                    //            componenet.setBackground(Color.RED);
                    //            componenet.setForeground(Color.WHITE);
                }
                return componenet;
            }
        }

        ;
        btnDevolver = new javax.swing.JButton();
        btnReemprestar = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        btnImprimeMulta = new javax.swing.JButton();
        btnDesbloqueiaUsuario = new javax.swing.JButton();
        txtIdEmprest = new javax.swing.JTextField();
        txtLeitorresponsavel = new javax.swing.JTextField();
        txtLivroEmprestado = new javax.swing.JTextField();
        txtFuncionarioQueEmprestou = new javax.swing.JTextField();
        txtDataEmprestimo = new javax.swing.JTextField();
        txtDataParaDevolucao = new javax.swing.JTextField();
        txtMulta = new javax.swing.JTextField();
        StatusEmprestimo = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtObservacoesDevolucao = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        radbtnAddObs = new javax.swing.JRadioButton();
        radioLista = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle de Empréstimo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1058, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        wdwEmprestimos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wdwEmprestimosMouseClicked(evt);
            }
        });
        wdwEmprestimos.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                wdwEmprestimosComponentShown(evt);
            }
        });

        jLabel14.setText("Nome:");

        txtPesquisaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaUsuarioActionPerformed(evt);
            }
        });
        txtPesquisaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaUsuarioKeyReleased(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        btnPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnPesquisarKeyReleased(evt);
            }
        });

        tabelaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "nome", "rg", "cpf", "email", "telefone", "celular", "cep", "endereco", "numero", "complemento", "bairro", "cidade", "estado", "curso", "ano", "Observações", "Tipo de Acesso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaUsuario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaUsuario.setAutoscrolls(false);
        tabelaUsuario.getTableHeader().setReorderingAllowed(false);
        tabelaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaUsuario);
        tabelaUsuario.getColumn(tabelaUsuario.getColumnName(0)).setPreferredWidth(40);
        tabelaUsuario.getColumn(tabelaUsuario.getColumnName(1)).setPreferredWidth(200);

        javax.swing.GroupLayout tabConsultaUsuariosLayout = new javax.swing.GroupLayout(tabConsultaUsuarios);
        tabConsultaUsuarios.setLayout(tabConsultaUsuariosLayout);
        tabConsultaUsuariosLayout.setHorizontalGroup(
            tabConsultaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultaUsuariosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(tabConsultaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabConsultaUsuariosLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(tabConsultaUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)
                        .addGap(618, 618, 618))))
        );
        tabConsultaUsuariosLayout.setVerticalGroup(
            tabConsultaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultaUsuariosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(tabConsultaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPesquisaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
        );

        wdwEmprestimos.addTab("Consulta de Usuário", tabConsultaUsuarios);

        btnPesquisar1.setText("Pesquisar");
        btnPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisar1ActionPerformed(evt);
            }
        });
        btnPesquisar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnPesquisar1KeyReleased(evt);
            }
        });

        txtPesquisaLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaLivrosActionPerformed(evt);
            }
        });
        txtPesquisaLivros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaLivrosKeyReleased(evt);
            }
        });

        tabelaLivros.setAutoCreateRowSorter(true);
        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "Título", "Autor", "Editora", "ISBN", "Ano", "Serie", "Edição", "Idioma", "Fornecedor", "Piso", "Corredor", "Posição", "Seção", "Disponibilidade", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLivros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaLivros.getTableHeader().setReorderingAllowed(false);
        tabelaLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaLivrosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaLivros);
        tabelaLivros.getColumn(tabelaLivros.getColumnName(0)).setPreferredWidth(40);
        tabelaLivros.getColumn(tabelaLivros.getColumnName(1)).setPreferredWidth(200);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(txtPesquisaLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisar1)
                .addContainerGap(337, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisaLivros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        wdwEmprestimos.addTab("Consulta de Livro", jPanel2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nome de Usuário:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Filtrar Livro:");

        txtNome.setEditable(false);
        txtNome.setForeground(new java.awt.Color(102, 102, 102));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        tabelaEmprestimos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "Data Empréstimo", "Data para  Devolução", "Observações", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaEmprestimos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(tabelaEmprestimos);
        tabelaEmprestimos.getColumn(tabelaEmprestimos.getColumnName(4)).setPreferredWidth(38);
        tabelaEmprestimos.getColumn(tabelaEmprestimos.getColumnName(0)).setPreferredWidth(130);
        tabelaEmprestimos.getColumn(tabelaEmprestimos.getColumnName(1)).setPreferredWidth(120);
        tabelaEmprestimos.getColumn(tabelaEmprestimos.getColumnName(2)).setPreferredWidth(120);
        tabelaEmprestimos.getColumn(tabelaEmprestimos.getColumnName(3)).setPreferredWidth(150);

        btnBuscaUser.setText("Buscar Usuário");
        btnBuscaUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaUserActionPerformed(evt);
            }
        });

        tabelaLivrosFiltro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Disponibilidade", "Título", "Observações", "Localização", "Cod", "ISBN", "Emprestado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLivrosFiltro.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaLivrosFiltro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaLivrosFiltroMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelaLivrosFiltro);
        tabelaLivrosFiltro.getColumn(tabelaLivrosFiltro.getColumnName(0)).setPreferredWidth(34);
        tabelaLivrosFiltro.getColumn(tabelaLivrosFiltro.getColumnName(1)).setPreferredWidth(170);
        tabelaLivrosFiltro.getColumn(tabelaLivrosFiltro.getColumnName(2)).setPreferredWidth(150);
        tabelaLivrosFiltro.getColumn(tabelaLivrosFiltro.getColumnName(3)).setPreferredWidth(140);

        txtBuscaLivro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaLivroKeyReleased(evt);
            }
        });

        txtAgora.setEditable(false);
        txtAgora.setForeground(new java.awt.Color(102, 102, 102));
        txtAgora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgoraActionPerformed(evt);
            }
        });

        txtPrazoEntrega.setEditable(false);
        txtPrazoEntrega.setForeground(new java.awt.Color(102, 102, 102));
        txtPrazoEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrazoEntregaActionPerformed(evt);
            }
        });

        jLabel4.setText("Data para  Devolução:");

        jLabel6.setText("Data Atual:");

        jLabel7.setText("Livro ID:");

        txtTituloSelect.setEditable(false);
        txtTituloSelect.setForeground(new java.awt.Color(102, 102, 102));
        txtTituloSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloSelectActionPerformed(evt);
            }
        });

        brnAddLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/right_small.png"))); // NOI18N
        brnAddLivro.setText("<html>Adicionar   à Lista");
        brnAddLivro.setToolTipText("");
        brnAddLivro.setActionCommand("<html>Adicionar  à Lista");
        brnAddLivro.setVerticalTextPosition(SwingConstants.BOTTOM);
        brnAddLivro.setHorizontalTextPosition(SwingConstants.CENTER);
        brnAddLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnAddLivroActionPerformed(evt);
            }
        });

        txtBookId.setEditable(false);
        txtBookId.setForeground(new java.awt.Color(102, 102, 102));

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane3.setViewportView(txtObservacoes);

        jLabel8.setText("Observações:");

        btnRemoveBook.setText("Remover Livro da Lista");
        btnRemoveBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveBookActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save_small.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel2.setText("ID de Usuário:");

        txtId.setEditable(false);
        txtId.setForeground(new java.awt.Color(102, 102, 102));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        cbOcultarEmprestados.setText("Ocultar Emprestados");
        cbOcultarEmprestados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOcultarEmprestadosItemStateChanged(evt);
            }
        });

        jButton5.setText("Mapa");

        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/new_file_small.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/book_cover.png"))); // NOI18N
        lblImagem.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.darkGray));

        txtISBN.setEditable(false);
        txtISBN.setForeground(new java.awt.Color(102, 102, 102));
        txtISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtISBNActionPerformed(evt);
            }
        });

        jLabel10.setText("<html>ISBN/<br>ISSN:\n");

        txtStatus.setEditable(false);
        txtStatus.setForeground(new java.awt.Color(102, 102, 102));

        jLabel11.setText("Status:");

        jLabel9.setText("Empréstimos Restantes:");

        txtEmprestimosRestantes.setEditable(false);
        txtEmprestimosRestantes.setForeground(new java.awt.Color(102, 102, 102));

        lblimagemUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimagemUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconfinder_student_309036 (2).png"))); // NOI18N
        lblimagemUser.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblimagemUser.setHorizontalTextPosition(SwingConstants.CENTER);
        lblimagemUser.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.darkGray));

        btnCartao.setText("Imprimir/ Ver Cartão");
        btnCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartaoActionPerformed(evt);
            }
        });

        jButton1.setText("Cadastro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton13.setText("Cadastro");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        btnDetalhes.setText("Detalhes");
        btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalhesActionPerformed(evt);
            }
        });

        btnBuscarLivrosAdvanced.setText("Busca Avançada");
        btnBuscarLivrosAdvanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLivrosAdvancedActionPerformed(evt);
            }
        });

        txtDisponibilidade.setEnabled(false);
        txtDisponibilidade.setOpaque(false);

        jLabel23.setText("Título:");

        javax.swing.GroupLayout tabCadastroLayout = new javax.swing.GroupLayout(tabCadastro);
        tabCadastro.setLayout(tabCadastroLayout);
        tabCadastroLayout.setHorizontalGroup(
            tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCadastroLayout.createSequentialGroup()
                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbOcultarEmprestados))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(332, 332, 332))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(tabCadastroLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel7)
                                        .addGap(29, 29, 29)
                                        .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6))
                                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel4)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPrazoEntrega)
                                            .addComponent(txtAgora, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
                                .addGroup(tabCadastroLayout.createSequentialGroup()
                                    .addComponent(btnDetalhes)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarLivrosAdvanced)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton13)))
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabCadastroLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(txtDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel23))
                                .addGap(15, 15, 15)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                    .addComponent(txtTituloSelect))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brnAddLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblimagemUser, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(tabCadastroLayout.createSequentialGroup()
                                    .addComponent(btnBuscaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmprestimosRestantes, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveBook))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(22, 22, 22))
        );
        tabCadastroLayout.setVerticalGroup(
            tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(415, 415, 415))
            .addGroup(tabCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtEmprestimosRestantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnBuscaUser)
                                    .addComponent(btnCartao)
                                    .addComponent(jButton1)))
                            .addComponent(lblimagemUser, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveBook, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNovo))
                        .addGap(238, 238, 238))
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtBuscaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbOcultarEmprestados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDetalhes)
                                    .addComponent(btnBuscarLivrosAdvanced)
                                    .addComponent(jButton5)
                                    .addComponent(jButton13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTituloSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)))
                            .addComponent(brnAddLivro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(tabCadastroLayout.createSequentialGroup()
                                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txtAgora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtPrazoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))))))
                        .addGap(226, 226, 226)
                        .addComponent(txtDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        wdwEmprestimos.addTab("Cadastro de Empréstimo", tabCadastro);

        tabelaDevolucoes.setDefaultRenderer(Date.class, new DateRenderer());
        tabelaDevolucoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Atraso", "Id do Empréstimo", "Usuário", "Titulo", "Funcionário", "Data do Empréstimo", "Data Agendada de Entrega", "Observações", "Data da  Devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaDevolucoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaDevolucoesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabelaDevolucoes);

        btnDevolver.setText("Devolver");
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        btnReemprestar.setText("reeemprestar");
        btnReemprestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReemprestarActionPerformed(evt);
            }
        });

        jButton9.setText("Localização");

        btnImprimeMulta.setText("Imprime Multa");
        btnImprimeMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimeMultaActionPerformed(evt);
            }
        });

        btnDesbloqueiaUsuario.setText("Desbloqueia Usuário");

        txtIdEmprest.setEditable(false);
        txtIdEmprest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEmprestActionPerformed(evt);
            }
        });

        txtLeitorresponsavel.setEditable(false);

        txtLivroEmprestado.setEditable(false);

        txtFuncionarioQueEmprestou.setEditable(false);
        txtFuncionarioQueEmprestou.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        txtDataEmprestimo.setEditable(false);

        txtDataParaDevolucao.setEditable(false);

        StatusEmprestimo.setEditable(false);

        txtObservacoesDevolucao.setColumns(20);
        txtObservacoesDevolucao.setRows(5);
        jScrollPane7.setViewportView(txtObservacoesDevolucao);

        jLabel12.setText("Id do Empréstimo");

        jLabel13.setText("Leitor Responsável:");

        jLabel15.setText("Título:");

        jLabel16.setText("Funcionário:");

        jLabel17.setText("Data de Empréstimo:");

        jLabel19.setText("Data para Devolução:");

        jLabel20.setText("Status:");

        jLabel21.setText("Multa:");

        jLabel22.setText("Observações:");

        radbtnAddObs.setText("Add Observações como permanente  para o Livro");

        radioLista.setText("Mostrar Devolvidos");
        radioLista.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioListaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel12))
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdEmprest, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLeitorresponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtLivroEmprestado, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(246, 246, 246))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel21))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDataParaDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(StatusEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFuncionarioQueEmprestou, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radbtnAddObs, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnReemprestar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnImprimeMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnDesbloqueiaUsuario)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(231, 231, 231)))
                .addComponent(jButton9))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(833, 833, 833)
                        .addComponent(radioLista))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(radioLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdEmprest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLeitorresponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtLivroEmprestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(radbtnAddObs)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnImprimeMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDesbloqueiaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReemprestar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(52, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFuncionarioQueEmprestou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtDataParaDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20)
                                            .addComponent(StatusEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel21)
                                            .addComponent(txtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {StatusEmprestimo, txtDataEmprestimo, txtDataParaDevolucao, txtMulta});

        wdwEmprestimos.addTab("Devoluções", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(wdwEmprestimos, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wdwEmprestimos, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Utilitarios util = new Utilitarios();
//______________________pega id de usuario . verifica se esta selecionado___________________________________
        try {
            int useridint;//
            try {
                useridint = Integer.parseInt(txtId.getText());//id int
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro! Selecione um Usuário");
                wdwEmprestimos.setSelectedIndex(0);
                return;
            }
//______________________caso user nao tenha emprestimos restantes__________________________________          
            EmprestimoDao emprestimoDao = new EmprestimoDao();
            if ((emprestimoDao.calculaemprestimosrestantes(useridint)) == 0) {
                JOptionPane.showMessageDialog(null, "Limite de empréstimos excedido, precisa devolver Livros antes de emprestar outros!");
                util.limpaTela(tabCadastro);
                while (caixa.getRowCount() > 0) {
                    caixa.removeRow(0);
                }
                return;
            }
//______________________ seleciona primeira linha - pega id do livro na 5a coluna ___________________________________           
            while (tabelaEmprestimos.getRowCount() > 0) {
                try {
                    if (tabelaEmprestimos != null && tabelaEmprestimos.getRowCount() > 0) {
                        tabelaEmprestimos.getSelectionModel().setSelectionInterval(0, 0);
                    }
                    DefaultTableModel modelo = (DefaultTableModel) tabelaEmprestimos.getModel();
                    int row = tabelaEmprestimos.getSelectedRow();
                    Livro livro = new Livro();
                    livro.setId(Integer.parseInt((String) modelo.getValueAt(row, 4)));
                    int livroidint = livro.getId();
//________________________  livro está emprestado? se estiver cancela  operacao_____________________________________                  
                    LivroDao livrodao = new LivroDao();
                    String seLivroEmprestado = livrodao.getLivroData("is_emprestado", livroidint);
                    if (!seLivroEmprestado.equals("0")) {
                        JOptionPane.showMessageDialog(null, "Livro já foi Emprestado, verifique!");
                        caixa.removeRow(0);
                        break;
                    }
//_____________________________________pega disponibilidade para caulcular prazos_ constroi objeto___________________________________
                    Emprestimo emprestimo = new Emprestimo();
                    Timestamp tmsp = new Timestamp(System.currentTimeMillis());//tempo agora
                    Timestamp prazoEntrega = emprestimoDao.addDays(tmsp, Integer.parseInt(livrodao.getLivroData("disponibilidade", livro.getId())));
                    emprestimo.setData_emprestimo(tmsp);//1  //trocar e implemtar horario de fechamento da biblioteca
                    emprestimo.setData_entrega_agendada(prazoEntrega);//2
                    emprestimo.setObservacoes(modelo.getValueAt(row, 3).toString());//3
                    emprestimo.setTb_livros_id(livro);//4

                    Funcionario funcionario = new Funcionario();
                    String contentid = new String(Files.readAllBytes(Paths.get("C:\\GoldenOwl\\LoggedIn")));
                    funcionario.setId(Integer.parseInt(contentid));
                    emprestimo.setTb_funcionarios_id(funcionario);//5 //set funcionario id

                    Usuario usuario = new Usuario();//parse com variaveis tipo usuario
                    usuario.setId(useridint);
                    emprestimo.setTb_leitores_id(usuario);//6 //pega direto
//_____________________________cadastra objeto __________________________
                    EmprestimoDao obj = new EmprestimoDao();
                    obj.cadastrarEmprestimo(emprestimo);// passar variavel default
                    livrodao.setIsEmprestado(livroidint);//7
//________________________ update campo emprestimos restantes e add ao contador do usuario no db ___________________________
                    int livrosemprestados = Integer.parseInt(emprestimoDao.getUserData("qtd_emprestimos", useridint));//8   
                    emprestimoDao.SomaEmprestimo(livrosemprestados, useridint);
                    caixa.removeRow(0);
                } catch (IOException ex) {
                    Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            txtEmprestimosRestantes.setText(String.valueOf(emprestimoDao.calculaemprestimosrestantes(useridint)));
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtPesquisaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaUsuarioActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // listar suario pt4
        listarUsers();
        try {
            listarLivros();
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            listarLivrosFiltro();
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date agora = new Date();
        SimpleDateFormat dataBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataFormatada = dataBR.format(agora);
        txtAgora.setText(String.valueOf(dataFormatada));
        try {
            listarDevolucoesCompact();
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_formWindowActivated

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        Utilitarios util = new Utilitarios();
        util.limpaTela(tabCadastro);
        while (caixa.getRowCount() > 0) {
            caixa.removeRow(0);
        }
        Date agora = new Date();
        SimpleDateFormat dataBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataFormatada = dataBR.format(agora);
        txtAgora.setText(String.valueOf(dataFormatada));
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisarKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_btnPesquisarKeyReleased

    private void txtPesquisaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaUsuarioKeyReleased

        String nome = "%" + txtPesquisaUsuario.getText() + "%";
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> lista = dao.pesquisarNome(nome);
        DefaultTableModel dados = (DefaultTableModel) tabelaUsuario.getModel();
        dados.setNumRows(0); //limpa/zera pesquisa a cada digitacao
        for (Usuario c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getRg(),
                c.getCpf(),
                c.getEmail(),
                c.getTelefone(),
                c.getCelular(),
                c.getCep(),
                c.getEndereco(),
                c.getNumero(),
                c.getComplemento(),
                c.getBairro(),
                c.getCidade(),
                c.getUf(),
                c.getCurso(),
                c.getSerie()
            });
        }
    }//GEN-LAST:event_txtPesquisaUsuarioKeyReleased

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

    }//GEN-LAST:event_btnPesquisarActionPerformed

    //tabela da aba 1
    private void tabelaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaUsuarioMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaUsuario.getModel();
            int selectedRowIndex = tabelaUsuario.getSelectedRow();
            txtId.setText(model.getValueAt(selectedRowIndex, 0).toString());
            txtNome.setText(model.getValueAt(selectedRowIndex, 1).toString());
            wdwEmprestimos.setSelectedIndex(2);
            String path = "C:\\goldenOwl\\images\\usuarios\\" + txtId.getText();
            lblimagemUser.setIcon(ResizeUserImage(path));
            EmprestimoDao user = new EmprestimoDao();//emprestimos restantes
            String preleitor = txtId.getText();
            int leitor = Integer.parseInt(preleitor);
            String emprestadosTxt = user.getUserData("qtd_emprestimos", leitor);
            String emprestmaxtxt = user.getUserData("emprestmax", leitor);
            int emprestmaxInt = Integer.parseInt(emprestmaxtxt);
            int emprestadosInt = Integer.parseInt(emprestadosTxt);
            int limite = emprestmaxInt - emprestadosInt;
            String s = String.valueOf(limite/**/);
            txtEmprestimosRestantes.setText(s);
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaUsuarioMouseClicked

    // tabela da aba 2
    private void tabelaLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaLivrosMouseClicked
        DefaultTableModel model = (DefaultTableModel) tabelaLivros.getModel();
        int selectedRowIndex = tabelaLivros.getSelectedRow();
        txtBookId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txtTituloSelect.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txtISBN.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txtObservacoes.setText(model.getValueAt(selectedRowIndex, 15).toString());
        txtDisponibilidade.setText(model.getValueAt(selectedRowIndex, 14).toString());
        String path = "C:\\goldenOwl\\images\\books\\" + txtISBN.getText();
        lblImagem.setIcon(ResizeBookImage(path));
        Date dataTeste = new Date();
        Calendar cal = Calendar.getInstance(); //prazo de entrega
        cal.setTime(dataTeste);
        cal.add(Calendar.DATE, (int) model.getValueAt(selectedRowIndex, 14)); //////////disponibilidade
        dataTeste = cal.getTime();
        SimpleDateFormat dataBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//trocar e implemtar horario de fechamento da biblioteca
        String dataFormatada = dataBR.format(dataTeste);
        txtPrazoEntrega.setText(String.valueOf(dataFormatada));
        wdwEmprestimos.setSelectedIndex(2);
    }//GEN-LAST:event_tabelaLivrosMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtAgoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgoraActionPerformed

    private void wdwEmprestimosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_wdwEmprestimosComponentShown
        //listarUsers();
    }//GEN-LAST:event_wdwEmprestimosComponentShown

    private void btnPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisar1ActionPerformed
    }//GEN-LAST:event_btnPesquisar1ActionPerformed

    private void btnPesquisar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisar1KeyReleased

    }//GEN-LAST:event_btnPesquisar1KeyReleased

    private void txtPesquisaLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaLivrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaLivrosActionPerformed

    private void txtPesquisaLivrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaLivrosKeyReleased
        String nome = "%" + txtPesquisaLivros.getText() + "%";
        LivroDao dao = null;
        try {
            dao = new LivroDao();
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Livro> lista = dao.pesquisarNomeLivros(nome);
        DefaultTableModel dados = (DefaultTableModel) tabelaLivros.getModel();
        dados.setNumRows(0); //limpa/zera pesquisa a cada digitacao
        for (Livro c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getTitulo(),
                c.getAutor(),
                c.getEditora(),
                c.getIsbn(),
                c.getAno(),
                c.getSerie(),
                c.getEdicao(),
                c.getIdioma(),
                c.getFornecedor().getNome(),
                c.getPiso(),
                c.getCorredor(),
                c.getPosicao(),
                c.getSecao(),
                c.getDisponibilidade(),
                c.getObservacoes(),});
        }
    }//GEN-LAST:event_txtPesquisaLivrosKeyReleased

    private void wdwEmprestimosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wdwEmprestimosMouseClicked
        // TODO add your handling code here:
        //listarUsers();
    }//GEN-LAST:event_wdwEmprestimosMouseClicked

    private void tabelaLivrosFiltroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaLivrosFiltroMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaLivros.getModel();
            int selectedRowIndex = tabelaLivrosFiltro.getSelectedRow();
            txtBookId.setText(model.getValueAt(selectedRowIndex, 0).toString());
            txtTituloSelect.setText(model.getValueAt(selectedRowIndex, 1).toString());
            txtISBN.setText(model.getValueAt(selectedRowIndex, 4).toString());
            txtObservacoes.setText(model.getValueAt(selectedRowIndex, 15).toString());
            txtDisponibilidade.setText(model.getValueAt(selectedRowIndex, 14).toString());
            String path = "C:\\goldenOwl\\images\\books\\" + txtISBN.getText();
            lblImagem.setIcon(ResizeBookImage(path));
            EmprestimoDao emprestimodao = new EmprestimoDao();
            txtStatus.setText(emprestimodao.campoStatusLista(Integer.parseInt(txtBookId.getText())));
            Date dataTeste = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataTeste);
            cal.add(Calendar.DATE, (int) model.getValueAt(selectedRowIndex, 14)); //////////disponibilidade
            dataTeste = cal.getTime();
            SimpleDateFormat dataBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            //trocar e implemtar horario de fechamento da biblioteca
            String dataFormatada = dataBR.format(dataTeste);
            txtPrazoEntrega.setText(String.valueOf(dataFormatada));
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaLivrosFiltroMouseClicked

    // filtrar busca na aba  cadastro de emprestimos
    private void txtBuscaLivroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaLivroKeyReleased
        String nome = "%" + txtBuscaLivro.getText() + "%";
        LivroDao dao = null;
        try {
            dao = new LivroDao();
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Livro> lista = dao.pesquisarNomeLivros(nome);
        DefaultTableModel dados = (DefaultTableModel) tabelaLivrosFiltro.getModel();
        dados.setNumRows(0); //limpa/zera pesquisa a cada digitacao
        for (Livro c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getTitulo(),});
        }
    }//GEN-LAST:event_txtBuscaLivroKeyReleased

    //botao add to emprtestimo
    // pega emprestimo restantes e qtdade de linhas da jtable para inserrir apenas limite de emprestimos junto com numero de licros na mao do usuario (ou se numero de emprestimos restantes == 0
    private void brnAddLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnAddLivroActionPerformed
        String nome = txtTituloSelect.getText();
        Livro obj = new Livro();
        LivroDao dao = null;
        try {
            dao = new LivroDao();
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //verifica se tem livro selecionado
        if ("".equals(txtISBN.getText()) | txtISBN.getText() == null) {
            JOptionPane.showMessageDialog(null, "Selecione um Livro");
            return;
        }

        obj = (Livro) dao.buscarLivro(nome);
        if (obj.getAutor() != null) {
            if (obj.getAutor() != null) {
                caixa = (DefaultTableModel) tabelaEmprestimos.getModel();
                caixa.addRow(new Object[]{
                    txtTituloSelect.getText(),
                    txtAgora.getText(),
                    txtPrazoEntrega.getText(),
                    txtObservacoes.getText(),
                    txtBookId.getText(),});
            }
            // limpa campos
            txtBookId.setText("");
            txtISBN.setText("");
            txtTituloSelect.setText("");
            txtStatus.setText("");
            txtObservacoes.selectAll();
            txtObservacoes.replaceSelection("");
            lblImagem.setIcon(null);
            txtPrazoEntrega.setText("");
        }
    }//GEN-LAST:event_brnAddLivroActionPerformed

    //detalhes
    private void btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesActionPerformed
        String msg = txtBookId.getText();
        int number = Integer.parseInt(msg);
        int row = number - 1;
        DefaultTableModel model = (DefaultTableModel) tabelaLivros.getModel();
        String msgTit = model.getValueAt(row, 1).toString();
        String msgAut = model.getValueAt(row, 2).toString();
        String msgEdt = model.getValueAt(row, 3).toString();
        String msgIsb = model.getValueAt(row, 4).toString();
        String msgAno = model.getValueAt(row, 5).toString();
        String msgSer = model.getValueAt(row, 6).toString();
        String msgEdc = model.getValueAt(row, 7).toString();
        String msgIdi = model.getValueAt(row, 8).toString();
        String msgFor = model.getValueAt(row, 9).toString();
        String msgPis = model.getValueAt(row, 10).toString();
        String msgCor = model.getValueAt(row, 11).toString();
        String msgPos = model.getValueAt(row, 12).toString();
        String msgSec = model.getValueAt(row, 13).toString();
        String msgObs = model.getValueAt(row, 15).toString();
        FormDetalhesLivro detalhesLivro = new FormDetalhesLivro(msgTit, msgAut, msgEdt, msgIsb, msgAno, msgSer, msgEdc, msgIdi, msgFor, msgPis, msgCor, msgPos, msgSec, msgObs);
        detalhesLivro.pack();
        detalhesLivro.setLocationRelativeTo(null);
        detalhesLivro.setVisible(true);
    }//GEN-LAST:event_btnDetalhesActionPerformed

    private void btnBuscaUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaUserActionPerformed
        wdwEmprestimos.setSelectedIndex(0);
    }//GEN-LAST:event_btnBuscaUserActionPerformed

    private void btnRemoveBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveBookActionPerformed
        //Delete Selected Row        
        int getSelectedRowForDeletion = tabelaEmprestimos.getSelectedRow();

        caixa.removeRow(getSelectedRowForDeletion);
        //JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
    }//GEN-LAST:event_btnRemoveBookActionPerformed

    private void btnBuscarLivrosAdvancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLivrosAdvancedActionPerformed
        wdwEmprestimos.setSelectedIndex(1);
    }//GEN-LAST:event_btnBuscarLivrosAdvancedActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FormLeitor leitorForn = new FormLeitor();
        //centralizar
        leitorForn.pack();
        leitorForn.setLocationRelativeTo(null);
        leitorForn.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        FormLivros BookForn = new FormLivros();
        //centralizar
        BookForn.pack();
        BookForn.setLocationRelativeTo(null);
        BookForn.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    //funcao tira screenshot
    public static BufferedImage getScreenshotCartao(Component component) {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }

    public static void salvaImagemCartao(Component component, String filename) throws Exception {
        BufferedImage img = getScreenshotCartao(component);
        ImageIO.write(img, "png", new File(filename));
    }

    private void btnCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartaoActionPerformed
        //cartao
        String msg = txtId.getText();
        int number = Integer.parseInt(msg);
        int row = number - 1;
        DefaultTableModel model = (DefaultTableModel) tabelaUsuario.getModel();

        String msgNomePre = model.getValueAt(row, 1).toString();
        String msgNome = msgNomePre.toUpperCase();

        String msgCursoPre = model.getValueAt(row, 14).toString();
        String msgCurso = msgCursoPre.toUpperCase();

        String msgAcessoPre = model.getValueAt(row, 17).toString();
        String msgAcesso = msgAcessoPre.toUpperCase(); // maiusculas

        FormCartao detalhesUser = null;
        try {
            detalhesUser = new FormCartao(msg, msgNome, msgCurso, msgAcesso);
        } catch (SQLException ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
        detalhesUser.pack();
        detalhesUser.setLocationRelativeTo(null);
        detalhesUser.setVisible(true);
    }//GEN-LAST:event_btnCartaoActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed

        String se_devolvido = StatusEmprestimo.getText();
        if (se_devolvido.equals("Devolvido")) {
            JOptionPane.showMessageDialog(null, " Livro já foi devolvido");
        } else {
            try {
                EmprestimoDao devEmpres = new EmprestimoDao();
                if (txtIdEmprest.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um Emprestimo antes de prosseguir");
                } else {
                    int EmpresId = Integer.parseInt(txtIdEmprest.getText());
                    devEmpres.devolveLivro(EmpresId);
                     if (radbtnAddObs.isSelected()) {
                         int livroid = devEmpres.getEmprestimoFKeyData("tb_livros_id", Integer.parseInt(txtIdEmprest.getText()));
                         LivroDao  livro = new LivroDao();
                         livro.addObservacoes(txtObservacoesDevolucao.getText(), livroid);
                     }
                    txtIdEmprest.setText("");
                    txtLeitorresponsavel.setText("");
                    txtLivroEmprestado.setText("");
                    txtDataEmprestimo.setText("");
                    txtDataParaDevolucao.setText("");
                    StatusEmprestimo.setText("");
                    txtFuncionarioQueEmprestou.setText("");
                    txtObservacoesDevolucao.selectAll();
                    txtMulta.setText("");
                    txtObservacoesDevolucao.replaceSelection("");
                    //pegar id do user e atualizar emprestimos restantes ou limpar campo user
                }
            } catch (Exception ex) {
                Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDevolverActionPerformed

    private void tabelaDevolucoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaDevolucoesMouseClicked
        try {
            EmprestimoDao emprestDao = new EmprestimoDao();
            Utilitarios util = new Utilitarios();
            DefaultTableModel model = (DefaultTableModel) tabelaDevolucoes.getModel();
            int selectedRowIndex = tabelaDevolucoes.getSelectedRow();
            txtIdEmprest.setText(model.getValueAt(selectedRowIndex, 1).toString());
            txtLeitorresponsavel.setText(model.getValueAt(selectedRowIndex, 2).toString());
            txtLivroEmprestado.setText(model.getValueAt(selectedRowIndex, 3).toString());
            txtFuncionarioQueEmprestou.setText(model.getValueAt(selectedRowIndex, 4).toString());
            int idmprest = Integer.parseInt(txtIdEmprest.getText());
            Timestamp data_devolucao = emprestDao.getEmprestData(idmprest);
            txtDataEmprestimo.setText(model.getValueAt(selectedRowIndex, 5).toString());
            txtDataParaDevolucao.setText(model.getValueAt(selectedRowIndex, 6).toString());
            String idempres = txtIdEmprest.getText();
            int atraso = emprestDao.calculaAtraso(Integer.parseInt(idempres));
            StatusEmprestimo.setText(emprestDao.campoStatus(data_devolucao, atraso));
            double multa = emprestDao.calculaMulta(atraso);
            txtMulta.setText(util.campoMulta(multa));
            txtObservacoesDevolucao.setText(model.getValueAt(selectedRowIndex, 7).toString());

        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaDevolucoesMouseClicked

    private void txtIdEmprestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEmprestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEmprestActionPerformed

    private void radioListaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioListaItemStateChanged
        if (radioLista.isSelected()) {
            try {
                listarDevolucoes();
            } catch (Exception ex) {
                Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                listarDevolucoesCompact();
            } catch (Exception ex) {
                Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_radioListaItemStateChanged

    private void btnImprimeMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimeMultaActionPerformed
        try {
            EmprestimoDao empDao = new EmprestimoDao();
            Multa multa = new Multa();
            UsuarioDao user = new UsuarioDao();

            multa.setTb_emprestimos_id(Integer.parseInt(txtIdEmprest.getText()));
            multa.setDias_atraso(empDao.calculaAtraso(multa.getTb_emprestimos_id()));
            multa.setValor_multa(empDao.calculaMulta(multa.getDias_atraso()));
            multa.setTb_leitores_id(user.pegaUserIdpeloNome(txtLeitorresponsavel.getText()));

            MultaDao multaDao = new MultaDao();
            System.out.println("iddo emprestimo para comparar" + Integer.parseInt(txtIdEmprest.getText()));
            if (multaDao.seJaExiste((Integer.parseInt(txtIdEmprest.getText()))) == false) {
                multaDao.cadastrarMulta(multa);
            }
            int msgEmpId = multa.getTb_emprestimos_id();
            FormMulta formulta = new FormMulta(msgEmpId);
            formulta.pack();
            formulta.setLocationRelativeTo(null);
            formulta.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimeMultaActionPerformed

    private void txtTituloSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloSelectActionPerformed

    private void txtISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtISBNActionPerformed

    private void txtPrazoEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrazoEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrazoEntregaActionPerformed

    private void cbOcultarEmprestadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOcultarEmprestadosItemStateChanged
        if (!cbOcultarEmprestados.isSelected()) {
            try {
                listarLivrosFiltro();
            } catch (Exception ex) {
                Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                listarLivrosFiltroCompact(); //compact
            } catch (Exception ex) {
                Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbOcultarEmprestadosItemStateChanged

    private void btnReemprestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReemprestarActionPerformed
        try {
            String se_devolvido = StatusEmprestimo.getText();
            if (se_devolvido.equals("Devolvido")) {
                JOptionPane.showMessageDialog(null, " Livro já foi devolvido");
            } else {
                EmprestimoDao devEmpres = new EmprestimoDao();
                if (txtIdEmprest.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um Emprestimo antes de prosseguir");
                } else {
                    //dialogo paca confirmar se o livro foi inspecionado
                    //nao esta marcando is emprestado em livro
                    Emprestimo obj = new Emprestimo();
                    LivroDao livrodao = new LivroDao();
                    EmprestimoDao emprestimodao = new EmprestimoDao();
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    int iddoemprestimo = Integer.parseInt(txtIdEmprest.getText());
                    int disponibilidade = Integer.parseInt(livrodao.getLivroData("disponibilidade", emprestimodao.getEmprestimoFKeyData("tb_livros_id", iddoemprestimo)));
                    Timestamp newdata = emprestimodao.addDays(now, disponibilidade);
                    obj.setData_emprestimo(now);
                    obj.setData_entrega_agendada(newdata);
                    obj.setObservacoes(txtObservacoesDevolucao.getText());
                    Funcionario funcionario = new Funcionario();
                    String contentid = new String(Files.readAllBytes(Paths.get("C:\\GoldenOwl\\LoggedIn")));
                    funcionario.setId(Integer.parseInt(contentid));
                    obj.setTb_funcionarios_id(funcionario);
                    Usuario usuario = new Usuario();
                    usuario.setId(emprestimodao.getEmprestimoFKeyData("tb_leitores_id", iddoemprestimo));
                    obj.setTb_leitores_id(usuario);
                    Livro livro = new Livro();
                    int livroid = emprestimodao.getEmprestimoFKeyData("tb_livros_id", iddoemprestimo);
                    livro.setId(livroid);
                    obj.setTb_livros_id(livro);
                    devEmpres.devolveLivro(iddoemprestimo);

                    livrodao.setIsEmprestado(livroid);
                    int livrosemprestados = Integer.parseInt(emprestimodao.getUserData("qtd_emprestimos", usuario.getId()));//8   
                    emprestimodao.SomaEmprestimo(livrosemprestados, usuario.getId());
                    emprestimodao.cadastrarEmprestimo(obj);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReemprestarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormEmprestimos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEmprestimos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEmprestimos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEmprestimos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEmprestimos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField StatusEmprestimo;
    private javax.swing.JButton brnAddLivro;
    private javax.swing.JButton btnBuscaUser;
    private javax.swing.JButton btnBuscarLivrosAdvanced;
    private javax.swing.JButton btnCartao;
    private javax.swing.JButton btnDesbloqueiaUsuario;
    private javax.swing.JButton btnDetalhes;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnImprimeMulta;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPesquisar1;
    private javax.swing.JButton btnReemprestar;
    private javax.swing.JButton btnRemoveBook;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox cbOcultarEmprestados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblimagemUser;
    private javax.swing.JRadioButton radbtnAddObs;
    private javax.swing.JRadioButton radioLista;
    private javax.swing.JPanel tabCadastro;
    private javax.swing.JPanel tabConsultaUsuarios;
    private javax.swing.JTable tabelaDevolucoes;
    private javax.swing.JTable tabelaEmprestimos;
    private javax.swing.JTable tabelaLivros;
    private javax.swing.JTable tabelaLivrosFiltro;
    private javax.swing.JTable tabelaUsuario;
    private javax.swing.JTextField txtAgora;
    private javax.swing.JTextField txtBookId;
    private javax.swing.JTextField txtBuscaLivro;
    private javax.swing.JTextField txtDataEmprestimo;
    private javax.swing.JTextField txtDataParaDevolucao;
    private javax.swing.JTextField txtDisponibilidade;
    private javax.swing.JTextField txtEmprestimosRestantes;
    private javax.swing.JTextField txtFuncionarioQueEmprestou;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdEmprest;
    private javax.swing.JTextField txtLeitorresponsavel;
    private javax.swing.JTextField txtLivroEmprestado;
    private javax.swing.JTextField txtMulta;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextArea txtObservacoesDevolucao;
    private javax.swing.JTextField txtPesquisaLivros;
    private javax.swing.JTextField txtPesquisaUsuario;
    private javax.swing.JTextField txtPrazoEntrega;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTituloSelect;
    private javax.swing.JTabbedPane wdwEmprestimos;
    // End of variables declaration//GEN-END:variables

    private ImageIcon ResizeBookImage(String imgPath) { //192x261
        int imageX = 126;
        int imageY = 194;
        lblImagem.setSize(imageX, imageY);

        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    private ImageIcon ResizeUserImage(String imgPath) { //192x261
        int imageX = 91;
        int imageY = 119;
        lblImagem.setSize(imageX, imageY);

        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

}
//na tabela de emprestimosa se a datya de devolucao for null nnao mostrar na lista
//em gravar nocvo se campos estiverem em branco dar aviso
            // em gravar existente se ja existir dar aviso
