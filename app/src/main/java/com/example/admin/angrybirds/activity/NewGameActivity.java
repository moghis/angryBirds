package com.example.admin.angrybirds.activity;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.angrybirds.R;

public class NewGameActivity extends AppCompatActivity {

    public static final int LAUNCH_PLAY_ACTIVITY = 1020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button map1=findViewById(R.id.map1);
        Button map2=findViewById(R.id.map2);
        Button randomMap=findViewById(R.id.random_map);
        Button mapDesign=findViewById(R.id.map_design);

        map1.setOnClickListener(v -> {
            Intent intentPlay = new Intent(NewGameActivity.this,PlayActivity.class);
            intentPlay.putExtra("type",1);
            startActivityForResult(intentPlay,LAUNCH_PLAY_ACTIVITY);
        });

        map2.setOnClickListener(v -> {
            Intent intentPlay = new Intent(NewGameActivity.this,PlayActivity.class);
            intentPlay.putExtra("type",5);
            startActivityForResult(intentPlay,LAUNCH_PLAY_ACTIVITY);
        });

        randomMap.setOnClickListener(v -> {
            Intent intentRandom = new Intent(NewGameActivity.this,PlayActivity.class);
            intentRandom.putExtra("type",4);
            startActivityForResult(intentRandom,LAUNCH_PLAY_ACTIVITY);
        });

        mapDesign.setOnClickListener(v -> {
            Intent intentDesign = new Intent(NewGameActivity.this,SettingActivity.class);
            startActivity(intentDesign);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_PLAY_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            }
        }
    }
}
