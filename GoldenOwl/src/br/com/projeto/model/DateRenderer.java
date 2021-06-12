/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
 public class DateRenderer extends DefaultTableCellRenderer {

    public DateRenderer() { // This is a contructor
        DateFormatter formatter = new DateFormatter("yyyy-MM-dd");
    }

    public class DateFormatter extends SimpleDateFormat { //This another class within a class

        public DateFormatter(String pattern) {
            super(pattern);
        }
    }
}
