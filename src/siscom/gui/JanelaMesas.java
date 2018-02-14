package siscom.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import siscom.model.Mesa;
import siscom.model.MesaComboBoxModel;
import siscom.model.MesaDados;
import siscom.model.MesaTableModel;

public class JanelaMesas extends JFrame {

    private JButton bMais;
    private JButton bMenos;
    private JButton bSalvar;
    private JButton bCarregar;
    private JTable tabela;
    private JTextField tQtdLugares;
    private JTextField tNroMesa;
    private MesaComboBoxModel mesaComboBoxModel;
    private JanelaPrincipal janelaPrincipal;

    public JanelaMesas() {
        super("Cadastro de Mesas");
        JPanel painel = new JPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//fecha somente a janela atual
        BorderLayout borda = new BorderLayout();
        painel.setLayout(borda);

        JToolBar bar = new JToolBar();
        bar.setFloatable(false);//barra não flutuável
        JLabel lMesa = new JLabel("Mesa: ");
        bar.add(lMesa);
        tNroMesa = new JTextField(15);
        bar.add(tNroMesa);
        JLabel lQtdLugares = new JLabel("Quantidade de lugares: ");
        bar.add(lQtdLugares);
        tQtdLugares = new JTextField(15);
        bar.add(tQtdLugares);

        bMais = new JButton(" + ");
        bMenos = new JButton(" - ");
        bar.add(bMais);
        bar.add(bMenos);
        painel.add("North", bar);

        //criando a tabela
        tabela = new JTable();
        MesaTableModel modelo = new MesaTableModel();
        tabela.setModel(modelo);
        this.bCarregarActionPerformed();//carregando dados salvos
        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add("Center", barraRolagem);

        JToolBar bar2 = new JToolBar();
        bSalvar = new JButton("Salvar");
        bar2.add(bSalvar);
        bCarregar = new JButton("Carregar");
        bar2.add(bCarregar);
        painel.add("South", bar2);

        setContentPane(painel);
        pack();//força a tela a ser ajustada conforme o tamanho dos componentes internos

        /* Ações dos botões   */
        bMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMaisActionPerformed();
            }
        });
        bMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMenosActionPerformed();
            }
        });
        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed();
            }
        });
        bCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCarregarActionPerformed();
            }
        });
    }

    public void bMenosActionPerformed() {
        //obtendo o model da tabela
        MesaTableModel modelo = (MesaTableModel) this.tabela.getModel();
        int i = tabela.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma mesa selecionada!");
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma remoção da mesa?");
            if (opcao == 0 || opcao == -1) {
                modelo.removeRow(i);
            }
        }
    }

    public void bMaisActionPerformed() {
        //obtendo o model da tabela
        MesaTableModel modelo = (MesaTableModel) this.tabela.getModel();
        String nMesa = this.tNroMesa.getText();
        String qMesa = this.tQtdLugares.getText();
        modelo.addRow(new Mesa(Integer.parseInt(nMesa), Integer.parseInt(qMesa)));
    }

    public void bSalvarActionPerformed() {
        //obtendo o model da tabela
        MesaTableModel modelo = (MesaTableModel) this.tabela.getModel();
        MesaDados mDMesa = new MesaDados();
        ArrayList<Mesa> mesas = modelo.getMesas();
        mDMesa.salvaMesasAtivas(mesas);
        this.janelaPrincipal.preencheDadosMesas();
    }

    public void bCarregarActionPerformed() {
        //criando um novo model para a tabela
        MesaTableModel cm = new MesaTableModel();

        MesaDados mDMesa = new MesaDados();
        ArrayList<Mesa> mesas = mDMesa.recuperaMesasAtivas();
        for (int i = 0; i < mesas.size(); i++) {//varrendo as comandas salvas
            cm.addRow(mesas.get(i)); //add as mesas no model                    
        }
        tabela.setModel(cm);
    }

    public void setJanelaPrincipal(JanelaPrincipal janelaP) {
        this.janelaPrincipal = janelaP;
    }
}
