package com.example.kmj.minimaltodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    ArrayList<Data> data = new ArrayList<>();
    FloatingActionButton plus;
    String mainText;
    String subText;
    String content;
    ListView list;
    int position;
    ListViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        plus = findViewById(R.id.plus);
        list = findViewById(R.id.listview);
        mAdapter = new ListViewAdapter(data, this);
        list.setAdapter(mAdapter);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(intent, 1);//startActivity는 단순히 어떠한 액티비티를 시작하기 위한 용도로 사용되지만 startActivity는 시작된 액티비티를 통해 결과값을 돌려받기 위해 사용된다.

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                mainText = data.getStringExtra("main");
                subText = data.getStringExtra("sub");
                content = data.getStringExtra("content");
                this.data.add(new Data(mainText, subText, content));
                mAdapter.notifyDataSetChanged();
                saveData();
            }
            if (requestCode == 2) {
                mainText = data.getStringExtra("main");
                subText = data.getStringExtra("sub");
                content = data.getStringExtra("content");
                position = data.getIntExtra("position", 0);
                this.data.get(position).setMaintext(mainText);
                this.data.get(position).setSubtext(mainText);
                this.data.get(position).setExtra(mainText);
                mAdapter.notifyDataSetChanged();
                saveData();
            }
        }
    }

    @Override
    public void onDeleteClickListener(int i) {
        data.remove(i);
        mAdapter.notifyDataSetChanged();
        saveData();
    }

    @Override
    public void onEditClickListener(int i) {
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra("main", mainText);
        intent.putExtra("sub", subText);
        intent.putExtra("content", content);
        intent.putExtra("check", true);
        intent.putExtra("position", i);
        startActivityForResult(intent, 2);
    }

/*    @Override
    public void onListClickListener(int i) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("main", data.get(i).getMaintext());
        intent.putExtra("sub", data.get(i).getSubtext());
        intent.putExtra("content", data.get(i).getExtra());
        startActivity(intent);

    }*/

    public void saveData() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String json = new Gson().toJson(data);
        editor.putString("data", json);
        editor.commit();
    }

    public void loadData() {
        Gson gson = new Gson();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String json = pref.getString("data", "");
        ArrayList<Data> shareditems;
        shareditems = gson.fromJson(json, new TypeToken<ArrayList<Data>>() {
        }.getType());
        if (shareditems != null) {
            data.addAll(shareditems);
        }
    }
}
