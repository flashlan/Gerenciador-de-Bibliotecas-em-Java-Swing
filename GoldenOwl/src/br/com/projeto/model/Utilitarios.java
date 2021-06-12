/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.awt.Component;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class Utilitarios {

    // método limpar tela
    public void limpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
    }

    public static boolean isNegative(double d) {
        return Double.doubleToRawLongBits(d) < 0;
    }

public interface DateUtil {

  String ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  String UTC_TIMEZONE_NAME = "UTC";

  static SimpleDateFormat provideDateFormat() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET);
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE_NAME));
    return simpleDateFormat;
  }
}

    public String campoMulta(double multa) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        if (this.isNegative(multa)) {
            String e = "Em dia";
            return e;
        } else {
            String e = formatter.format(multa);
            return e;
        }
    }

}
