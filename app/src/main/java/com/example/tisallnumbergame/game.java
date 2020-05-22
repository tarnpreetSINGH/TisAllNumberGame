package com.example.tisallnumbergame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class game extends AppCompatActivity {

	int[] same = {R.drawable.emoji,
			R.drawable.emoji2,
			R.drawable.emoji3,
			R.drawable.emoji4,
			R.drawable.emoji5,
			R.drawable.srsly};
	int[] hot = {R.drawable.hot,
			R.drawable.hot2,
			R.drawable.hot3,
			R.drawable.warm};
	int[] cold = {R.drawable.cold,
			R.drawable.cold2,
			R.drawable.cold3,
			R.drawable.cold4,
			R.drawable.icy};
	int[] normal = {R.drawable.calculate,
			R.drawable.calculate2,
			R.drawable.calculate3,
			R.drawable.number,
			R.drawable.number3,
			R.drawable.number4,
			R.drawable.number5};
	int[] cong = {R.drawable.congo,
			R.drawable.congo2,
			R.drawable.congo3,
			R.drawable.congo4,
			R.drawable.congo5};

	int theNum = Math.abs(new Random().nextInt());
	int move = 0;
	int num, prev, min, max;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

		Intent intent = getIntent();
		min = intent.getIntExtra("min",0);
		max = intent.getIntExtra("max",100);

		theNum = min + Math.abs(new Random().nextInt(max-min+1));

		final TextView moves = findViewById(R.id.moves);
		final EditText et = findViewById(R.id.editText);
		final Button check = findViewById(R.id.check);
		final Button add1 = findViewById(R.id.add1);
		final Button add10 = findViewById(R.id.add10);
		final Button add100 = findViewById(R.id.add100);
		final Button sub1 = findViewById(R.id.sub1);
		final Button sub10 = findViewById(R.id.sub10);
		final Button sub100 = findViewById(R.id.sub100);
		final Button gu = findViewById(R.id.gu);
		final ImageView iv = findViewById(R.id.imageView);
		int ivr = new Random().nextInt(normal.length);
		iv.setImageResource(normal[ivr]);

		et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
				boolean handled = false;
				if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
					check.performClick();
					handled = true;
				}
				return handled;
			}
		});

		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					num = Integer.parseInt(et.getText().toString());
				}
				catch (Exception e){
					Toast.makeText(getApplicationContext(),"Please enter a valid number.\nError: "+e,Toast.LENGTH_SHORT).show();
				}
				if(theNum==num){
					int i = new Random().nextInt(cong.length);
					iv.setImageResource(cong[i]);
					Toast.makeText(game.this, "lol, It took you "+move+" moves to do this!", Toast.LENGTH_LONG).show();
				}
				else if(num != prev){
					move++;
					moves.setText("Moves: "+move+" "+theNum+" "+min+" "+max);
			//decide
					if(Math.abs(theNum)-Math.abs(prev)>Math.abs(theNum)-Math.abs(num)){
						if(Math.abs(theNum)-Math.abs(num)<11)
							iv.setImageResource(R.drawable.scorching2);
						else{
							int i = new Random().nextInt(hot.length);
							iv.setImageResource(hot[i]);
						}
					}
					else if(Math.abs(theNum)-Math.abs(num)<11)
						iv.setImageResource(R.drawable.scorching);
					else{
						int i = new Random().nextInt(cold.length);
						iv.setImageResource(cold[i]);
					}

				}
				else{
					int i = new Random().nextInt(same.length);
					iv.setImageResource(same[i]);
				}
				prev = num;
			}
		});
		add1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num++;
				et.setText(String.valueOf(num));
			}
		});
		add10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num+= 10;
				et.setText(String.valueOf(num));
			}
		});
		add100.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num+= 100;
				et.setText(String.valueOf(num));
			}
		});
		sub1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num--;
				et.setText(String.valueOf(num));
			}
		});
		sub10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num-= 10;
				et.setText(String.valueOf(num));
			}
		});
		sub100.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num-= 100;
				et.setText(String.valueOf(num));
			}
		});
		gu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new AlertDialog.Builder(game.this)
						.setMessage("'The Number' you just gave up was: "+theNum+".\n'The Number' is now changed if you wanna play.")
						.setPositiveButton("OK", null)
						.show();
				theNum = min + Math.abs(new Random().nextInt(max-min+1));
				move = 0;
			}
		});

	}
}