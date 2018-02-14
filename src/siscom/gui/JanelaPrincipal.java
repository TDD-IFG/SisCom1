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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import siscom.model.Comanda;
import siscom.model.ComandaDados;
import siscom.model.ComandaTableModel;
import siscom.model.ItemComanda;
import siscom.model.Mesa;
import siscom.model.MesaComboBoxModel;
import siscom.model.MesaDados;
import siscom.model.Produto;
import siscom.model.ProdutoDados;

public class JanelaPrincipal extends JFrame {

    private JButton bMais;
    private JButton bMenos;
    private JButton bAbrirComanda;
    private JButton bHistorico;
    private JTable tabela;
    private JTextField tQtd;
    private JComboBox<Mesa> jcMesas;
    private JComboBox<Produto> jcProdutos;
    private MesaComboBoxModel mesaComboBoxModel;
    private ComandaTableModel tabelaComandaModel;
    private MenuAplicacao menuAplicacao;

    public JanelaPrincipal() {
        super("SisCom - Gestão de Comandas - V 1.0 beta");
        JPanel painel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JToolBar bar = new JToolBar();
        BorderLayout borda = new BorderLayout();
        painel.setLayout(borda);

        //setando o menu da janela
        this.menuAplicacao = new MenuAplicacao(this);
        setJMenuBar(this.menuAplicacao);

        JLabel lMesa = new JLabel("Mesa: ");

        this.mesaComboBoxModel = new MesaComboBoxModel();
        jcMesas = new JComboBox<>();
        jcMesas.setModel(this.mesaComboBoxModel);
        JLabel lProduto = new JLabel("Produto: ");
        jcProdutos = new JComboBox();
        
        jcProdutos.setSelectedIndex(-1);
        JLabel lQtd = new JLabel("Qtde: ");
        tQtd = new JTextField(15);

        bar.add(lMesa);
        bar.add(jcMesas);
        bar.add(lProduto);
        bar.add(jcProdutos);
        bar.add(lQtd);
        bar.add(tQtd);

        bMais = new JButton(" + ");
        bMenos = new JButton(" - ");
        bar.add(bMais);
        bar.add(bMenos);

        bar.setFloatable(false);
        painel.add("North", bar);
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

        //criando a tabela
        tabela = new JTable();
        //criando o model sem utilizar o salvamento em arquivo
        tabelaComandaModel = new ComandaTableModel();
        tabela.setModel(tabelaComandaModel);
        //utilizando o arquivo criado inicialmente
        
        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add("Center", barraRolagem);

        JToolBar bar2 = new JToolBar();
        bar2.setFloatable(false);
        bAbrirComanda = new JButton("Abrir comanda");
        bar2.add(bAbrirComanda);
        bHistorico = new JButton("Histórico");
        bar2.add(bHistorico);
        painel.add("South", bar2);

        bAbrirComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAbrirComandaActionPerformed();
            }
        });
        bHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //bRecarregarActionPerformed();
                bHistoricoActionPerformed();
            }
        });

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    abreComanda(tabela.getSelectedRow());
                }
            }
        });
        
        this.preencheDadosMesas();
        carregarProdutos();
        
        
        
        preencheTabela();

        setContentPane(painel);
        //setVisible(true);
        pack();//força a tela a ser ajustada conforme o tamanho dos componentes internos    
    }

    /**
     * Recarrega os elementos que apresentam dados na janela principal
     */
    public void bHistoricoActionPerformed() {
        JanelaHistorico janela = new JanelaHistorico();
        janela.setVisible(true);
    }

    public void abreComanda(int index) {
        
        ComandaTableModel c = (ComandaTableModel)tabela.getModel();
        
        JanelaComandas janela = new JanelaComandas(this, c.get(index));
        janela.setVisible(true);
    }

    public void limparComboDadosMesas() {
        this.mesaComboBoxModel.clearAll();
    }

    public void preencheDadosMesas() {
        this.limparComboDadosMesas();
        MesaDados mesaDados = new MesaDados();
        ArrayList<Mesa> mesas = mesaDados.recuperaMesasAtivas();
        for (Mesa m : mesas) {
            this.mesaComboBoxModel.addRow(m);
        }
        this.setMesaComboBoxModel(mesaComboBoxModel);
    }

    public void bAbrirComandaActionPerformed() {
        
        int i[] = this.tabela.getSelectedRows();
        
        for(int j = 0; j < i.length; j++){
            JanelaComandas janela;
            janela = new JanelaComandas(this, tabelaComandaModel.get(i[j]));
            janela.setVisible(true);
        }
        
    }

    public void salvarComandas(){
        ComandaDados c = new ComandaDados();
        c.salvaComandasAtivas(tabelaComandaModel.getComandas());
    }

    public void bMaisActionPerformed() {

        if (jcMesas.getSelectedIndex() < 0 || jcProdutos.getSelectedIndex() < 0 || tQtd.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }

        ComandaTableModel pm = (ComandaTableModel) tabela.getModel();

        ArrayList<Comanda> comandas = pm.getComandas();

        Mesa mesa = (Mesa) jcMesas.getSelectedItem();
        boolean existeComanda = false;
        boolean existeProduto = false;
        ArrayList<ItemComanda> itens;
        Produto produto = (Produto)jcProdutos.getSelectedItem();

        for (int i = 0; i < comandas.size(); i++) {
            if (mesa.getNroMesa() == comandas.get(i).getMesa().getNroMesa()) {
                itens = comandas.get(i).getItensComanda();
                for(int j = 0; j < itens.size(); j++){
                    if (produto.getNomeProduto().equals(itens.get(j).getProdutoItemComanda().getNomeProduto())){
                        pm.get(j).getItensComanda().get(j).setQtdProdutoItemComanda(Integer.parseInt(tQtd.getText()));
                        pm.fireTableCellUpdated(tabela.getSelectedRow(), tabela.getSelectedColumn());
                        existeProduto = true;
                    }
                }
                
                if(!existeProduto){
                    comandas.get(i).addItemComanda(new ItemComanda((Produto) jcProdutos.getSelectedItem(), Integer.parseInt(tQtd.getText())));
                }
                
                existeComanda = true;                
            }
        }

        if (!existeComanda) {
            ItemComanda item = new ItemComanda((Produto) jcProdutos.getSelectedItem(), Integer.parseInt(tQtd.getText()));
            Comanda c = new Comanda((Mesa) jcMesas.getSelectedItem());
            c.addItemComanda(item);
            pm.addRow(c);
        }
        
        pm.fireTableDataChanged();

        jcMesas.setSelectedIndex(-1);
        jcMesas.updateUI();
        jcProdutos.setSelectedIndex(-1);
        tQtd.setText("");
        salvarComandas();
    }

    public void bMenosActionPerformed() {
        ComandaTableModel pm = (ComandaTableModel) tabela.getModel();
        int i = tabela.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma Comanda Selecionada!");
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma remoção da comanda?");
            if (opcao == 0 || opcao == -1) {
                pm.removeRow(i);
            }
        }
        salvarComandas();
    }

    public void setMesaComboBoxModel(MesaComboBoxModel mc) {
        this.mesaComboBoxModel = mc;
        this.jcMesas.setModel(mc);
    }

    public MesaComboBoxModel getMesaComboBoxModel() {
        return (MesaComboBoxModel) this.jcMesas.getModel();
    }

    public void setTabelaComandaModel(ComandaTableModel ctm) {
        this.tabelaComandaModel = ctm;
        this.tabela.setModel(ctm);
    }

    public ComandaTableModel getTabelaComandaModel() {
        return (ComandaTableModel) this.tabela.getModel();
    }

    public MenuAplicacao getMenuAplicacao() {
        return this.menuAplicacao;
    }

    public void carregarProdutos() {
        ProdutoDados p = new ProdutoDados();

        ArrayList<Produto> produtos = p.getProdutos();
        jcProdutos.removeAllItems();
        for (int i = 0; i < produtos.size(); i++) {
            jcProdutos.addItem(produtos.get(i));
        }
        jcProdutos.setSelectedIndex(-1);
    }

    private void preencheTabela() {
        ComandaDados c = new ComandaDados();
        
        
        for(Comanda com : c.recuperaComandasAtivas()){
            tabelaComandaModel.addRow(com);
        }
        
        tabelaComandaModel.fireTableDataChanged();
        
    }
}