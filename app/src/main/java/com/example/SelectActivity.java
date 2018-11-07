package com.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.app.*;
import java.text.DecimalFormat;
import java.util.*;

public class SelectActivity extends AppCompatActivity {

    Button quiz,opr;

    final Context context = this;

    public void onBackPressed() {
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        quiz=(Button)findViewById(R.id.quiz);
        opr = (Button)findViewById(R.id.opr);


        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        opr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, OprActivity.class);
                startActivity(intent);
            }
        });

    }

}
