package br.com.autocontrolbrasil.autocontrolbrasil.utilities;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ImageUtilities {

    public static byte[] imageViewToByte (ImageView imagem){

        imagem.setDrawingCacheEnabled(true);

        imagem.buildDrawingCache();

        Bitmap bitmap = imagem.getDrawingCache();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        return stream.toByteArray();
    }
}