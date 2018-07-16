package com.example.kmj.minimaltodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity{
    TextView main;
    TextView sub;
    TextView content;

    String large;
    String small;
    String extra;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        main=findViewById(R.id.viewlarge);
        sub=findViewById(R.id.viewsmall);
        content=findViewById(R.id.viewcontent);
        Intent intent=getIntent();
        large=intent.getStringExtra("main");
        small=intent.getStringExtra("sub");
        extra=intent.getStringExtra("extra");
        main.setText(large);
        sub.setText(small);
        content.setText(extra);

    }
}
