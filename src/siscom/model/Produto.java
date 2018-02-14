
package siscom.model;

import java.io.Serializable;

/**
 *
 * @author tatuapu
 */
public class Produto implements Serializable {
    private static int count = 0;
    private int idProduto;
    private String nomeProduto;
    private Double precoProduto;
    
    public Produto(String nomeProduto,Double precoProduto){
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        count++;
        this.idProduto = count;
    }
    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @return the nomeProduto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * @param nomeProduto the nomeProduto to set
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * @return the precoProduto
     */
    public Double getPrecoProduto() {
        return precoProduto;
    }

    /**
     * @param precoProduto the precoProduto to set
     */
    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
    public String toString(){
        return this.nomeProduto;
    }
}
