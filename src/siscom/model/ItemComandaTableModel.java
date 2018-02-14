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
public class ItemComandaTableModel extends AbstractTableModel{
    
    private ArrayList<ItemComanda> itens;
    private String[] colunas = {"Produto","Preco", "Quantidade", "Total R$"};
    
    public ItemComandaTableModel(ArrayList<ItemComanda> i){
        this.itens = i;
    }
    
    public void addRow(ItemComanda item){
        this.itens.add(item);
    }
    
    public void removeRow(int index){
        this.itens.remove(index);
        this.fireTableRowsDeleted(index, index);
    }
    
    @Override
    public int getRowCount() {
        return itens.size();
    }
    
    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }
    
    public void clear(){
        this.itens = new ArrayList<ItemComanda>();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch (columnIndex){
            case 0: return itens.get(rowIndex).getProdutoItemComanda().getNomeProduto();
            case 1: return itens.get(rowIndex).getProdutoItemComanda().getPrecoProduto();
            case 2: return itens.get(rowIndex).getQtdProdutoItemComanda();
            case 3: return itens.get(rowIndex).getProdutoItemComanda().getPrecoProduto() * itens.get(rowIndex).getQtdProdutoItemComanda();
        }
        
        return null;
    }
    
    public ArrayList<ItemComanda> getItens(){
        return this.itens;
    }
    
}
