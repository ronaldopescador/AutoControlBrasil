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
    private static final String troca_oleo_filtro_previsao = "troca_oleo_filtro_previsao";
    private static final String troca_oleo_filtro_anterior = "troca_oleo_filtro_anterior";
    private static final String troca_pneu_freio_previsao = "troca_pneu_freio_previsao";
    private static final String troca_pneu_freio_anterior = "troca_pneu_freio_anterior";
    private static final String revisao_geral_previsao = "revisao_geral_previsao";
    private static final String revisao_geral_anterior = "revisao_geral_anterior";

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
        valores.put(troca_oleo_filtro_previsao, veiculo.getTroca_oleo_filtro_previsao());
        valores.put(troca_oleo_filtro_anterior, veiculo.getTroca_oleo_filtro_anterior());
        valores.put(troca_pneu_freio_previsao, veiculo.getTroca_pneu_freio_previsao());
        valores.put(troca_pneu_freio_anterior, veiculo.getTroca_pneu_freio_anterior());
        valores.put(revisao_geral_previsao, veiculo.getRevisao_geral_previsao());
        valores.put(revisao_geral_anterior, veiculo.getRevisao_geral_anterior());

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
                        troca_oleo_filtro_previsao, troca_oleo_filtro_anterior,
                        troca_pneu_freio_previsao, troca_pneu_freio_anterior,
                        revisao_geral_previsao, revisao_geral_anterior}, id + " = ?",
                new String[]{ idVeiculo.toString()}, null, null, null);

        if (cursor.moveToFirst()) {
            veiculo = new VeiculoVO();

            veiculo.setId( cursor.getInt( cursor.getColumnIndex(id)));
            veiculo.setNome( cursor.getString( cursor.getColumnIndex(nome)));
            veiculo.setPlaca( cursor.getString( cursor.getColumnIndex(placa)));
            veiculo.setAno_modelo( cursor.getInt( cursor.getColumnIndex(ano_modelo)));
            veiculo.setFoto( cursor.getBlob( cursor.getColumnIndex(foto)));
            veiculo.setTroca_oleo_filtro_previsao( cursor.getInt( cursor.getColumnIndex(troca_oleo_filtro_previsao)));
            veiculo.setTroca_oleo_filtro_anterior( cursor.getInt( cursor.getColumnIndex(troca_oleo_filtro_anterior)));
            veiculo.setTroca_pneu_freio_previsao( cursor.getInt( cursor.getColumnIndex(troca_pneu_freio_previsao)));
            veiculo.setTroca_pneu_freio_anterior( cursor.getInt( cursor.getColumnIndex(troca_pneu_freio_anterior)));
            veiculo.setRevisao_geral_previsao( cursor.getInt( cursor.getColumnIndex(revisao_geral_previsao)));
            veiculo.setRevisao_geral_anterior( cursor.getInt( cursor.getColumnIndex(revisao_geral_anterior)));
        }

        cursor.close();
        db.close();

        return veiculo;
    }

    public Cursor listar(){
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(tabela, new String[]{id, nome, placa, ano_modelo,
                troca_oleo_filtro_previsao, troca_oleo_filtro_anterior,
                troca_pneu_freio_previsao, troca_pneu_freio_anterior,
                revisao_geral_previsao, revisao_geral_anterior},
                null, null, null, null, null);
    }
}
