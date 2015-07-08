package br.com.autocontrolbrasil.autocontrolbrasil.utilities;

import android.os.Environment;

import java.io.File;

/**
 * Created by Edir on 04/07/2015.
 */
public class FileUtilities {
    private static void validarDiretorio(File nomeArquivo){
        if (!nomeArquivo.exists()){
            nomeArquivo.mkdirs();
        }
    }

    public static File diretorioPadraoArquivos(){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "");
        validarDiretorio(file);
        return file;
    }

    public static File getNomeArquivoImagem(){
        File dirImagens = diretorioPadraoArquivos();

        String nomeArquivo = "MeuVeiculo_" + DateUtilities.getDataHotaAtual() + ".jpg";

        File arqImage = new File(dirImagens, nomeArquivo);

        return arqImage;
    }

}
