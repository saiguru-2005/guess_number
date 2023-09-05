package com.saiguru.guessnumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class winORlost extends AppCompatActivity {
    RelativeLayout bg;

    TextView randa1;
    ImageView ThumpsUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_orlost);
        ThumpsUp = findViewById(R.id.iv_thumbs);
        randa1 = findViewById(R.id.tv_result);

        bg = findViewById(R.id.bg_lay);

        Intent intent = getIntent();
        if (intent.getStringExtra("key").equals("w")) {
            String num = intent.getStringExtra("ans");
            bg.setBackgroundColor(Color.parseColor("#4CAF50"));
            ThumpsUp.setBackground(getResources().getDrawable(R.drawable.round_thumb_up));
            randa1.setText("Congratulations your guess is correct! in "+num+" try.");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.parseColor("#4CAF50"));
            }

            // win
        } else {
            // lose
            String num = intent.getStringExtra("ans");
            bg.setBackgroundColor(Color.parseColor("#F44336"));
            ThumpsUp.setBackground(getResources().getDrawable(R.drawable.round_thumb_down));
        randa1.setText("Sorry your guess is wrong! "+num+" is the correct answer");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.parseColor("#F44336"));
            }
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        new AlertDialog.Builder(this)
                .setTitle("Restart Game")
                .setMessage("Are you sure you want to restart the game?")
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user confirms, close the app
                        re();
                    }
                })
                .setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user cancels, dismiss the dialog
                        finishAffinity();
                    }
                })
                .show();
    }

    private void re()
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}