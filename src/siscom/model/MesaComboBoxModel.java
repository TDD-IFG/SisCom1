package siscom.model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MesaComboBoxModel extends AbstractListModel implements ComboBoxModel {
  
  ArrayList<Mesa> mesas;

  public MesaComboBoxModel(){
      this.mesas = new ArrayList<Mesa>();
  }
  Mesa selection = null;

  public void addRow(Mesa m){
      this.mesas.add(m);
      this.fireContentsChanged(m, 0, 0);
  }
  public Object getElementAt(int index) {
    return mesas.get(index);
  }

  public int getSize() {
    return mesas.size();
  }
  /**
   * limpa todos os dados existentes
   */
  public void clearAll(){
      this.mesas = new ArrayList<Mesa>();
  }

  /** Método que permite a seleção do item.
  * recebe o item selecionado na view e captura a String associada ao valor selecionado
  * @param Object anItem representa o objeto oriundo da seleção na view
  * 
  */
  public void setSelectedItem(Object anItem) {
      Mesa m = (Mesa) anItem;
      selection = m; 
  } 

  // Methods implemented from the interface ComboBoxModel
  public Mesa getSelectedItem() {
    return selection; // to add the selection to the combo box
  }
}