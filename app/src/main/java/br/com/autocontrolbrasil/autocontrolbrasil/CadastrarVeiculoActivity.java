package br.com.autocontrolbrasil.autocontrolbrasil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Calendar;

import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.VeiculoVO;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.FileUtilities;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.ImageUtilities;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.NumberUtilities;

public class CadastrarVeiculoActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private static final Integer anoBase = 1886;
    private static final Integer codigoRequisicao = 9876;
    private static EditText txtPlaca;
    private static EditText txtVeiculo;
    private static ImageView imgFoto;
    private static EditText txtAno;
    private static SeekBar skbAno;
    private static EditText txtTrocaOleo;
    private static SeekBar skbTrocaOleo;
    private static EditText txtTrocaPneu;
    private static SeekBar skbTrocaPneu;
    private static EditText txtRevisaoGeral;
    private static SeekBar skbRevisaoGeral;


    private VeiculoDAO dao = new VeiculoDAO(this);

    private Uri uriFoto;

    private VeiculoVO veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_veiculo);

        txtVeiculo = (EditText) findViewById(R.id.txtVeiculo);
        txtPlaca = (EditText) findViewById(R.id.txtPlaca);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        txtAno = (EditText) findViewById(R.id.txtAno);
        skbAno = (SeekBar) findViewById(R.id.skbAno);
        txtTrocaOleo = (EditText) findViewById(R.id.txtTrocaOleo);
        skbTrocaOleo = (SeekBar) findViewById(R.id.skbTrocaOleo);
        txtTrocaPneu = (EditText) findViewById(R.id.txtTrocaPneu);
        skbTrocaPneu = (SeekBar) findViewById(R.id.skbTrocaPneu);
        txtRevisaoGeral = (EditText) findViewById(R.id.txtRevisaoGeral);
        skbRevisaoGeral = (SeekBar) findViewById(R.id.skbRevisaoGeral);

        skbAno.setOnSeekBarChangeListener(this);
        skbTrocaOleo.setOnSeekBarChangeListener(this);
        skbTrocaPneu.setOnSeekBarChangeListener(this);
        skbRevisaoGeral.setOnSeekBarChangeListener(this);

        skbAno.setMax(200);
        skbTrocaOleo.setMax(100);
        skbTrocaPneu.setMax(100);
        skbRevisaoGeral.setMax(100);

        Intent i = getIntent();

        Integer idVeiculo = i.getIntExtra("ID_VEICULO", 0);

        if (idVeiculo > 0) {
            this.veiculo = dao.consultar(idVeiculo);
        } else {
            this.veiculo = new VeiculoVO();
        }
        atualizarTela();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_veiculo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_novo_veiculo) {
            veiculo = new VeiculoVO();

            atualizarTela();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == codigoRequisicao){

            ImageView imagem = (ImageView) findViewById(R.id.imgFoto);
            imagem.setImageURI(uriFoto);
        }
    }

    private Uri obterCaminhoNovaFoto(){
        return Uri.fromFile(FileUtilities.getNomeArquivoImagem());
    }

    public void salvar(View v) {
        veiculo.setPlaca( txtPlaca.getText().toString());
        veiculo.setNome( txtVeiculo.getText().toString());
        veiculo.setAno_modelo( NumberUtilities.parseInt( txtAno.getText().toString()));
        veiculo.setFoto( ImageUtilities.imageViewToByte( imgFoto));
        veiculo.setTroca_oleo_filtro( NumberUtilities.parseInt( txtTrocaOleo.getText().toString()));
        veiculo.setTroca_pneu_freio( NumberUtilities.parseInt( txtTrocaPneu.getText().toString()));
        veiculo.setRevisao_geral( NumberUtilities.parseInt( txtRevisaoGeral.getText().toString()));

        dao.salvar(veiculo);

        setResult(RESULT_OK);

        finish();
    }

    private void atualizarTela(){
        if (veiculo.getId() == null) {
            txtVeiculo.setText("");
            txtPlaca.setText("");
            imgFoto.setImageBitmap(null);
            imgFoto.setBackgroundColor(getTitleColor());
            txtTrocaOleo.setText("5");
            skbTrocaOleo.setProgress(5);
            txtTrocaPneu.setText("10");
            skbTrocaPneu.setProgress(10);
            txtRevisaoGeral.setText("30");
            skbRevisaoGeral.setProgress(30);
            Integer sugestaoAno = Calendar.getInstance().get(Calendar.YEAR) - anoBase;
            txtAno.setText(sugestaoAno.toString());
            skbAno.setProgress(sugestaoAno);
        } else {
            txtVeiculo.setText(veiculo.getNome());
            txtPlaca.setText(veiculo.getPlaca());
            imgFoto.setImageBitmap(ImageUtilities.byteToBitmap(veiculo.getFoto()));
            txtAno.setText(veiculo.getAno_modelo().toString());
            skbAno.setProgress(veiculo.getAno_modelo() - anoBase);
            txtTrocaOleo.setText(veiculo.getTroca_oleo_filtro().toString());
            skbTrocaOleo.setProgress(veiculo.getTroca_oleo_filtro());
            txtTrocaPneu.setText(veiculo.getTroca_pneu_freio().toString());
            skbTrocaPneu.setProgress(veiculo.getTroca_pneu_freio());
            txtRevisaoGeral.setText(veiculo.getRevisao_geral().toString());
            skbRevisaoGeral.setProgress(veiculo.getRevisao_geral());
        }
    }

    public void novaFoto(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        uriFoto = obterCaminhoNovaFoto();

        i.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);

        startActivityForResult(i, codigoRequisicao);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Integer valor = progress;

        if (seekBar.getId() == skbAno.getId()) {
            valor = anoBase + valor;
            txtAno.setText(valor.toString());
        } else if (seekBar.getId() == skbTrocaOleo.getId()) {
            txtTrocaOleo.setText(valor.toString());
        } else if (seekBar.getId() == skbTrocaPneu.getId()) {
            txtTrocaPneu.setText(valor.toString());
        } else if (seekBar.getId() == skbRevisaoGeral.getId()) {
            txtRevisaoGeral.setText(valor.toString());
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Integer valor = seekBar.getProgress();

        if (seekBar.getId() == skbAno.getId()) {
            valor = anoBase + valor;
            txtAno.setText(valor.toString());
        } else if (seekBar.getId() == skbTrocaOleo.getId()) {
            txtTrocaOleo.setText(valor.toString());
        } else if (seekBar.getId() == skbTrocaPneu.getId()) {
            txtTrocaPneu.setText(valor.toString());
        } else if (seekBar.getId() == skbRevisaoGeral.getId()) {
            txtRevisaoGeral.setText(valor.toString());
        }
    }
}

