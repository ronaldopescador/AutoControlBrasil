package br.com.autocontrolbrasil.autocontrolbrasil.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.VeiculoVO;

public class VeiculoDAO extends BaseDAO  {
    private static final String tabela = "veiculo";
    private static final String id = "_id";
    private static final String placa = "placa";
    private static final String nome = "nome";
    private static final String ano_modelo = "ano_modelo";
    private static final String foto = "foto";
    private static final String troca_oleo_filtro = "troca_oleo_filtro";
    private static final String troca_pneu_freio = "troca_pneu_freio";
    private static final String revisao_geral = "revisao_geral";

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
        valores.put(troca_oleo_filtro, veiculo.getTroca_oleo_filtro());
        valores.put(troca_pneu_freio, veiculo.getTroca_pneu_freio());
        valores.put(revisao_geral, veiculo.getRevisao_geral());

        if (veiculo.getId() == null) {
            db.insert(tabela, null, valores);
        } else {
            db.update(tabela, valores, id + " = ?", new String[]{ veiculo.getId().toString()});
        }

        db.close();
    }

    public VeiculoVO consultar(Integer idVeiculo){

        VeiculoVO veiculo = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(tabela, new String[]{id, nome, placa, ano_modelo, foto,
                        troca_oleo_filtro, troca_pneu_freio, revisao_geral}, id + " = ?",
                new String[]{ idVeiculo.toString()}, null, null, null);

        if (cursor.moveToFirst()) {
            veiculo = new VeiculoVO();

            veiculo.setId(cursor.getInt(cursor.getColumnIndex(id)));
            veiculo.setNome(cursor.getString(cursor.getColumnIndex(nome)));
            veiculo.setPlaca(cursor.getString(cursor.getColumnIndex(placa)));
            veiculo.setAno_modelo(cursor.getInt(cursor.getColumnIndex(ano_modelo)));
            veiculo.setFoto(cursor.getBlob(cursor.getColumnIndex(foto)));
            veiculo.setTroca_oleo_filtro(cursor.getInt(cursor.getColumnIndex(troca_oleo_filtro)));
            veiculo.setTroca_pneu_freio(cursor.getInt(cursor.getColumnIndex(troca_pneu_freio)));
            veiculo.setRevisao_geral(cursor.getInt(cursor.getColumnIndex(revisao_geral)));
        }

        cursor.close();
        db.close();

        return veiculo;
    }

    public Cursor listar(){
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(tabela, new String[]{id, nome, placa, ano_modelo, troca_oleo_filtro, troca_pneu_freio, revisao_geral}, null, null, null, null, null);
    }
}
