package com.saiguru.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    Button startBtn,guessBtn;

    EditText low,high,etGuess;

    RelativeLayout gueLay;

    TextView randa;
    int chance;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        startBtn=findViewById(R.id.btn_start);
        guessBtn=findViewById(R.id.btn_guess);

        low = findViewById(R.id.et_low);
        high = findViewById(R.id.et_high);
        etGuess = findViewById(R.id.et_guess_number);
        gueLay=findViewById(R.id.gu_lay);

        randa = findViewById(R.id.tv_chances);

        TextView tv = findViewById(R.id.tv1);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("guess_number");


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!low.getText().toString().isEmpty() && !high.getText().toString().isEmpty())
                {
                    gueLay.setVisibility(View.VISIBLE);
                    PyObject obj = pyObject.callAttr("main1",low.getText().toString(),high.getText().toString());

                    randa.setText("Chances left: "+obj.toString());
                    chance=obj.toInt();

                }else {
                    Toast.makeText(MainActivity.this, "Please enter lower and higher bounds.", Toast.LENGTH_LONG).show();
                }
            }
        });

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etGuess.getText().toString().isEmpty()){

                    PyObject obj = pyObject.callAttr("check",etGuess.getText().toString());
                    PyObject[] tupleItems = obj.asList().toArray(new PyObject[0]);

                    if (tupleItems.length >= 2) {
                        int variable1 = tupleItems[0].toJava(int.class);
                        String variable2 = tupleItems[1].toJava(String.class);


                        if (variable2.equals("win"))
                        {
                            open("w", String.valueOf(variable1));
                        } else if (variable2.equals("small")) {
                            Toast.makeText(MainActivity.this, "Your guess is too small!"+variable1, Toast.LENGTH_SHORT).show();
                            //chance=chance-variable1;
                            randa.setText("Chances left: "+variable1);
                        } else if (variable2.equals("high")) {
                            Toast.makeText(MainActivity.this, "Your guess is too high!"+variable1, Toast.LENGTH_SHORT).show();
                            //chance=chance-variable1;
                            randa.setText("Chances left: "+variable1);
                        } else if (variable2.equals("lose")) {
                            open("l", String.valueOf(variable1));
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Please enter your guess", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void open(String string, String ans)
    {
        Intent intent = new Intent(this, winORlost.class);
        intent.putExtra("key", string);
        intent.putExtra("ans",ans);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}