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
import br.com.projeto.dao.UsuarioDao;
import br.com.projeto.model.DateRenderer;
import br.com.projeto.model.Emprestimo;
import br.com.projeto.model.Funcionario;
import br.com.projeto.model.Livro;
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
    // #################################################################
    //add disponibilidade leitor localizacao e observações

    public void listarLivrosFiltro() throws Exception {
        LivroDao dao = new LivroDao();
        List<Livro> lista = dao.buscarLivros();
        DefaultTableModel dados = (DefaultTableModel) tabelaLivrosFiltro.getModel();
        dados.setNumRows(0);
        for (Livro c : lista) {
            dados.addRow(new Object[]{
                c.getTitulo(),
                c.getDisponibilidade(),
                //c.getDisponibilidade(), // + " Dias", //implemntar se esta emprestado (o = leitura interna apenas)
                c.getFornecedor(), //trocar para ususario que emprestou
                c.getObservacoes(),
                c.getSecao(),
                c.getId(),
                c.getIsbn(),});
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
        tabelaLivrosFiltro = new javax.swing.JTable();
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
        btnExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
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
        jComboBox1 = new javax.swing.JComboBox<>();
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
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
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
                        .addComponent(txtPesquisaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
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
                .addContainerGap(422, Short.MAX_VALUE))
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
                .addContainerGap(25, Short.MAX_VALUE))
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
                "Título", "Disponibilidade", "leitor atual", "Observações", "Localização", "Cod", "ISBN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
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
        tabelaLivrosFiltro.getColumn(tabelaLivrosFiltro.getColumnName(1)).setPreferredWidth(140);
        tabelaLivrosFiltro.getColumn(tabelaLivrosFiltro.getColumnName(0)).setPreferredWidth(170);

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

        jLabel4.setText("Data para  Devolução:");

        jLabel6.setText("Data Atual:");

        jLabel7.setText("Livro:");

        txtTituloSelect.setEditable(false);
        txtTituloSelect.setForeground(new java.awt.Color(102, 102, 102));

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

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_small.png"))); // NOI18N
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
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

        jCheckBox1.setText("Mostrar Emprestados");

        jButton5.setText("Mapa");

        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print_small.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout tabCadastroLayout = new javax.swing.GroupLayout(tabCadastro);
        tabCadastro.setLayout(tabCadastroLayout);
        tabCadastroLayout.setHorizontalGroup(
            tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCadastroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addComponent(btnDetalhes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarLivrosAdvanced)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13))
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabCadastroLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabCadastroLayout.createSequentialGroup()
                                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tabCadastroLayout.createSequentialGroup()
                                        .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTituloSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                                    .addComponent(txtDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAgora, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                        .addComponent(txtPrazoEntrega)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCadastroLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(159, 159, 159))
                                    .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tabCadastroLayout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addComponent(jLabel8))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(brnAddLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
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
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveBook))
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addComponent(btnImprimir)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNovo))
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
                            .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveBook))
                        .addGap(18, 18, 18)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImprimir)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNovo))
                        .addGap(187, 187, 187))
                    .addGroup(tabCadastroLayout.createSequentialGroup()
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtBuscaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDetalhes)
                            .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton13)
                                .addComponent(jButton5)
                                .addComponent(btnBuscarLivrosAdvanced)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(brnAddLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtTituloSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabCadastroLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtAgora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tabCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPrazoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                true, true, false, true, true, true, true, true, true
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

        jButton9.setText("Localização");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnImprimeMulta.setText("Imprime Multa");

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

        jRadioButton1.setText("Add Observações como permanente  para o Livro");

        jRadioButton2.setText("Mostrar Devolvidos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel12))
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtIdEmprest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLeitorresponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtLivroEmprestado, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
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
                                            .addComponent(StatusEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDataParaDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFuncionarioQueEmprestou, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReemprestar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImprimeMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDesbloqueiaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton9)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jRadioButton2)
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdEmprest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLeitorresponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtLivroEmprestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(jScrollPane7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnImprimeMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnDesbloqueiaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnReemprestar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(jLabel22))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtFuncionarioQueEmprestou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDataParaDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(StatusEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {StatusEmprestimo, txtDataEmprestimo, txtDataParaDevolucao, txtMulta});

        wdwEmprestimos.addTab("Devoluções", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(wdwEmprestimos)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wdwEmprestimos, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int bookId;
        // se tem 2 na lista nao avisa que ja foi emprestado
        try {
            EmprestimoDao userE = new EmprestimoDao();
            EmprestimoDao us = new EmprestimoDao();
            Livro lvr = new Livro();
            LivroDao livro = new LivroDao();
            Usuario uso = new Usuario();
            String uservide = txtId.getText(); //id string
            int useridE;
            try {
                useridE = Integer.parseInt(uservide);//id int
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro! Selecione um Usuário");
                wdwEmprestimos.setSelectedIndex(0);
                return;
            }
            int limiteE = Integer.parseInt(userE.getUserData("emprestmax", useridE));//
            System.out.println("useridE=" + useridE);
            int emprestadosE = Integer.parseInt(userE.getUserData("qtd_emprestimos", useridE));
            int restantesE = limiteE - emprestadosE;
            String sE = String.valueOf(restantesE);
            txtEmprestimosRestantes.setText(sE);//_____________________restantes combobox
            if (restantesE > 0) {
                while (tabelaEmprestimos.getRowCount() > 0) {
                    try {
                        if (tabelaEmprestimos != null && tabelaEmprestimos.getRowCount() > 0) {//seleciona primeira linha
                            tabelaEmprestimos.getSelectionModel().setSelectionInterval(0, 0);
                        }
                        DefaultTableModel modelo = (DefaultTableModel) tabelaEmprestimos.getModel();
                        int row = tabelaEmprestimos.getSelectedRow();
                        lvr.setId(Integer.parseInt((String) modelo.getValueAt(row, 4)));
                        int idDoLivro = lvr.getId();//us.getEmprestimoFKeyData("tb_livros_id", idDoEmpretstimo);// int id do livro
                        String seLivroEmprestado = livro.getLivroData("is_emprestado", idDoLivro);
                        System.out.println("idDoLivro-" + idDoLivro + "/lvr.getId()-" + lvr.getId() + "/seLivroEmprestado-" + seLivroEmprestado);
                        if (seLivroEmprestado.equals("0")) {//_________________is emprestado?
                            Emprestimo emp = new Emprestimo();
                            int useridI = Integer.parseInt(txtId.getText());
                            int limiteI = Integer.parseInt(us.getUserData("emprestmax", useridI));
                            int emprestadosI = Integer.parseInt(us.getUserData("qtd_emprestimos", useridI));
                            int restantesI = limiteI - emprestadosI;
                            String sI = String.valueOf(restantesI);
                            txtEmprestimosRestantes.setText(sI);//_____________________________________alera campo emprestados
                            Timestamp tmsp = new Timestamp(System.currentTimeMillis());//tempo agora
                            lvr.setId(Integer.parseInt((String) modelo.getValueAt(row, 4)));
                            bookId = lvr.getId(); // var. ja inicialzada
                            String disponibilidadePre = livro.getLivroData("disponibilidade", bookId);
                            int disponibilidade = Integer.parseInt(disponibilidadePre);
                            Timestamp prazoEntrega = us.addDays(tmsp, disponibilidade);
                            emp.setData_emprestimo(tmsp);//1  //trocar e implemtar horario de fechamento da biblioteca
                            txtPrazoEntrega.setText(String.valueOf(prazoEntrega)); //cast to cal
                            emp.setData_entrega_agendada(prazoEntrega);//2
                            emp.setObservacoes(modelo.getValueAt(row, 3).toString());//3
                            emp.setTb_livros_id(lvr);//4
                            String contentid;
                            contentid = new String(Files.readAllBytes(Paths.get("C:\\GoldenOwl\\LoggedIn")));
                            Funcionario fnc = new Funcionario();
                            fnc.setId(Integer.parseInt(contentid));
                            emp.setTb_funcionarios_id(fnc);//5 //set funcionario id
                            uso.setId(useridI);
                            emp.setTb_leitores_id(uso);//6
                            EmprestimoDao empD = new EmprestimoDao();
                            empD.cadastrarEmprestimo(emp);// passar variavel default
                            System.out.println("useridI===" + useridI);
                            livro.setIsEmprestado(idDoLivro);//7 ////////____*******
                            int somaemprestado = emprestadosI + 1;//8   
                            us.SomaEmprestimo(somaemprestado, useridI);
                            caixa.removeRow(0);
                        } else {
                            JOptionPane.showMessageDialog(null, "Livro já foi Emprestado, verifique!");
                            break;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                int emprestados2 = Integer.parseInt(userE.getUserData("qtd_emprestimos", useridE));//altera campo  restantes no final do processo
                int restantes2 = limiteE - emprestados2;
                String s2 = String.valueOf(restantes2);
                txtEmprestimosRestantes.setText(s2);
            } else {
                JOptionPane.showMessageDialog(null, "Limite de empréstimos excedido, precisa devolver Livros antes de emprestar outros!");
            }
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
//        Funcionario obj = new Funcionario();
//        obj.setId(Integer.valueOf(txtId.getText()));
//        FuncionarioDao dao = new FuncionarioDao();
//        dao.checkIdFuncionarioExist(obj);

    }//GEN-LAST:event_btnImprimirActionPerformed

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
            listarDevolucoes();
        } catch (Exception ex) {
            Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_formWindowActivated

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //editar
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // excluir
//        Funcionario obj = new Funcionario();
//        obj.setId(Integer.valueOf(txtId.getText()));
//        FuncionarioDao dao = new FuncionarioDao();
//        dao.excluirFuncionario(obj);

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
//        Utilitarios util = new Utilitarios();
//        util.limpaTela(tabCadastro);
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
        // TODO add your handling code here:

    }//GEN-LAST:event_btnPesquisar1KeyReleased

    private void txtPesquisaLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaLivrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaLivrosActionPerformed

    private void txtPesquisaLivrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaLivrosKeyReleased
        // TODO add your handling code here:
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
        DefaultTableModel model = (DefaultTableModel) tabelaLivros.getModel();
        int selectedRowIndex = tabelaLivrosFiltro.getSelectedRow();
        txtBookId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txtTituloSelect.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txtISBN.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txtObservacoes.setText(model.getValueAt(selectedRowIndex, 15).toString());
        txtDisponibilidade.setText(model.getValueAt(selectedRowIndex, 14).toString());
        String path = "C:\\goldenOwl\\images\\books\\" + txtISBN.getText();
        lblImagem.setIcon(ResizeBookImage(path));
        Date dataTeste = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataTeste);
        cal.add(Calendar.DATE, (int) model.getValueAt(selectedRowIndex, 14)); //////////disponibilidade
        dataTeste = cal.getTime();
        SimpleDateFormat dataBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//trocar e implemtar horario de fechamento da biblioteca
        String dataFormatada = dataBR.format(dataTeste);
        txtPrazoEntrega.setText(String.valueOf(dataFormatada));
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
    // add funcao para limapr campos no final
    // nao add se ja existe
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
                    // limpa campos
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
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimeMulta;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPesquisar1;
    private javax.swing.JButton btnReemprestar;
    private javax.swing.JButton btnRemoveBook;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblimagemUser;
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
