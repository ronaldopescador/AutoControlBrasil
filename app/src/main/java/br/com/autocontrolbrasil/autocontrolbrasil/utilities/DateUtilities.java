package br.com.autocontrolbrasil.autocontrolbrasil.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {
    public static String getDataHotaAtual() {
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        return df.format(new Date());
    }

    public static String formatarData(Long longData){
        Date data = new Date(longData);
        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        return dtf.format(data);
    }

}
