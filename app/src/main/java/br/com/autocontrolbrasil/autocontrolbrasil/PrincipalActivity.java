package br.com.autocontrolbrasil.autocontrolbrasil;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import br.com.autocontrolbrasil.autocontrolbrasil.adapter.ListaVeiculosAdapter;
import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.VeiculoVO;


public class PrincipalActivity extends AppCompatActivity{
    private static final Integer requestCodeSelecao = 100;
    private static final Integer requestCodeVeiculo = 101;
    private static final Integer requestCodeAbastecimento = 102;
//    private ListaVeiculosAdapter adapter;
    private VeiculoDAO dao;
    private VeiculoVO veiculo = null;

    private static EditText txtVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        carregarCampos();

        selecionarVeiculoVO(true);
    }

    @Override
    protected void onResume() {
        carregarVeiculo();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cadastrar_veiculo) {
            cadastrarVeiculo();
            return true;
        } else if (id == R.id.action_abastecimentos){
            abastecimentos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == requestCodeSelecao) && (resultCode > 0)) {
            abrirVeiculoVO(resultCode);
        }
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        Intent i = new Intent(this, CadastrarVeiculoActivity.class);
//        i.putExtra("ID_VEICULO", new Long(id).intValue());

//        startActivityForResult(i, 2);
//    }

    private void cadastrarVeiculo(){
        Intent i = new Intent(this, CadastrarVeiculoActivity.class);
        startActivityForResult(i, requestCodeVeiculo);
    }

    private void abastecimentos(){
        Intent i = new Intent(this, ListaAbastecimentosActivity.class);
        startActivityForResult(i, requestCodeAbastecimento);
    }

/*    public void novoVeiculo(View v){
        cadastrarVeiculo();
    }
*/
    private void selecionarVeiculoVO(Boolean autoSelecao){
        Intent i = new Intent(this, SelecionarVeiculoActivity.class);

        i.putExtra("AUTO_SELECAO", autoSelecao);

        startActivityForResult(i, requestCodeSelecao);
}

    private void abrirVeiculoVO(Integer idVeiculo){
        this.dao = new VeiculoDAO(this);
        veiculo = dao.consultar(idVeiculo);
    }

    public void selecionarVeiculo(View v){
        selecionarVeiculoVO(false);
    }

    private void carregarCampos(){
        txtVeiculo = (EditText) findViewById(R.id.txtVeiculo);
    }

    private void carregarVeiculo(){
        if (veiculo != null){
            txtVeiculo.setText( veiculo.getNome());
        }
    }
}
