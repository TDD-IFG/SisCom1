package siscom.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import siscom.model.Produto;
import siscom.model.ProdutoDados;
import siscom.model.ProdutoTableModel;

/**
 *
 * @author rafael
 */
public class JanelaProdutos extends JFrame{
    
    private JButton bMais;
    private JButton bMenos;
    private JButton bSalvar;
    private JTable tabela;
    private JTextField tNome;
    private JTextField tPreco;
    private JLabel lNome;
    private JLabel lPreco;
    private JanelaPrincipal janelaPrincipal;
    ProdutoTableModel modelo;
    
    
    public JanelaProdutos(JanelaPrincipal janelaPrincipal){
        super("Produtos");
        
        this.janelaPrincipal = janelaPrincipal;
        
        JPanel painel = new JPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//fecha somente a janela atual
        BorderLayout borda = new BorderLayout();
        painel.setLayout(borda);
        
        JToolBar bar = new JToolBar();
        bar.setFloatable(false);//barra não flutuável
        
        lNome = new JLabel("Nome ");
        bar.add(lNome);
        tNome = new JTextField(15);
        bar.add(tNome);
        
        lPreco = new JLabel("Preco ");
        bar.add(lPreco);
        tPreco = new JTextField(15);
        bar.add(tPreco);
        
        bMais = new JButton(" + ");
        bMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMaisActionPerformed();
            }
        });
        
        bMenos = new JButton(" - ");
        bMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMenosActionPerformed();
            }
        });
        
        bar.add(bMais);
        bar.add(bMenos);
        
        painel.add("North", bar);
        
        tabela = new JTable();
        modelo = new ProdutoTableModel();
        tabela.setModel(modelo);
        
        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add("Center", barraRolagem);
        
        JToolBar bar2 = new JToolBar();
        bar2.setFloatable(false);
        bSalvar = new JButton("Salvar");
        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed();
            }
        });
        bar2.add(bSalvar);
        
        painel.add("South", bar2);
        setContentPane(painel);
        pack();
        
    }
    
    public void bMaisActionPerformed() {
        if(tNome.equals("") || tPreco.equals("")){
            return;
        }
        
        modelo.addRow(new Produto(tNome.getText(), Double.parseDouble(tPreco.getText())));
        tNome.setText("");
        tPreco.setText("");
        
        
    }
    
    public void bMenosActionPerformed() {
        
        int i = tabela.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(null, "Nenhum produto selecionado!");
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma remoção do produto?");
            if (opcao == 0 || opcao == -1) {
                modelo.removeRow(i);
            }
        }
    }
    
    public void bSalvarActionPerformed(){
        ProdutoDados dados = new ProdutoDados();
        dados.salvar(modelo.getProdutos());
        
        janelaPrincipal.carregarProdutos();
    }

    
}
