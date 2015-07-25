package br.com.autocontrolbrasil.autocontrolbrasil;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.AbastecimentoDAO;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.AbastecimentoVO;

public class InclusaoAbastecimentoActivity extends AppCompatActivity {

    AbastecimentoVO abastecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inclusao_abastecimento);

        Intent i = getIntent();
        abastecimento = new AbastecimentoVO();
        abastecimento.setKmAnterior(i.getDoubleExtra("KMANTERIOR", 0));
        abastecimento.setKmAtual(new Double(0));
        abastecimento.setVolume(new Double(0));

        final EditText txtMedia = (EditText) findViewById(R.id.txtMedia);
        final EditText txtVolume = (EditText) findViewById(R.id.txtVolume);
        final EditText txtKmAtual = (EditText) findViewById(R.id.txtKmAtual);
        final EditText txtKmAnterior = (EditText) findViewById(R.id.txtKmAnterior);

        txtKmAnterior.setText(abastecimento.getKmAnterior().toString());

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (txtKmAnterior.getText().length() != 0) {
                    abastecimento.setKmAnterior(Double.parseDouble(txtKmAnterior.getText().toString()));
                }
                if (txtKmAtual.getText().length() != 0) {
                    abastecimento.setKmAtual(Double.parseDouble(txtKmAtual.getText().toString()));
                }
                if (txtVolume.getText().length() != 0) {
                    abastecimento.setVolume(Double.parseDouble(txtVolume.getText().toString()));
                }

                NumberFormat format = new DecimalFormat("#.#");
                txtMedia.setText(format.format(abastecimento.getKmMedia()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        txtVolume.addTextChangedListener(watcher);
        txtKmAtual.addTextChangedListener(watcher);
        txtKmAnterior.addTextChangedListener(watcher);
    }

    public void salvar(View v) {
        EditText txtValorTotal = (EditText) findViewById(R.id.txtValorTotal);

        if (txtValorTotal.getText().length() != 0) {
            abastecimento.setValorTotal(Double.parseDouble(txtValorTotal.getText().toString()));
        }

        AbastecimentoDAO dao = new AbastecimentoDAO(this);

        dao.salvar(abastecimento);

        setResult(RESULT_OK);

        finish();
    }

}
