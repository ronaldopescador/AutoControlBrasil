package br.com.autocontrolbrasil.autocontrolbrasil;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.autocontrolbrasil.autocontrolbrasil.adapter.ListaPrecoAdapter;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.PrecoVO;
import br.com.autocontrolbrasil.autocontrolbrasil.task.ConsultaPrecoTask;

public class ListaPrecosActivity extends ListActivity {

    private List<PrecoVO> precos;

    private PrecoVO preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_precos);
        executarTask();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, MapaPostoActivity.class);

        preco = precos.get(new Long(id).intValue());

        i.putExtra("PRECO", preco);

        startActivity(i);

    }

    private void executarTask() {
        ConsultaPrecoTask task = new ConsultaPrecoTask(this);

        task.execute("");
    }

    public void receberPreco(List<PrecoVO> precos) {
        this.precos = precos;

        ListaPrecoAdapter adapter = new ListaPrecoAdapter(this, precos);
        setListAdapter(adapter);

    }

}
