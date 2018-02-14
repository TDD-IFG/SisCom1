package siscom.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tatuapu
 */
public class ComandaTableModel extends AbstractTableModel {

    private ArrayList<Comanda> dados;
    private String[] colunas = {"Mesa", "QtdProdutos", "total R$"};

    public ComandaTableModel() {
        this.dados = new ArrayList<Comanda>();
    }

    public void addRow(Comanda p) {
        this.dados.add(p);
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return this.dados.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            //case 0: return dados.get(linha).getMesa().getNroMesa();
            //case 1: return dados.get(linha);
            //case 2: return dados.get(linha);
            case 0:
                return dados.get(linha).getMesa();
            case 1:
                return dados.get(linha).getQtdItens();
            case 2:
                return dados.get(linha).getTotal();
        }
        return null;
    }

    public Comanda get(int linha) {
        return this.dados.get(linha);
    }

    public ArrayList<Comanda> getComandas() {
        return this.dados;
    }
}
