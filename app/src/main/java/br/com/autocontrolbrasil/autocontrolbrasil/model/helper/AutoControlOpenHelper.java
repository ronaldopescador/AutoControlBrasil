package br.com.autocontrolbrasil.autocontrolbrasil.model.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AutoControlOpenHelper extends SQLiteOpenHelper {


    public AutoControlOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sql;

        sql = new StringBuffer();
        sql.append("create table veiculo ( ");
        sql.append("       _id INTEGER PRIMARY KEY, ");
        sql.append("       placa TEXT, ");
        sql.append("       nome TEXT, ");
        sql.append("       foto BLOB, ");
        sql.append("       ano_modelo INTEGER, ");
        sql.append("       troca_oleo_filtro_previsao INTEGER DEFAULT 0, ");
        sql.append("       troca_oleo_filtro_anterior INTEGER DEFAULT 0, ");
        sql.append("       troca_pneu_freio_previsao INTEGER DEFAULT 0, ");
        sql.append("       troca_pneu_freio_anterior INTEGER DEFAULT 0, ");
        sql.append("       revisao_geral_previsao INTEGER DEFAULT 0, ");
        sql.append("       revisao_geral_anterior INTEGER DEFAULT 0 ");
        sql.append("       )");
        db.execSQL(sql.toString());

        sql = new StringBuffer();

        sql.append("create table abastecimento ( ");
        sql.append("       _id INTEGER PRIMARY KEY, ");
        sql.append("       km_anterior INTEGER, ");
        sql.append("       km_atual INTEGER, ");
        sql.append("       volume REAL, ");
        sql.append("       data INTEGER, ");
        sql.append("       valor_total REAL, ");
        sql.append("       km_media REAL ");
        sql.append("       )");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
