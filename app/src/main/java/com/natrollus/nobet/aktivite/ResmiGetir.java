package com.natrollus.nobet.aktivite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.natrollus.nobet.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.natrollus.nobet.araclar.Logla.tostla;

public class ResmiGetir extends Activity {

    ImageButton ayarla;
    ImageView resim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resim);
        ayarla = (ImageButton) findViewById(R.id.ayarla);
        resim = (ImageView) findViewById(R.id.resim);

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
        InputStream is;
        try {
            is = context.getContentResolver().openInputStream(data.getData());
            if (is!=null){
                resim.setImageURI(data.getData());
            }
        } catch (Exception e) {
            tostla(context,"hata:"+e.toString());
        }
    }
}
