/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.view;

import br.com.projeto.dao.OptionsDao;
import br.com.projeto.model.QRCodeGenerator;
import java.awt.Image;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.google.zxing.WriterException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class FormCartao extends javax.swing.JFrame  {

   // Image src;

    /**
     * Creates new form FormCartao
     */
    public FormCartao() {
       // src = Toolkit.getDefaultToolkit().createImage("C:\\GoldenOwl\\images\\cardBackground.jpg");
        initComponents();
    }



    public FormCartao(String msg, String msgNome, String msgCurso, String msgAcesso) throws SQLException {
        initComponents();
//        int in = Integer.parseInt(msg);
//        String id = String.format("%08d", in);
        lblId.setText(msg);
        lblNome.setText(msgNome);
        lblCurso.setText(msgCurso);

        OptionsDao opt = new OptionsDao();
        String biblioteca = opt.retornaOption(15);
        lblInstituicao.setText(biblioteca.toUpperCase()); // global! nao precisa importar do outro frame

        lblAcesso.setText(msgAcesso);  // nivel  de acesso a implementar  

        String path = "C:\\goldenOwl\\images\\usuarios\\" + msg;
        lblFoto.setIcon(ResizeIdImage(path));

        String pathLogo = "C:\\goldenOwl\\images\\libraryLogo.png";
        lblLogo.setIcon(ResizeLogoImage(pathLogo));

        int i = Integer.parseInt(msg);
        //parse to int e add zero
        String toCode = String.format("%08d", i);

        //qrcode 
        String QrCodeName = toCode;
        String QR_CODE_IMAGE_PATH = "C:\\GoldenOwl\\images\\QrCodes\\";
        String Finalpath = QR_CODE_IMAGE_PATH + QrCodeName;
        QRCodeGenerator genCode = new QRCodeGenerator();
        try {
            genCode.generateQRCodeImage(toCode, 550, 550, Finalpath);
        } catch (WriterException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String QrImage = "C:\\GoldenOwl\\images\\QrCodes\\" + toCode;
        lblQrcode.setIcon(ResizeQrCodeImage(QrImage));

        //lblCodBarras.setIcon(toCode);
        String toBCode = String.format("%08d", i);
        String BarCodeName = toBCode;
        String BAR_CODE_IMAGE_PATH = "C:\\GoldenOwl\\images\\BarCodes\\";
        String Finalbpath = BAR_CODE_IMAGE_PATH + BarCodeName;
        QRCodeGenerator genBarCode = new QRCodeGenerator();
        try {
            genBarCode.generateBarCodeImage(toCode, 340, 150, Finalbpath);
        } catch (WriterException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String BarCodeImage = "C:\\GoldenOwl\\images\\BarCodes\\" + toCode;
        lblCodBarras.setIcon(ResizeBarCodeImage(BarCodeImage));

    }
    
    //criar classe em arquivo separado aqui
    class PrintObject implements Printable
{
        public int print(Graphics g, PageFormat f, int pageIndex) {
        Graphics2D g2 = (Graphics2D) g; // Allow use of Java 2 graphics on
// the print pages :

        String msg =lblId.getText();
        if (pageIndex == 0) {
            
            // tamabnho de margens e papel
//            Paper p = new Paper();
            //p.setSize(5.48, 8.6);
            //f.setPaper(p);
//            double margin = 20.;
//p.setImageableArea(margin, 
//        p.getImageableY(), 
//        p.getWidth() - 2* margin, p.getImageableHeight());
//
//            f.setPaper(p);
            
            try {
                g2.drawImage(ImageIO.read(new File("C:\\GoldenOwl\\images\\cards\\" + msg + ".png" )), null, pageIndex, pageIndex);
            } catch (IOException ex) {
                Logger.getLogger(PrintObject.class.getName()).log(Level.SEVERE, null, ex);
            }
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }
    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblInstituicao = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblAcesso = new javax.swing.JLabel();
        lblQrcode = new javax.swing.JLabel();
        lblCodBarras = new javax.swing.JLabel();
        lblCurso = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cartao");
        setLocation(new java.awt.Point(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));

        lblInstituicao.setBackground(new java.awt.Color(0, 51, 153));
        lblInstituicao.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblInstituicao.setForeground(new java.awt.Color(0, 51, 153));
        lblInstituicao.setText("Nome da Instituição");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("ID:");

        lblId.setBackground(new java.awt.Color(235, 235, 235));
        lblId.setForeground(new java.awt.Color(0, 0, 0));
        lblId.setText("XXXXXXX");
        lblId.setOpaque(true);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setText("Curso:");

        setContentPane(new JLabel(new ImageIcon("C:/GoldenOwl/images/cardBackground.jpg")));
        lblFoto.setBackground(new java.awt.Color(255, 255, 255));
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/leitor.png"))); // NOI18N
        lblFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray));
        lblFoto.setOpaque(true);

        jLabel6.setForeground(new java.awt.Color(0, 102, 204));
        jLabel6.setText("Nome:");

        lblNome.setBackground(new java.awt.Color(235, 235, 235));
        lblNome.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblNome.setForeground(new java.awt.Color(0, 0, 0));
        lblNome.setText("XXXXXX XX XXXXXXX XXXXXXXXXXXX");
        lblNome.setOpaque(true);

        lblAcesso.setBackground(new java.awt.Color(51, 153, 255));
        lblAcesso.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblAcesso.setForeground(new java.awt.Color(0, 102, 204));
        lblAcesso.setText("ESTUDANTE");

        lblQrcode.setBackground(new java.awt.Color(255, 255, 255));
        lblQrcode.setText("QRCODE");
        lblQrcode.setOpaque(true);

        lblCodBarras.setBackground(new java.awt.Color(255, 255, 255));
        lblCodBarras.setText("Cod Barras");
        lblCodBarras.setOpaque(true);

        lblCurso.setBackground(new java.awt.Color(235, 235, 235));
        lblCurso.setForeground(new java.awt.Color(0, 0, 0));
        lblCurso.setText("XXXXXXXXXXXXXXXXXXXX");
        lblCurso.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblAcesso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblQrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblId))))
                                    .addComponent(lblInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 8, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(lblCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCurso))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        btnPrint.setText("Imprimir");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jButton3.setText("Fechar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSave.setText("Salvar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnPrint)
                    .addComponent(jButton3))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        PrinterJob job = PrinterJob.getPrinterJob();

  // It is first called to tell it what object will print each page.
  job.setPrintable(new PrintObject());

  // Then it is called to display the standard print options dialog.
  if (job.printDialog())
  {
     // If the user has pressed OK (printDialog returns true), then go
     // ahead with the printing. This is started by the simple call to
     // the job print() method. When it runs, it calls the page print
     // object for page index 0. Then page index 1, 2, and so on
     // until NO_SUCH_PAGE is returned.
     try { job.print(); }
     catch (PrinterException e) { System.out.println(e); }
  }

    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String msg = lblId.getText();
            salvaImagemCartao(jPanel1, "C:\\GoldenOwl\\images\\cards\\" + msg + ".png");
        } catch (Exception ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(FormCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCartao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAcesso;
    private javax.swing.JLabel lblCodBarras;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblInstituicao;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblQrcode;
    // End of variables declaration//GEN-END:variables
//jPanel1.setLayout(new OverlayLayout(panel));
    private ImageIcon ResizeIdImage(String imgPath) { //192x261
        int imageX = 65;
        int imageY = 73;
        lblFoto.setSize(imageX, imageY);

        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    private ImageIcon ResizeLogoImage(String imgPath) { //192x261
        int imageX = 84;
        int imageY = 83;
        lblLogo.setSize(imageX, imageY);

        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    private ImageIcon ResizeQrCodeImage(String imgPath) { //192x261
        int imageX = 50;
        int imageY = 50;
        lblQrcode.setSize(imageX, imageY);

        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(lblQrcode.getWidth(), lblQrcode.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    private ImageIcon ResizeBarCodeImage(String imgPath) { //192x261
        int imageX = 113;
        int imageY = 50;
        lblCodBarras.setSize(imageX, imageY);

        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(lblCodBarras.getWidth(), lblCodBarras.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;

    }

}
