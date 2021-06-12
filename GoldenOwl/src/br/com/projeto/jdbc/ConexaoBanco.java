/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import br.com.projeto.view.FormOptions;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class ConexaoBanco {

    public Connection pegarConexao() {
        try {

            String contentIP = null;
            try {
                contentIP = new String(Files.readAllBytes(Paths.get("C:\\GoldenOwl\\ipserver")));
            } catch (IOException ex) {
                Logger.getLogger(FormOptions.class.getName()).log(Level.SEVERE, null, ex);
            }

            String contentUser;
            contentUser = new String(Files.readAllBytes(Paths.get("C:\\GoldenOwl\\DBUser")));

            String contentPass;
            contentPass = new String(Files.readAllBytes(Paths.get("C:\\GoldenOwl\\DBPass")));

            String url = "jdbc:mysql://" + contentIP + ":3306/gowl"; //Nome da base de dados
            String user = contentUser; //nome do usuário do MySQL
            String password = contentPass; //senha do MySQL
            //System.out.println(url + ipserver);

            Connection conexao = null;
            conexao = (Connection) DriverManager.getConnection(url, user, password);

            return conexao;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro" + e);
        }
        return null;
    }

}
