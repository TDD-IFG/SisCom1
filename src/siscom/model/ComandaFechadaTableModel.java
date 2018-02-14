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
public class ComandaFechadaTableModel extends AbstractTableModel{
    
    private String[] colunas = {"Mesa", "QtdProdutos", "Total R$", "Data"};
    private ArrayList<Comanda> comandas;
    
    public ComandaFechadaTableModel(ArrayList<Comanda> c){
        this.comandas = c;
    }
    
    @Override
    public Object getValueAt(int linha, int coluna){
        switch(coluna){
            case 0: return comandas.get(linha).getMesa();
            case 1: return comandas.get(linha).getQtdItens();
            case 2: return comandas.get(linha).getTotal();
            case 3: return comandas.get(linha).getData();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return this.comandas.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }
    
    public ArrayList<Comanda> getComandas(){
        return this.comandas;
    }
}
