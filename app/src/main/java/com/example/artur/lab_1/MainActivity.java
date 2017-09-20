package com.example.artur.lab_1;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button bt;
    EditText et;
    TableLayout tl;
    private static long  back_pressed;
    ConstraintLayout cl;
    int guess;
    boolean isGameEnd;
    boolean isColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView2);
        bt = (Button)findViewById(R.id.button);
        et = (EditText)findViewById(R.id.editText);
        tl = (TableLayout)findViewById(R.id.table);
        cl = (ConstraintLayout)findViewById(R.id.Constrait);
        isGameEnd = false;
        isColor = true;
        guess = (int)(Math.random()*100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Resources res = getResources();
        int title_color = res.getColor(R.color.colorPrimaryDark_second);
        int text_color = res.getColor(R.color.colorPrimary_second);
        int table_color = res.getColor(R.color.colorTableBackground_second);
        int tc = res.getColor(R.color.colorAccent);
        int tcr = res.getColor(R.color.colorPrimary);
        int tac = res.getColor(R.color.colorPrimaryDark);
        switch(id){
            case R.id.action_settings:
            {
                if(isColor){
                    bt.setBackgroundColor(title_color);
                    tl.setBackgroundColor(table_color);
                    et.setBackgroundColor(text_color);
                    cl.setBackgroundColor(text_color);
                    isColor = false;
                }else{
                    bt.setBackgroundColor(tac);
                    et.setBackgroundColor(tcr);
                    cl.setBackgroundColor(tcr);
                    tl.setBackgroundColor(tc);
                    isColor = true;
                }
                return true;
            }
            case R.id.action_cat1:
            {
                Toast.makeText(getBaseContext(), "Artur Georgizov, lab 2", Toast.LENGTH_SHORT).show();

                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onBackPressed() {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();
    }

    public void onClick(View v){
        if(!isGameEnd){
            if(et.getText().toString().equals("") || Integer.parseInt(et.getText().toString())<0 || Integer.parseInt(et.getText().toString())>100){
                Toast.makeText(this,"Wrong input",Toast.LENGTH_SHORT).show();
            }else {
                int input = Integer.parseInt(et.getText().toString());
                if (input < guess) {
                    tv.setText(getResources().getString(R.string.small_input));
                }
                if (input > guess) {
                    tv.setText(getResources().getString(R.string.large_input));
                }
                if (input == guess) {
                    tv.setText(getResources().getString(R.string.game_end));
                    isGameEnd = true;
                    bt.setText(getResources().getString(R.string.play_again));
                }
            }
        }else{
            guess = (int)(Math.random()*100);
            bt.setText(getResources().getString(R.string.button_text));
            tv.setText(getResources().getString(R.string.textField_text));
            isGameEnd = false;
        }
        et.setText("");
    }
}
