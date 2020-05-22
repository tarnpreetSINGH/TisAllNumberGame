package com.example.tisallnumbergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	int min;
	int max;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        final Button game = findViewById(R.id.game);
        final TextView ets = findViewById(R.id.editText2);
        final TextView etb = findViewById(R.id.editText3);

		ets.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
				boolean handled = false;
				if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
					etb.requestFocus();
					handled = true;
				}
				return handled;
			}
		});
		etb.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
				boolean handled = false;
				if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
					game.performClick();
					handled = true;
				}
				return handled;
			}
		});

		game.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				min = 0;
				max = 100;
				try {
					min = Integer.parseInt(ets.getText().toString());
				}
				catch (Exception e){ Toast.makeText(getApplicationContext(),"Please enter a valid number.\nError: "+e,Toast.LENGTH_SHORT).show();}
					try {
						max = Integer.parseInt(etb.getText().toString());
					}
					catch (Exception e){ Toast.makeText(getApplicationContext(),"Please enter a valid number.\nError: "+e,Toast.LENGTH_SHORT).show();}
				Intent intent = new Intent(MainActivity.this, com.example.tisallnumbergame.game.class);
				intent.putExtra("min", min);
				intent.putExtra("max", max);
				startActivity(intent);
		}});

	}
}
