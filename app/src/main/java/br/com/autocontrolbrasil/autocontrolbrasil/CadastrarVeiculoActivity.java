package br.com.autocontrolbrasil.autocontrolbrasil;

import android.content.Intent;
import android.media.Image;
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

public class CadastrarVeiculoActivity extends AppCompatActivity {

    private static Integer codigoRequisicao = 9876;

    private Uri uriFoto;

    VeiculoVO veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_veiculo);

        Intent i = getIntent();

        Integer idVeiculo = i.getIntExtra("ID_VEICULO", 0);

        VeiculoDAO daoProjeto = new VeiculoDAO(this);
        this.veiculo = daoProjeto.consultar(idVeiculo);

        TextView txtPlaca = (TextView) findViewById(R.id.txtPlaca);
        txtPlaca.setText(veiculo.getPlaca());
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
            return true;
        }

        if (id == R.id.action_nova_foto) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            uriFoto = obterCaminhoNovaFoto();

            i.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);

            startActivityForResult(i, codigoRequisicao);

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

    private Uri obterCaminhoNovaFoto(){;
        return Uri.fromFile(FileUtilities.getNomeArquivoImagem());
    }

    public void salvar(View v) {
        EditText txtPlaca = (EditText) findViewById(R.id.txtPlaca);
        EditText txtVeiculo = (EditText) findViewById(R.id.txtVeiculo);
        EditText txtAno = (EditText) findViewById(R.id.txtAno);

        VeiculoVO veiculo = new VeiculoVO();
        veiculo.setPlaca(txtPlaca.getText().toString());
        veiculo.setNome(txtVeiculo.getText().toString());
        //veiculo.setAno_modelo();

        VeiculoDAO dao = new VeiculoDAO(this);

        dao.salvar(veiculo);

        setResult(RESULT_OK);

        finish();
    }



}
