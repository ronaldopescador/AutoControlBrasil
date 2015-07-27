package br.com.autocontrolbrasil.autocontrolbrasil;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import br.com.autocontrolbrasil.autocontrolbrasil.adapter.ListaPrecoAdapter;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.PrecoVO;
import br.com.autocontrolbrasil.autocontrolbrasil.task.ConsultaPrecoTask;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.MyLocation;

public class ListaPrecosActivity extends ListActivity {

    private List<PrecoVO> precos;

    private PrecoVO preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_precos);

        MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){
                //Usar a localizacao aqui!
                executarTask(location);
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, MapaPostoActivity.class);

        preco = precos.get(new Long(id).intValue());

        i.putExtra("PRECO", preco);

        startActivity(i);

    }

    private void executarTask(Location location) {
        ConsultaPrecoTask task = new ConsultaPrecoTask(this);
        task.execute(location);
    }

    public void receberPreco(List<PrecoVO> precos) {
        this.precos = precos;

        ListaPrecoAdapter adapter = new ListaPrecoAdapter(this, precos);
        setListAdapter(adapter);

    }

}
