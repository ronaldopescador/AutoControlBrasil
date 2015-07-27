package br.com.autocontrolbrasil.autocontrolbrasil;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dao.apagarRegistro(id);
                onActivityResult(0, 0, null);
                return true;
            }
        };

        //ListView list = (ListView) findViewById(android.R.id.list);
        getListView().setOnItemLongClickListener(listener);
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
            novoAbastecimento(dao.ultimoAbastecimento().getKmAnterior());
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

    private void novoAbastecimento(Long kmAnterior){
        Intent i = new Intent(this, InclusaoAbastecimentoActivity.class);

        i.putExtra("KMANTERIOR", kmAnterior);

        startActivityForResult(i, 1);
    }
}
