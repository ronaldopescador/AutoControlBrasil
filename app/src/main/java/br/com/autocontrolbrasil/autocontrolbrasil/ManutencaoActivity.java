package br.com.autocontrolbrasil.autocontrolbrasil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.VeiculoVO;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.NumberUtilities;


public class ManutencaoActivity extends AppCompatActivity {
    private VeiculoDAO dao  = new VeiculoDAO(this);
    private VeiculoVO veiculo;

    private static EditText txtVeiculo;
    private static EditText txtUltimaTrocaOleo;
    private static EditText txtProximaTrocaOleo;
    private static EditText txtUltimaTrocaPneu;
    private static EditText txtProximaTrocaPneu;
    private static EditText txtUltimaRevisaoGeral;
    private static EditText txtProximaRevisaoGeral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manutencao);

        txtVeiculo = (EditText) findViewById( R.id.txtVeiculo);
        txtUltimaTrocaOleo = (EditText) findViewById( R.id.txtUltimaTrocaOleo);
        txtProximaTrocaOleo = (EditText) findViewById( R.id.txtProximaTrocaOleo);
        txtUltimaTrocaPneu = (EditText) findViewById( R.id.txtUltimaTrocaPneu);
        txtProximaTrocaPneu = (EditText) findViewById( R.id.txtProximaTrocaPneu);
        txtUltimaRevisaoGeral = (EditText) findViewById( R.id.txtUltimaRevisaoGeral);
        txtProximaRevisaoGeral = (EditText) findViewById( R.id.txtProximaRevisaoGeral);

        Intent i = getIntent();

        Integer idVeiculo = i.getIntExtra("ID_VEICULO", 0);

        carregarVeiculo(idVeiculo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manutencao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregarVeiculo(Integer idVeiculo){
        if (idVeiculo > 0) {
            veiculo = dao.consultar(idVeiculo);

            txtVeiculo.setText( veiculo.getNome());

            txtUltimaTrocaOleo.setText( veiculo.getTroca_oleo_filtro_anterior().toString() );
            Integer valorTrocaOleo = veiculo.getTroca_oleo_filtro_anterior() + veiculo.getTroca_oleo_filtro_previsao() * 1000;
            txtProximaTrocaOleo.setText( valorTrocaOleo.toString() );

            txtUltimaTrocaPneu.setText( veiculo.getTroca_pneu_freio_anterior().toString() );
            Integer valorTrocaPneu = veiculo.getTroca_pneu_freio_anterior() + veiculo.getTroca_pneu_freio_previsao() * 1000;
            txtProximaTrocaPneu.setText( valorTrocaPneu.toString() );

            txtUltimaRevisaoGeral.setText( veiculo.getRevisao_geral_anterior().toString() );
            Integer valorRevisaoGeral = veiculo.getRevisao_geral_anterior() + veiculo.getRevisao_geral_previsao() * 1000;
            txtProximaRevisaoGeral.setText( valorRevisaoGeral.toString() );
        } else {
            finish();
        }
    }
    private void salvar(){
        veiculo.setTroca_oleo_filtro_anterior( NumberUtilities.parseInt(txtUltimaTrocaOleo.getText().toString()));
        veiculo.setTroca_pneu_freio_anterior( NumberUtilities.parseInt(txtUltimaTrocaPneu.getText().toString()));
        veiculo.setRevisao_geral_anterior(NumberUtilities.parseInt(txtUltimaRevisaoGeral.getText().toString()));

        dao.salvar(veiculo);

        setResult(veiculo.getId());

        finish();
    }

    public void salvarManutencao(View v){
        salvar();
    }
/*
    public void atualizarTela(View v){
        Integer valorTrocaOleo = veiculo.getTroca_oleo_filtro_previsao() * 1000 + NumberUtilities.parseInt( txtUltimaTrocaOleo.getText().toString());
        txtProximaTrocaOleo.setText( valorTrocaOleo.toString() );

        Integer valorTrocaPneu = veiculo.getTroca_pneu_freio_previsao()  * 1000 + NumberUtilities.parseInt( txtUltimaTrocaPneu.getText().toString());
        txtProximaTrocaPneu.setText( valorTrocaPneu.toString() );

        Integer valorRevisaoGeral = veiculo.getRevisao_geral_previsao()  * 1000 + NumberUtilities.parseInt( txtUltimaRevisaoGeral.getText().toString());
        txtProximaRevisaoGeral.setText( valorRevisaoGeral.toString() );
    }
*/
}
