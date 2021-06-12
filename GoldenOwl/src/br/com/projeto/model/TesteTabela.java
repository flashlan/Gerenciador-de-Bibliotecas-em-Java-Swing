/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class TesteTabela {

    public static void main(String[] args) {
        JFrame f = new JFrame("Teste menu");
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        Tabela tabela = new Tabela(new TabelaModelo());
        f.getContentPane().add(tabela, BorderLayout.CENTER);
        f.setVisible(true);
    }

    private static class Tabela extends JTable {

        private static final long serialVersionUID = 1L;
        private FormatadorData formatadorData = new FormatadorData();
        private FormatadorMoeda formatadorMoeda = new FormatadorMoeda();

        public Tabela(TableModel modeloDaTabela) {
            super(modeloDaTabela);
        }

        @Override
        public TableCellRenderer getCellRenderer(int row, int column) {
            if (column == 1) {
                return formatadorData;
            }
            if (column == 2) {
                return formatadorMoeda;
            }
            return super.getCellRenderer(row, column);
        }
    }

    private static class FormatadorMoeda extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        public FormatadorMoeda() {
            super();
        }

        @Override
        protected void setValue(Object o) {
            String dataFormatada = "";
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("PT", "BR"));
            NumberFormatter nff = new NumberFormatter(nf);
            try {
                dataFormatada = nff.valueToString(o);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            super.setValue(dataFormatada);
        }
    }

    private static class FormatadorData extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        public FormatadorData() {
            super();
        }

        @Override
        protected void setValue(Object o) {
            String dataFormatada = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataFormatada = sdf.format(o);
            super.setValue(dataFormatada);
        }
    }

    private static class TabelaModelo extends AbstractTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public int getRowCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int row, int col) {
            if (row == 0 && col == 0) {
                return "linha1";
            }
            if (row == 0 && col == 1) {
                return new Timestamp(934534435344L);
            }
            if (row == 0 && col == 2) {
                return new BigDecimal("123.45");
            }

            if (row == 1 && col == 0) {
                return "linha2";
            }
            if (row == 1 && col == 1) {
                return new Timestamp(954345345355L);
            }
            if (row == 1 && col == 2) {
                return new BigDecimal("67.89");
            }
            return null;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
// if (columnIndex==1) return null;
// if (columnIndex==2) return null;
            return null;
        }

    }
}
