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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tatuapu
 */
public class ComandaDados {
    public void salvaComanda(Comanda com){
        String nomeArquivo = "Comanda"+com.getMesa().getNroMesa()+".dat";
        try{
		FileOutputStream arquivoGrav = new FileOutputStream(nomeArquivo);
 		ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

		    //Grava o objeto cliente no arquivo
		    objGravar.writeObject(com);
		    objGravar.flush();

		    objGravar.close();
		    arquivoGrav.flush();

		    arquivoGrav.close();
	   }catch(Exception e){
		e.printStackTrace();
	   }
    }
    public Comanda recuperaComanda(Comanda com){
        String nomeArquivo = "Comanda"+com.getMesa().getNroMesa()+".dat";
        Comanda comRecovered = null;
        try{
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream(nomeArquivo);

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            comRecovered = (Comanda) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
	}
        return comRecovered;
    }
    public void salvaComandasAtivas(ArrayList<Comanda> com){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream("comandasAtivas.dat");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto cliente no arquivo
	    objGravar.writeObject(com);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    public ArrayList<Comanda> recuperaComandasAtivas(){
        ArrayList<Comanda> comRecovered = null;
        try{
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("comandasAtivas.dat");

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            comRecovered = (ArrayList<Comanda>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
            comRecovered = new ArrayList<Comanda>();
	}
        return comRecovered;
    }
    
    public ArrayList<Comanda> recuperaComandasFechadas(){
        ArrayList<Comanda> comRecovered = null;
        
        
        try{
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("comandasFechadas.dat");

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
         
            comRecovered = (ArrayList<Comanda>)objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(ClassCastException e){
            e.printStackTrace();
            comRecovered = new ArrayList<Comanda>();
	}catch (Exception e){
            comRecovered = new ArrayList<Comanda>();
        }
        return comRecovered;
    }
    
    
    public void salvaComandasFechadas(Comanda com){

        ArrayList <Comanda> comandas = this.recuperaComandasFechadas();
        
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        com.setData(sdf.format(data));
        
        comandas.add(com);
        
        try{
            FileOutputStream arquivoGrav = new FileOutputStream("comandasFechadas.dat");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto cliente no arquivo
	    objGravar.writeObject(comandas);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    
}
