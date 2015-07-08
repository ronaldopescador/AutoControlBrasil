package br.com.autocontrolbrasil.autocontrolbrasil.model.dao;

import android.content.Context;

import br.com.autocontrolbrasil.autocontrolbrasil.model.helper.AutoControlOpenHelper;

/**
 * Created by Edir on 02/07/2015.
 */
public class BaseDAO {

    protected AutoControlOpenHelper helper;

    public BaseDAO(Context context) {
        this.helper = new AutoControlOpenHelper(context, "auto_control_brasil", null, 1);
    }
}
