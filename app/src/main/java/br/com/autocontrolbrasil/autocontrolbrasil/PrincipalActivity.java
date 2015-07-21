package br.com.autocontrolbrasil.autocontrolbrasil;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import br.com.autocontrolbrasil.autocontrolbrasil.adapter.ListaVeiculosAdapter;
import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;


public class PrincipalActivity extends ListActivity{

    private ListaVeiculosAdapter adapter;

    private VeiculoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        this.dao = new VeiculoDAO(this);

        Cursor cursor = this.dao.listar();

        this.adapter = new ListaVeiculosAdapter(this, cursor, 0);

        setListAdapter(adapter);

        //cursor.close();
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
        Cursor cursor = dao.listar();
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, CadastrarVeiculoActivity.class);
        i.putExtra("ID_VEICULO", new Long(id).intValue());

        startActivityForResult(i, 2);
    }

    private void cadastrarVeiculo(){
        Intent i = new Intent(this, CadastrarVeiculoActivity.class);
        startActivityForResult(i, 1);
    }

    private void abastecimentos(){
        Intent i = new Intent(this, ListaAbastecimentosActivity.class);
        startActivityForResult(i, 1);
    }

    public void novoVeiculo(View v){
        cadastrarVeiculo();
    }
}
