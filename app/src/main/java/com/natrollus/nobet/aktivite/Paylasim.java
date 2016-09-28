package com.natrollus.nobet.aktivite;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import com.natrollus.nobet.*;

import static com.natrollus.nobet.araclar.Logla.logla;
import static com.natrollus.nobet.araclar.Logla.tostla;
import static com.natrollus.nobet.araclar.Ayarlar.*;
import android.net.*;
import android.preference.*;

public class Paylasim extends Activity {

    TextView cikti;
	Context context;
	SharedPreferences ayarlar;
	String adres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paylasim);
		context = getApplicationContext();
		adres = (String) degerGetir(context,"adres","bos");
        cikti = (TextView) findViewById(R.id.cikti);
        Intent icerik = getIntent();
		if(icerik.getType().contains("image")){
			Uri uri = (Uri) icerik.getExtras().get(Intent.EXTRA_STREAM);
			degerEkle(context,"adres",uri.toString());
			tostla(context,"uri="+uri+" adres:"+adres);
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
