package com.natrollus.nobet.aktivite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.natrollus.nobet.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.natrollus.nobet.araclar.Ayarlar.degerEkle;
import static com.natrollus.nobet.araclar.Ayarlar.degerGetir;
import static com.natrollus.nobet.araclar.Logla.logla;
import static com.natrollus.nobet.araclar.Logla.tostla;

public class ResmiGetir extends Activity {

    ImageButton ayarla;
    ImageView resim;
    String adres;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        adres = (String) degerGetir(context,"adres","bos");
        tostla(context,"acikla:"+adres);
        setContentView(R.layout.resim);
        ayarla = (ImageButton) findViewById(R.id.ayarla);
        resim = (ImageView) findViewById(R.id.resim);

        if (adres!=null && !adres.equals("bos")){
            resim.setImageURI(Uri.parse(adres));
        }

        ayarla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resimSec();
            }
        });

    }

    private void resimSec() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Context context = getApplicationContext();
        if (requestCode == 0 && resultCode == Activity.RESULT_OK){
            if (data==null){
                tostla(context,"sıkıntı mevcut");
                return;
            }
            resmiAyarla(data, context);
        }
    }

    private void resmiAyarla(Intent data, Context context) {
        try {
            Uri icerik = data.getData();
            if (icerik!=null){
                adres = icerik.toString();
                logla("adres bu:"+adres);
                degerEkle(context,"adres",adres);
                degerEkle(context,"kac",0);
                Uri uri = Uri.parse(adres);
                resim.setImageURI(uri);
            }
            tostla(context,"adr:"+adres);
        } catch (Exception e) {
            tostla(context,"hata:"+e.toString());
        }
    }
}
