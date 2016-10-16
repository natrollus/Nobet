package com.natrollus.nobet.aktivite;
import android.app.*;
import android.os.*;
import com.natrollus.nobet.*;
import android.widget.*;

public class LogActivite extends Activity {

	TextView cikti;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_activite);
		cikti = (TextView) findViewById(R.id.logcikti);
		String log = getIntent().getStringExtra("log");
		cikti.setTextIsSelectable(true);
		cikti.setText(log);
	}
	
	
	
}
