
package siscom.gui;

import siscom.model.MesaComboBoxModel;


/**
 *
 * @author tatuapu
 */
public class Controlador {
    private static JanelaPrincipal janelaPrincipal;
    private static MesaComboBoxModel mesaComboBoxModel;
    public static void main(String[] args){
        janelaPrincipal = new JanelaPrincipal();        
        janelaPrincipal.setVisible(true);
    }
    
}
