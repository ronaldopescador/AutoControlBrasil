package br.com.autocontrolbrasil.autocontrolbrasil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.autocontrolbrasil.autocontrolbrasil.R;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.PrecoVO;

/**
 * Created by Ronaldo on 23/07/2015.
 */
public class ListaPrecoAdapter extends ArrayAdapter<PrecoVO> {

    private List<PrecoVO> precos;
    private LayoutInflater inflater;

    public ListaPrecoAdapter(Context context, List<PrecoVO> objects) {

        super(context, R.layout.item_preco, objects);

        this.precos = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = inflater.inflate(R.layout.item_preco, null);

        PrecoVO preco = precos.get(position);

        TextView lblPosto = (TextView) item.findViewById(R.id.lblPosto);
        TextView lblPreco = (TextView) item.findViewById(R.id.lblPreco);

        lblPosto.setText(preco.getNome());
        DecimalFormat df = new DecimalFormat("#0.00");
        lblPreco.setText(df.format(preco.getGasolina()));

        return item;
    }

}
