package com.example.admin.angrybirds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.admin.angrybirds.R;
import com.example.admin.angrybirds.model.MovingObject;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    List<MovingObject> objects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button play=findViewById(R.id.play);
        Button save=findViewById(R.id.save);
        Button exit=findViewById(R.id.exit);
       // Button video=findViewById(R.id.video);
        //Button setting=findViewById(R.id.setting);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlay = new Intent(MenuActivity.this,NewGameActivity.class);
                startActivity(intentPlay);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSave = new Intent(MenuActivity.this,PlayActivity.class);
                intentSave.putExtra("type",2);
                startActivity(intentSave);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
