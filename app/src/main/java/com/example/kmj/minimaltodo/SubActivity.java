package com.example.kmj.minimaltodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SubActivity extends AppCompatActivity{
    EditText mainText;
    EditText subText;
    EditText inputcontent;
    ImageButton okay;
    int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmemo);
        mainText=findViewById(R.id.inputbig);
        subText=findViewById(R.id.inputsmall);
        inputcontent=findViewById(R.id.inputcontent);
        okay=findViewById(R.id.send);

        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);
        Boolean bool=intent.getBooleanExtra("check",false);
        if(bool ==true){
            mainText.setText(intent.getStringExtra("main"));
            subText.setText(intent.getStringExtra("sub"));
            inputcontent.setText(intent.getStringExtra("content"));
        }
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("main",mainText.getText().toString());
                intent.putExtra("sub",subText.getText().toString());
                intent.putExtra("content",inputcontent.getText().toString());
                intent.putExtra("position",position);
                setResult(RESULT_OK,intent);
                finish();
            }
        });



    }
}
