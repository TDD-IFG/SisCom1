/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscom.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rafael
 */
public class ProdutoTableModel extends AbstractTableModel {

    private ArrayList<Produto> produtos;
    private String[] colunas = {"Código", "Nome", "Preço"};

    public ProdutoTableModel() {
        ProdutoDados p = new ProdutoDados();
        
        this.produtos = p.getProdutos();
    }

    public void addRow(Produto p) {
        produtos.add(p);
        this.fireTableDataChanged();
    }

    public void removeRow(int index) {
        produtos.remove(index);
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return this.produtos.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return produtos.get(rowIndex).getIdProduto();
            case 1: return produtos.get(rowIndex).getNomeProduto();
            case 2: return produtos.get(rowIndex).getPrecoProduto();
        }
        return null;
    }
    
    public ArrayList<Produto> getProdutos(){
        return this.produtos;
    }

}
