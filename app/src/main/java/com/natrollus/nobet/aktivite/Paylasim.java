package com.natrollus.nobet.aktivite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.natrollus.nobet.R;

import static com.natrollus.nobet.araclar.Logla.logla;
import static com.natrollus.nobet.araclar.Logla.tostla;

public class Paylasim extends Activity {

    TextView cikti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paylasim);
        cikti = (TextView) findViewById(R.id.cikti);
        Intent icerik = getIntent();
        String yaz;
        yaz = "data:" + icerik.getData() + "\ntip:" +icerik.getType() + "\ndatastirng:" + icerik.getDataString();
        yaz+= "\naksiyon:" + icerik.getAction();
		yaz += "\n\n--extras--\n";
        Bundle bundle = icerik.getExtras();
        for (String key: bundle.keySet()){
            Object deger = bundle.get(key);
            if (deger != null) {
                yaz += "\nkey:" + key + "\ndeger:" + deger.toString() + "\nclass:" + deger.getClass().getName();
            }

        }

        cikti.setText(yaz);


    }
}
