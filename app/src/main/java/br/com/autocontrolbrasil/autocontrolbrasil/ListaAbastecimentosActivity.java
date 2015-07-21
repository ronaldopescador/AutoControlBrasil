package br.com.autocontrolbrasil.autocontrolbrasil;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.autocontrolbrasil.autocontrolbrasil.R;
import br.com.autocontrolbrasil.autocontrolbrasil.adapter.ListaAbastecimentosAdapter;
import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.AbastecimentoDAO;

public class ListaAbastecimentosActivity extends ListActivity {

    private ListaAbastecimentosAdapter adapter;

    private AbastecimentoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_abastecimentos);

        this.dao = new AbastecimentoDAO(this);

        Cursor cursor = this.dao.listar();

        this.adapter = new ListaAbastecimentosAdapter(this, cursor, 0);

        setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_abastecimentos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_novo_abastecimento) {
            novoAbastecimento();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

    private void novoAbastecimento(){
        Intent i = new Intent(this, InclusaoAbastecimentoActivity.class);
        startActivityForResult(i, 1);
    }

}
