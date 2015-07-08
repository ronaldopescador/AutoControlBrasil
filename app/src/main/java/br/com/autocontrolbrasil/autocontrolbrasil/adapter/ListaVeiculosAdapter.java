package br.com.autocontrolbrasil.autocontrolbrasil.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.autocontrolbrasil.autocontrolbrasil.R;
import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;

/**
 * Created by Ronaldo on 05/07/2015.
 */
public class ListaVeiculosAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    private VeiculoDAO dao;


    public ListaVeiculosAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.dao = new VeiculoDAO(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View item = inflater.inflate(R.layout.layout_item_veiculo, null);

        return item;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblPlaca = (TextView) view.findViewById(R.id.lblPlaca);

        String placa = cursor.getString(cursor.getColumnIndex("placa"));
        lblPlaca.setText(placa);

    }
}
