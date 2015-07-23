package br.com.autocontrolbrasil.autocontrolbrasil;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import br.com.autocontrolbrasil.autocontrolbrasil.adapter.ListaVeiculosAdapter;
import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;


public class SelecionarVeiculoActivity extends ListActivity {
    private static final Integer requestCodeCadastroVeiculo = 150;
    private ListaVeiculosAdapter adapter;
    private VeiculoDAO dao;
    private Cursor cursor;
    private Boolean autoSelecao;

    private void abrirListaVeiculos(){
        dao = new VeiculoDAO(this);

        cursor = dao.listar();

        adapter = new ListaVeiculosAdapter(this, cursor, 0);

        setListAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_veiculo);

        Intent i = getIntent();

        autoSelecao = i.getBooleanExtra("AUTO_SELECAO", false);

        abrirListaVeiculos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoSelecao) {
            if (!cursor.moveToFirst()) {
                cadastrarVeiculo();
            } else if (!cursor.moveToNext()) {
                cursor.moveToFirst();
                Integer idVeiculo = cursor.getInt( cursor.getColumnIndex("_id"));
                finalizarSelecao(idVeiculo);
            }
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Long novoId = id;
        Integer idVeiculo = novoId.intValue();
        finalizarSelecao(idVeiculo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == requestCodeCadastroVeiculo) && (resultCode == RESULT_OK)){
            abrirListaVeiculos();
        }
    }

    private void cadastrarVeiculo(){
        Intent i = new Intent(this, CadastrarVeiculoActivity.class);
        startActivityForResult(i, requestCodeCadastroVeiculo);
    }

    public void novoVeiculo(View v){
        cadastrarVeiculo();
    }

    private void finalizarSelecao(Integer idVeiculo){
        setResult(idVeiculo);
        finish();
    }
}
