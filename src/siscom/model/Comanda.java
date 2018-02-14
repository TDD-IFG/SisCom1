package siscom.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tatuapu
 */
public class Comanda implements Serializable {
    private Mesa mesa;
    private ArrayList<ItemComanda> produtos = new ArrayList<ItemComanda>();
    private String data;
    
    /* Método construtor da classe <b>Comanda</b>
     @param objeto Mesa
    */
    public Comanda(Mesa mesa){
         this.mesa = mesa;         
    }
    public Mesa getMesa(){
        return this.mesa;
    }
    public ArrayList<ItemComanda> getItensComanda(){
        return this.produtos;
    }
    public void addItemComanda(ItemComanda item){
        this.produtos.add(item);
    }
    public void delItemComanda(ItemComanda item){
        this.produtos.remove(item);
    }
    public void delItemComanda(int index){
        this.produtos.remove(index);
    }
    public void limparComanda(){
        this.produtos.clear();
    }
    public int getQtdItens(){
        int total = 0;
        
        for (int i = 0; i < this.produtos.size(); i++){
            total += this.produtos.get(i).getQtdProdutoItemComanda();
        }
        return total;
    }
    
    public double getTotal(){
        double total = 0;
        
        for (int i = 0; i < produtos.size(); i++) {
            total += produtos.get(i).getProdutoItemComanda().getPrecoProduto() * produtos.get(i).getQtdProdutoItemComanda();
        }
        
        return total;
    }
    
    public void setData(String data){
        this.data = data;
    }
    
    public String getData(){
        return this.data;
    }
    

}
