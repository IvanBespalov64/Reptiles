package com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        TextView info = (TextView) findViewById(R.id.data);
        Intent intent = getIntent();
        String arg = intent.getStringExtra("EXTRA");
        info.setText(arg);
    }
}
