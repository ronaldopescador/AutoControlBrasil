package br.com.autocontrolbrasil.autocontrolbrasil.utilities;

import android.util.Log;

public class NumberUtilities {

    public static Integer parseInt (String valor){
        Integer resultado = 0;

        try {
            resultado = Integer.parseInt(valor);
        } catch (NumberFormatException e)
        {
            Log.e("AutoControlBrasil", "Número inválido: " + e.toString());
        }

        return resultado;
    }
}
