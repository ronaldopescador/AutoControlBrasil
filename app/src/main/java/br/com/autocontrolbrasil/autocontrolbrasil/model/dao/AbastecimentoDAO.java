package br.com.autocontrolbrasil.autocontrolbrasil.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.AbastecimentoVO;

/**
 * Created by Ronaldo on 20/07/2015.
 */
public class AbastecimentoDAO extends BaseDAO {

    private static final String tabela = "abastecimento";
    private static final String id = "_id";
    private static final String km_anterior = "km_anterior";
    private static final String km_atual = "km_atual";
    private static final String volume = "volume";
    private static final String valor_total = "valor_total";
    private static final String km_media = "km_media";

    public AbastecimentoDAO(Context context) {
        super(context);
    }

    public void salvar(AbastecimentoVO abastecimento) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(km_anterior, abastecimento.getKmAnterior());
        valores.put(km_atual, abastecimento.getKmAtual());
        valores.put(volume, abastecimento.getVolume());
        valores.put(valor_total, abastecimento.getValorTotal());
        valores.put(km_media, abastecimento.getKmMedia());

        db.insert(tabela, null, valores);

        db.close();
    }

    public AbastecimentoVO ultimoAbastecimento() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(tabela, new String[]{"max(" + km_atual + ")"}, null, null, null, null, null);

        AbastecimentoVO abastecimento = new AbastecimentoVO();
        if (cursor.moveToFirst()) {
            abastecimento.setKmAnterior(cursor.getDouble(0));
        }

        return abastecimento;
    };

    public void apagarRegistro(long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("delete from " + tabela + " where " + this.id + "= " + id);
    }

    public Cursor listar() {
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(tabela, new String[]{id, km_anterior, km_atual, volume, valor_total, km_media}, null, null, null, null, null);
    }

}
