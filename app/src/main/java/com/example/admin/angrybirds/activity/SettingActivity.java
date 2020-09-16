package com.example.admin.angrybirds.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;

import com.example.admin.angrybirds.R;

public class SettingActivity extends AppCompatActivity {
    int birdCount,boxPos1,boxPos2,boxPos3,boxPos4,boxPos5;
    int[] pigPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pigPos=new int[9];

        NumberPicker numberPickerBird=findViewById(R.id.bird_number);
        numberPickerBird.setMinValue(1);
        numberPickerBird.setMaxValue(6);

        NumberPicker numberPickerBoxPos1 = findViewById(R.id.box_number_pos1);
        numberPickerBoxPos1.setMaxValue(0);
        numberPickerBoxPos1.setMaxValue(4);

        NumberPicker numberPickerBoxPos2 = findViewById(R.id.box_number_pos2);
        numberPickerBoxPos2.setMaxValue(0);
        numberPickerBoxPos2.setMaxValue(4);

        NumberPicker numberPickerBoxPos3 = findViewById(R.id.box_number_pos3);
        numberPickerBoxPos3.setMaxValue(0);
        numberPickerBoxPos3.setMaxValue(4);

        NumberPicker numberPickerBoxPos4 = findViewById(R.id.box_number_pos4);
        numberPickerBoxPos4.setMaxValue(0);
        numberPickerBoxPos4.setMaxValue(4);

        NumberPicker numberPickerBoxPos5 = findViewById(R.id.box_number_pos5);
        numberPickerBoxPos5.setMaxValue(0);
        numberPickerBoxPos5.setMaxValue(4);

        CheckBox checkBoxPigPos1=findViewById(R.id.pig_pos1);
        CheckBox checkBoxPigPos2=findViewById(R.id.pig_pos2);
        CheckBox checkBoxPigPos3=findViewById(R.id.pig_pos3);
        CheckBox checkBoxPigPos4=findViewById(R.id.pig_pos4);
        CheckBox checkBoxPigPos5=findViewById(R.id.pig_pos5);
        CheckBox checkBoxPigPos6=findViewById(R.id.pig_pos6);
        CheckBox checkBoxPigPos7=findViewById(R.id.pig_pos7);
        CheckBox checkBoxPigPos8=findViewById(R.id.pig_pos8);
        CheckBox checkBoxPigPos9=findViewById(R.id.pig_pos9);

        birdCount=1;

        numberPickerBird.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                birdCount=newVal;
            }
        });



        numberPickerBoxPos1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                boxPos1=newVal;
            }
        });

        numberPickerBoxPos2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                boxPos2=newVal;
            }
        });

        numberPickerBoxPos3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                boxPos3=newVal;
            }
        });

        numberPickerBoxPos4.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                boxPos4=newVal;
            }
        });

        numberPickerBoxPos5.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                boxPos5 = newVal;
            }
        });




        checkBoxPigPos1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[0]=1;
                else
                    pigPos[0]=0;
            }
        });

        checkBoxPigPos2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[1]=2;
                else
                    pigPos[1]=0;
            }
        });

        checkBoxPigPos3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[2]=3;
                else
                    pigPos[2]=0;
            }
        });

        checkBoxPigPos4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[3]=4;
                else
                    pigPos[3]=0;
            }
        });

        checkBoxPigPos5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[4]=5;
                else
                    pigPos[4]=0;
            }
        });

        checkBoxPigPos6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[5]=6;
                else
                    pigPos[5]=0;
            }
        });

        checkBoxPigPos7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[6]=7;
                else
                    pigPos[6]=0;
            }
        });

        checkBoxPigPos8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[7]=8;
                else
                    pigPos[7]=0;
            }
        });

        checkBoxPigPos9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pigPos[8]=9;
                else
                    pigPos[8]=0;
            }
        });

        Button playButton=findViewById(R.id.play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingActivity.this,PlayActivity.class);
                intent.putExtra("pigPos",pigPos);
                intent.putExtra("birdCount",birdCount);
                intent.putExtra("boxPos1",boxPos1);
                intent.putExtra("boxPos2",boxPos2);
                intent.putExtra("boxPos3",boxPos3);
                intent.putExtra("boxPos4",boxPos4);
                intent.putExtra("boxPos5",boxPos5);
                intent.putExtra("type",3);
                startActivity(intent);
            }
        });

    }
}
