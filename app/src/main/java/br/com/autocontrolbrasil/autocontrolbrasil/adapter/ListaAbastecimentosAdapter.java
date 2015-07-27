package br.com.autocontrolbrasil.autocontrolbrasil.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.autocontrolbrasil.autocontrolbrasil.R;
import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.AbastecimentoDAO;

/**
 * Created by Ronaldo on 20/07/2015.
 */
public class ListaAbastecimentosAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    private AbastecimentoDAO dao;

    public ListaAbastecimentosAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.dao = new AbastecimentoDAO(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View item = inflater.inflate(R.layout.layout_item_abastecimento, null);

        return item;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblKmAtual = (TextView) view.findViewById(R.id.lblKmAtual);
        TextView lblVolume = (TextView) view.findViewById(R.id.lblVolume);
        TextView lblKmMedia = (TextView) view.findViewById(R.id.lblKmMedia);
        TextView lblData = (TextView) view.findViewById(R.id.lblData);

        String kmAtual = cursor.getString(cursor.getColumnIndex("km_atual"));
        Double Volume = cursor.getDouble(cursor.getColumnIndex("volume"));
        Double KmMedia = cursor.getDouble(cursor.getColumnIndex("km_media"));

        Long timeData = cursor.getLong(cursor.getColumnIndex("data"));
        Date data = new Date(timeData);

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat formatVolume = new DecimalFormat("#0.000");
        DecimalFormat formatKmMedia = new DecimalFormat("#0.0");

        lblKmAtual.setText(kmAtual + " Km");
        lblVolume.setText(formatVolume.format(Volume) + " Litros");
        lblKmMedia.setText(formatKmMedia.format(KmMedia) + " Km/Litros" );
        lblData.setText(dtf.format(data));
    }
}
