package br.com.autocontrolbrasil.autocontrolbrasil;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.AbastecimentoDAO;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.AbastecimentoVO;


public class InclusaoAbastecimentoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusao_abastecimento);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inclusao_abastecimento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void salvar(View v) {
        EditText txtKmAtual = (EditText) findViewById(R.id.txtKmAtual);

        AbastecimentoVO abastecimento = new AbastecimentoVO();

        abastecimento.setKmAtual(Double.parseDouble(txtKmAtual.getText().toString()));

        AbastecimentoDAO dao = new AbastecimentoDAO(this);

        dao.salvar(abastecimento);

        setResult(RESULT_OK);

        finish();
    }
}
