package siscom.model;

import java.io.Serializable;

public class Mesa implements Serializable {
    private int nroMesa;
    private int qtdLugares;
    
    public Mesa(int nroMesa, int qtdLugares){
        this.nroMesa = nroMesa;
        this.qtdLugares = qtdLugares;
    }

    /**
     * @return the nroMesa
     */
    public int getNroMesa() {
        return nroMesa;
    }

    /**
     * @param nroMesa the nroMesa to set
     */
    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }

    /**
     * @return the qtdLugares
     */
    public int getQtdLugares() {
        return qtdLugares;
    }

    /**
     * @param qtdLugares the qtdLugares to set
     */
    public void setQtdLugares(int qtdLugares) {
        this.qtdLugares = qtdLugares;
    }
    public String toString() {
        return String.valueOf(nroMesa);
    }
    
}
