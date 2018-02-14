/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscom.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class ProdutoDados {
    
    public void salvar(ArrayList<Produto> p){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream("produtos.dat");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto mesas no arquivo
	    objGravar.writeObject(p);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    
    public ArrayList<Produto> getProdutos(){
        ArrayList<Produto> produtos = null;
        try{
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("produtos.dat");

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            produtos = (ArrayList<Produto>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
            produtos = new ArrayList<Produto>();
	}
        return produtos;
        
    }
    
}
