package br.com.autocontrolbrasil.autocontrolbrasil.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.VeiculoVO;

/**
 * Created by Edir on 02/07/2015.
 */
public class VeiculoDAO extends BaseDAO  {
    private static String tabela = "veiculo";
    private static String id = "_id";
    private static String placa = "placa";
    private static String nome = "nome";
    private static String ano_modelo = "ano_modelo";
    private static String foto = "foto";

    public VeiculoDAO(Context context) {
        super(context);
    }

    public void salvar(VeiculoVO veiculo){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(placa, veiculo.getPlaca());
        valores.put(nome, veiculo.getNome());
        valores.put(ano_modelo, veiculo.getAno_modelo());
        valores.put(foto, veiculo.getFoto());

        db.insert(tabela, null, valores);


//        if (veiculo.getId() > 0) {
//            db.update(tabela, valores, id + " = ?", new String[]{ veiculo.getId().toString()});
//        } else {
//            db.insert(tabela, null, valores);
//        }

        db.close();
    }

    public VeiculoVO consultar(Integer idVeiculo){

        VeiculoVO veiculo = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(tabela, new String[]{id, nome, placa, ano_modelo, foto}, id + " = ?",
                new String[]{ idVeiculo.toString()}, null, null, null);

        if (cursor.moveToFirst()) {
            veiculo = new VeiculoVO();

            veiculo.setId( cursor.getInt( cursor.getColumnIndex(id)));
            veiculo.setNome( cursor.getString( cursor.getColumnIndex(nome)));
            veiculo.setPlaca( cursor.getString( cursor.getColumnIndex(placa)));
            veiculo.setAno_modelo( cursor.getInt( cursor.getColumnIndex(ano_modelo)));
            veiculo.setFoto( cursor.getString( cursor.getColumnIndex(foto)));
        }

        cursor.close();
        db.close();

        return veiculo;
    }

    public Cursor listar(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(tabela, new String[]{id, nome, placa, ano_modelo},
                null, null, null, null, null);

        return cursor;
    }
}
