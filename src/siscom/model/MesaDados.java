package siscom.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MesaDados {
    public void salvaMesasAtivas(ArrayList<Mesa> mesas){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream("mesasAtivas.dat");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto mesas no arquivo
	    objGravar.writeObject(mesas);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    public ArrayList<Mesa> recuperaMesasAtivas(){
        ArrayList<Mesa> mesaRecovered = null;
        try{
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("mesasAtivas.dat");

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            mesaRecovered = (ArrayList<Mesa>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
	}
        return mesaRecovered;
    }
}
