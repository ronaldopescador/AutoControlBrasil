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
import android.widget.TextView;

import br.com.autocontrolbrasil.autocontrolbrasil.model.dao.VeiculoDAO;
import br.com.autocontrolbrasil.autocontrolbrasil.model.vo.VeiculoVO;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.FileUtilities;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.ImageUtilities;
import br.com.autocontrolbrasil.autocontrolbrasil.utilities.NumberUtilities;

public class CadastrarVeiculoActivity extends AppCompatActivity {
    private static Integer codigoRequisicao = 9876;

    private VeiculoDAO dao = new VeiculoDAO(this);

    private Uri uriFoto;

    private VeiculoVO veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_veiculo);

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
            this.veiculo = new VeiculoVO();

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
        EditText txtPlaca = (EditText) findViewById(R.id.txtPlaca);
        EditText txtVeiculo = (EditText) findViewById(R.id.txtVeiculo);
        EditText txtAno = (EditText) findViewById(R.id.txtAno);
        ImageView imgFoto = (ImageView) findViewById(R.id.imgFoto);

        this.veiculo.setPlaca(txtPlaca.getText().toString());
        this.veiculo.setNome(txtVeiculo.getText().toString());
        this.veiculo.setAno_modelo(NumberUtilities.parseInt(txtAno.getText().toString()));
        this.veiculo.setFoto(ImageUtilities.imageViewToByte(imgFoto));

        dao.salvar(this.veiculo);

        setResult(RESULT_OK);

        finish();
    }

    private void atualizarTela(){
        TextView txtVeiculo = (TextView) findViewById( R.id.txtVeiculo);
        TextView txtPlaca = (TextView) findViewById( R.id.txtPlaca);
        TextView txtAno = (TextView) findViewById( R.id.txtAno);
        ImageView imgFoto = (ImageView) findViewById( R.id.imgFoto);

        if (veiculo.getId() == null) {
            txtVeiculo.setText("");
            txtPlaca.setText("");
            txtAno.setText("");
            imgFoto.setImageBitmap(null);
        } else {
            txtVeiculo.setText(this.veiculo.getNome());
            txtPlaca.setText(this.veiculo.getPlaca());
            txtAno.setText(this.veiculo.getAno_modelo().toString());
            imgFoto.setImageBitmap(ImageUtilities.byteToBitmap(this.veiculo.getFoto()));
        }
    }

    public void novaFoto(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        uriFoto = obterCaminhoNovaFoto();

        i.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);

        startActivityForResult(i, codigoRequisicao);
    }
}

