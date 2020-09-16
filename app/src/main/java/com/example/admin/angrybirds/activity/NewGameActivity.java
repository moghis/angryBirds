package com.example.admin.angrybirds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.admin.angrybirds.R;

public class NewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button map1=findViewById(R.id.map1);
        Button map2=findViewById(R.id.map2);
        Button randomMap=findViewById(R.id.random_map);
        Button mapDesign=findViewById(R.id.map_design);

        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlay = new Intent(NewGameActivity.this,PlayActivity.class);
                intentPlay.putExtra("type",1);
                startActivity(intentPlay);
            }
        });

        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlay = new Intent(NewGameActivity.this,PlayActivity.class);
                intentPlay.putExtra("type",5);
                startActivity(intentPlay);
            }
        });

        randomMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRandom = new Intent(NewGameActivity.this,PlayActivity.class);
                intentRandom.putExtra("type",4);
                startActivity(intentRandom);
            }
        });

        mapDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDesign = new Intent(NewGameActivity.this,SettingActivity.class);
                startActivity(intentDesign);
            }
        });
    }
}
