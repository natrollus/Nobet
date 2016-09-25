package com.natrollus.nobet.aktivite;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import com.natrollus.nobet.*;

import static com.natrollus.nobet.araclar.Logla.logla;
import static com.natrollus.nobet.araclar.Logla.tostla;
import android.net.*;

public class Paylasim extends Activity {

    TextView cikti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paylasim);
		Context context = getApplicationContext();
        cikti = (TextView) findViewById(R.id.cikti);
        Intent icerik = getIntent();
		if(icerik.getType().contains("image")){
			Uri uri = (Uri) icerik.getExtras().get("android.intent.extra.STREAM");
			tostla(context,"uri="+uri);
		}
		
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
