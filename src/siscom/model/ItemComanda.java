package siscom.model;

import java.io.Serializable;

/**
 *
 * @author tatuapu
 */
public class ItemComanda implements Serializable {
    private Produto produto;
    private int qtd;
    
    public ItemComanda(Produto prod, int qtd){
        this.produto = prod;
        this.qtd = qtd;
    }
    /* Método público <b>getProdutoItemComanda()</b> que retorna o produto deste item
    * @return Produto object
    */
    public Produto getProdutoItemComanda(){
        return this.produto;
    }
    /* <b>getQtdProdutoItemComanda()<b> retorna a quantidade do produto deste item de comanda
    * @return int qtd
    */
    public int getQtdProdutoItemComanda(){
        return qtd;
    }
    
    public void setQtdProdutoItemComanda(int qtd){
        this.qtd += qtd;
    }
}