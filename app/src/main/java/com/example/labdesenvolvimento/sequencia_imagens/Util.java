package com.example.labdesenvolvimento.sequencia_imagens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Util {

    public static Bitmap loadImage(URL url) throws IOException {
        InputStream inputStream;
        Bitmap myBitmap;

        inputStream = url.openStream();
        myBitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();//alt enter com cursor antes de ";" para escolher o tipo da excessão a ser tratada.
        // throws joga pra ser tratado na activity e try trata na hora como Util não exstende Activity será usado
        // throws para ser resolvido em uma classe que extenda Activity.
        
        return myBitmap;

    }

}
