package br.com.autocontrolbrasil.autocontrolbrasil.model.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edir on 02/07/2015.
 */
public class AutoControlOpenHelper extends SQLiteOpenHelper {


    public AutoControlOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sql = null;

        sql = new StringBuffer();
        sql.append("create table veiculo ( ");
        sql.append("       _id INTEGER PRIMARY KEY, ");
        sql.append("       placa TEXT, ");
        sql.append("       nome TEXT, ");
        sql.append("       foto BLOB, ");
        sql.append("       ano_modelo INTEGER ");
        sql.append("       )");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
