/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscom.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import siscom.model.Comanda;
import siscom.model.ComandaDados;
import siscom.model.ComandaFechadaTableModel;

/**
 *
 * @author rafael
 */
public class JanelaHistorico extends JFrame {

    private JLabel lTitulo;
    private JLabel lTotal;
    private JTable tabela;
    private ComandaFechadaTableModel modelo;
    private JButton bAbrirComanda;

    public JanelaHistorico() {
        super("Histórico de comandas");

        JPanel painel = new JPanel();
        BorderLayout borda = new BorderLayout();
        painel.setLayout(borda);

        JToolBar bar = new JToolBar();
        bar.setFloatable(false);
        lTitulo = new JLabel("Comandas");
        bar.add(lTitulo);

        painel.add("North", bar);

        tabela = new JTable();

        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add("Center", barraRolagem);

        ComandaDados dados = new ComandaDados();

        modelo = new ComandaFechadaTableModel(dados.recuperaComandasFechadas());
        tabela.setModel(modelo);

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    abreComanda();
                }
            }
        });

        JToolBar bar2 = new JToolBar();
        bar2.setFloatable(false);

        bAbrirComanda = new JButton("Abrir comanda(s)");
        bAbrirComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abreComanda();
            }
        });

        lTotal = new JLabel();
        lTotal.setText("               Faturamento total: R$ " + getTotal());
        bar2.add(bAbrirComanda);
        bar2.add(lTotal);

        painel.add("South", bar2);

        setContentPane(painel);
        pack();

    }

    public double getTotal() {
        double total = 0;

        for (Comanda c : modelo.getComandas()) {
            total += c.getTotal();
        }
        return total;
    }

    public void abreComanda() {

        int index[] = tabela.getSelectedRows();

        for (int i = 0; i < index.length; i++) {
            JanelaComandas j = new JanelaComandas(null, modelo.getComandas().get(index[i]));
            j.desativaBotoes();
            j.setVisible(true);
        }
    }

}
