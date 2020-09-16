package com.example.admin.angrybirds.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.example.admin.angrybirds.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button play=findViewById(R.id.play);
        Button save=findViewById(R.id.save);
        Button exit=findViewById(R.id.exit);

        play.setOnClickListener(v -> {
            Intent intentPlay = new Intent(MenuActivity.this,NewGameActivity.class);
            startActivity(intentPlay);
        });

        save.setOnClickListener(v -> {
            Intent intentSave = new Intent(MenuActivity.this,PlayActivity.class);
            intentSave.putExtra("type",2);
            startActivity(intentSave);
        });

        exit.setOnClickListener(v -> finish());
    }
}
