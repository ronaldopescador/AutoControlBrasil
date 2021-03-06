package br.com.autocontrolbrasil.autocontrolbrasil.task;

import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.autocontrolbrasil.autocontrolbrasil.ListaPrecosActivity;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.PrecoVO;

/**
 * Created by Ronaldo on 23/07/2015.
 */
public class ConsultaPrecoTask extends AsyncTask<Location, Void, List<PrecoVO> > {
//Location location
    private ListaPrecosActivity activity;

    public ConsultaPrecoTask(ListaPrecosActivity activity){
        this.activity = activity;
    }

    @Override
    protected List<PrecoVO> doInBackground(Location... params) {

        List<PrecoVO> precos = new ArrayList<PrecoVO>();

        Double swlat;
        Double swlng;
        Double nelat;
        Double nelng;
        String pos;

        try{
            swlat = (params[0].getLatitude()) - 0.1;
            swlng = (params[0].getLongitude()) - 0.1;
            nelat = (params[0].getLatitude()) + 0.1;
            nelng = (params[0].getLongitude()) + 0.1;
            pos = "swlat=" + swlat.toString() +
                  "&swlng=" + swlng.toString() +
                  "&nelat=" + nelat.toString() +
                  "&nelng=" + nelng.toString();

            //URL ws = new URL("http://www.precodoscombustiveis.com.br/mapa/atualiza?swlat=-27.139131250167814&swlng=-52.6622192331543&nelat=-27.062724314030632&nelng=-52.56917876684571&zoom=13");
            URL ws = new URL("http://www.precodoscombustiveis.com.br/mapa/atualiza?" + pos + "&zoom=13");

            HttpURLConnection con  = (HttpURLConnection) ws.openConnection();

            InputStream is = con.getInputStream();

            Gson gson = new Gson();
            //create a new JSON reader from the response input stream
            JsonReader jsonReader = new JsonReader(new InputStreamReader(is));
            //begin parsing
            jsonReader.beginObject();
            //stay in loop as long as there are more data elements
            while (jsonReader.hasNext()) {
                //get the element name
                String name = jsonReader.nextName();
                PrecoVO preco = gson.fromJson(jsonReader, PrecoVO.class);
                precos.add(preco);
            }
            //end reader and close the stream
            jsonReader.endObject();
            jsonReader.close();

            con.disconnect();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return precos;
    }

    @Override
    protected void onPostExecute(List<PrecoVO> precos) {
        this.activity.receberPreco(precos);
    }

}