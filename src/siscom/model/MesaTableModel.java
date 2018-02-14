package siscom.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MesaTableModel extends AbstractTableModel{
    private ArrayList<Mesa> dados;
    private String[] colunas = {"Número da mesa","Quantidade de lugares"};
    public MesaTableModel(){
        this.dados = new ArrayList<>();
    }
    
    public void addRow(Mesa p){
            this.dados.add(p);
            this.fireTableDataChanged();
    }
    public void removeRow(int linha){
            this.dados.remove(linha);
            this.fireTableRowsDeleted(linha,linha);
    }
    public String getColumnName(int num){
            return this.colunas[num];
    }
    @Override
    public int getRowCount(){
            return this.dados.size();
    }
    @Override
    public int getColumnCount(){
            return this.colunas.length;
    }
    @Override
    public Object getValueAt(int linha, int coluna){
            switch(coluna){
                    case 0: return dados.get(linha).getNroMesa();
                    case 1: return dados.get(linha).getQtdLugares();
            }
            return null;
    }
    public Mesa get(int linha){
            return this.dados.get(linha);
    }
    public ArrayList<Mesa> getMesas(){
            return this.dados;
    }
    
}
