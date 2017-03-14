package com.example.labdesenvolvimento.sequencia_imagens;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.onClick;

public class ImagensActivity extends Activity {

    private ImageView imageDownload;
    String[] URLS;
    int c, f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagens);
        imageDownload = (ImageView) findViewById(R.id.imageDownload);
        c = 0;
        URLS = getResources().getStringArray(R.array.URLs);
        f = URLS.length;

        nextImage(null);
    }

    public void nextImage(View v){
        if(c == f){
            c = 0;
        }
        DownloadImageAsync downloadImageAsync = new DownloadImageAsync();
        URL url = null;
        try {
            url = new URL(URLS[c++]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        downloadImageAsync.execute(url);
    }

    private class DownloadImageAsync extends AsyncTask<URL,Integer,Bitmap>{

        ProgressDialog progress;

        @Override
        protected void onPreExecute(){
            progress = ProgressDialog.show(ImagensActivity.this,"Baixando imagem","Por favor, aguarde...");
        }

        @Override
        protected Bitmap doInBackground(URL... params) {
            Bitmap myBitmap = null;
            try {
                myBitmap = Util.loadImage(params[0]);
            }catch (IOException e){
                Toast.makeText(ImagensActivity.this,"Imagem não carregada", Toast.LENGTH_SHORT).show();
            }
            return myBitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){ //acontece depois q a tarefa termina (exibe imagem)
            if(bitmap != null){
                imageDownload.setImageBitmap(bitmap);
            }
            progress.dismiss(); //para parar de aparecer o carregamento mesmo após a tarefa ser concluída.
        }
    }
}
