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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import siscom.model.Comanda;
import siscom.model.ComandaDados;
import siscom.model.ItemComanda;
import siscom.model.ItemComandaTableModel;
import siscom.model.Mesa;

/**
 *
 * @author rafael
 */
public class JanelaComandas extends JFrame {

    private JLabel lComanda;
    private JLabel lTotal;
    private Comanda comanda;
    private JTable tabela;
    private ItemComandaTableModel modelo;
    private JButton bMenos;
    private JButton bFecharComanda;
    private JanelaPrincipal janela;
    private int posicao;

    public JanelaComandas(JanelaPrincipal j, Comanda c) {
        super("Comanda");

        comanda = c;
        janela = j;
        this.posicao = posicao;

        JPanel painel = new JPanel();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BorderLayout borda = new BorderLayout();
        painel.setLayout(borda);

        JToolBar bar = new JToolBar();
        bar.setFloatable(false);
        lComanda = new JLabel("Mesa " + comanda.getMesa());
        bar.add(lComanda);

        JLabel s = new JLabel("                                                                                                  ");
        bar.add(s);

        bMenos = new JButton(" - ");
        bMenos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bMenosActionPerformed();
            }
        });
        bar.add(bMenos);

        painel.add("North", bar);

        modelo = new ItemComandaTableModel(comanda.getItensComanda());
        tabela = new JTable();
        tabela.setModel(modelo);
        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add("Center", barraRolagem);

        JToolBar bar2 = new JToolBar();
        bar2.setFloatable(false);
        lTotal = new JLabel(total());

        bFecharComanda = new JButton("Fechar comanda");
        bFecharComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharComanda();
            }
        });

        bar2.add(bFecharComanda);
        bar2.add(lTotal);

        painel.add("South", bar2);

        
        
        setLocationByPlatform(true);
        setContentPane(painel);
        pack();
    }

    public void fecharComanda() {

        int opcao = JOptionPane.showConfirmDialog(null, "Confirmar fechamento de comanda?");
            if (opcao != 0 && opcao != -1) {
                return;
            }
        
        ComandaDados dados = new ComandaDados();
        Mesa mesa = comanda.getMesa();

        dados.salvaComandasFechadas(comanda);

        for (int i = 0; i < janela.getTabelaComandaModel().getRowCount(); i++) {
            if (janela.getTabelaComandaModel().get(i).getMesa().getNroMesa() == mesa.getNroMesa()) {
                janela.getTabelaComandaModel().removeRow(i);
            }
        }

        modelo.clear();
        modelo.fireTableDataChanged();
        janela.salvarComandas();
        this.dispose();

    }

    public void bMenosActionPerformed() {

        int i = tabela.getSelectedRow();

        if (i < 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma Comanda Selecionada!");
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma remoção da comanda?");
            if (opcao == 0 || opcao == -1) {
                removerItem();
            }
        }

    }

    public void removerItem() {
        int index = tabela.getSelectedRow();

        Mesa mesa = comanda.getMesa();

        if (modelo.getItens().isEmpty()) {
            for (int i = 0; i < janela.getTabelaComandaModel().getRowCount(); i++) {
                if (janela.getTabelaComandaModel().get(i).getMesa().getNroMesa() == mesa.getNroMesa()) {
                    janela.getTabelaComandaModel().removeRow(i);
                }
            }
        }

        modelo.removeRow(index);
        modelo.fireTableDataChanged();

        janela.getTabelaComandaModel().fireTableDataChanged();
        lTotal.setText(total());
        janela.salvarComandas();
    }

    public String total() {
        if (modelo.getItens().isEmpty()) {
            return "Total R$ 0.00";
        }
        return String.valueOf("Total R$ " + comanda.getTotal());
    }
    
    public void desativaBotoes(){
        this.bFecharComanda.setVisible(false);
        this.bMenos.setVisible(false);
    }

}
