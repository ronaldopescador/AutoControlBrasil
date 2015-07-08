package br.com.autocontrolbrasil.autocontrolbrasil.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Edir on 04/07/2015.
 */
public class DateUtilities {
    public static String getDataHotaAtual() {
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        return df.format(new Date());
    }


}
