package br.com.autocontrolbrasil.autocontrolbrasil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.VeiculoVO;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.DateUtilities;


public class PrincipalActivity extends AppCompatActivity{
    private static final Integer requestCodeSelecao = 100;
    private static final Integer requestCodeVeiculo = 101;
    private static final Integer requestCodeAbastecimento = 102;
    private static final Integer requestCodeManutencao = 103;
    private VeiculoDAO dao;
    private VeiculoVO veiculo = null;

    private static EditText txtVeiculo;
    private static EditText txtKmAbastecimento;
    private static EditText txtDataAbastecimento;
    private static EditText txtKmTrocaFreio;
    private static EditText txtKmTrocaPneu;
    private static EditText txtKmRevisaoGeral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        carregarCampos();

        selecionarVeiculoVO(true);
    }

    @Override
    protected void onResume() {
        carregarVeiculo();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cadastrar_veiculo) {
            veiculos();
            return true;
        } else if (id == R.id.action_abastecimentos){
            abastecimentos();
            return true;
        } else if (id == R.id.action_manutencao){
            manutencao();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == requestCodeSelecao) && (resultCode > 0)) {
            abrirVeiculoVO(resultCode);
        } else if (requestCode == requestCodeManutencao) {
            abrirVeiculoVO(veiculo.getId());
        } else if (requestCode == requestCodeVeiculo) {
            abrirVeiculoVO(veiculo.getId());
        } else if (requestCode == requestCodeAbastecimento) {
            abrirVeiculoVO(veiculo.getId());
        }
    }

    private void veiculos(){
        Intent i = new Intent(this, CadastrarVeiculoActivity.class);
        i.putExtra("ID_VEICULO", veiculo.getId());
        startActivityForResult(i, requestCodeVeiculo);
    }

    private void abastecimentos(){
        Intent i = new Intent(this, ListaAbastecimentosActivity.class);
        startActivityForResult(i, requestCodeAbastecimento);
    }

    private void manutencao(){
        Intent i = new Intent(this, ManutencaoActivity.class);
        i.putExtra("ID_VEICULO", veiculo.getId());
        startActivityForResult(i, requestCodeManutencao);
    }

    private void selecionarVeiculoVO(Boolean autoSelecao){
        Intent i = new Intent(this, SelecionarVeiculoActivity.class);

        i.putExtra("AUTO_SELECAO", autoSelecao);

        startActivityForResult(i, requestCodeSelecao);
}

    private void abrirVeiculoVO(Integer idVeiculo){
        this.dao = new VeiculoDAO(this);
        veiculo = dao.consultar(idVeiculo);
    }

    private void carregarCampos(){
        txtVeiculo = (EditText) findViewById(R.id.txtVeiculo);
        txtKmAbastecimento = (EditText) findViewById(R.id.txtKmAbastecimento);
        txtDataAbastecimento = (EditText) findViewById(R.id.txtDataAbastecimento);
        txtKmTrocaFreio = (EditText) findViewById(R.id.txtKmTrocaFreio);
        txtKmTrocaPneu = (EditText) findViewById(R.id.txtKmTrocaPneu);
        txtKmRevisaoGeral = (EditText) findViewById(R.id.txtKmRevisaoGeral);
    }

    private void carregarVeiculo() {
        String corVermelho = "#ffff0000";
        String corAmarelo = "#FFFAFF5F";
        Integer milKm = 1000;
        Integer KmAlertaManutencao = 0;
        Integer KmLembreteManutencao = 1000;
        String msgAlertaTrocaOleo = "Troca de óleo ou filtro pendente!";
        String msgAlertaTrocaPneu = "Troca de pneu, freio ou suspenção pendente!";
        String msgAlertaRevisaoGeral = "Revisão geral pendente!";
        String msgLembreteTrocaOleo = "Programe a troca de óleo ou filtro!";
        String msgLembreteTrocaPneu = "Programe a troca de pneu, freio ou suspenção!";
        String msgLembreteRevisaoGeral = "Programe a revisão geral!";

        if (veiculo != null){
            txtVeiculo.setText( veiculo.getNome());

            Integer valorKmAbastecimento = 49500; // falta carregar.
            txtKmAbastecimento.setText( valorKmAbastecimento.toString() );

            txtDataAbastecimento.setText(DateUtilities.getDataAtualFormatada()); // falta carregar a data do abastecimento.

            Integer valorTrocaFreio = veiculo.getTroca_oleo_filtro_anterior() + veiculo.getTroca_oleo_filtro_previsao() * milKm;
            txtKmTrocaFreio.setText( valorTrocaFreio.toString() );

            if (valorKmAbastecimento + KmAlertaManutencao >= valorTrocaFreio){
                txtKmTrocaFreio.setBackgroundColor( Color.parseColor(corVermelho));
            } else if (valorKmAbastecimento + KmLembreteManutencao >= valorTrocaFreio) {
                txtKmTrocaFreio.setBackgroundColor(Color.parseColor(corAmarelo));
            } else {
                txtKmTrocaFreio.setBackgroundColor(Color.TRANSPARENT);
            }

            Integer valorTrocaPneu = veiculo.getTroca_oleo_filtro_anterior() + veiculo.getTroca_pneu_freio_previsao() * milKm;
            txtKmTrocaPneu.setText( valorTrocaPneu.toString() );

            if (valorKmAbastecimento + KmAlertaManutencao >= valorTrocaPneu){
                txtKmTrocaPneu.setBackgroundColor( Color.parseColor(corVermelho));
            } else if (valorKmAbastecimento + KmLembreteManutencao >= valorTrocaPneu) {
                txtKmTrocaPneu.setBackgroundColor(Color.parseColor(corAmarelo));
            }else {
                txtKmTrocaPneu.setBackgroundColor(Color.TRANSPARENT);
            }

            Integer valorRevisaoGeral = veiculo.getRevisao_geral_anterior() + veiculo.getRevisao_geral_previsao() * milKm;
            txtKmRevisaoGeral.setText( valorRevisaoGeral.toString() );

            if (valorKmAbastecimento + KmAlertaManutencao >= valorRevisaoGeral){
                txtKmRevisaoGeral.setBackgroundColor( Color.parseColor(corVermelho));
            } else if (valorKmAbastecimento + KmLembreteManutencao >= valorRevisaoGeral) {
                txtKmRevisaoGeral.setBackgroundColor(Color.parseColor(corAmarelo));
            }else {
                txtKmRevisaoGeral.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

}
