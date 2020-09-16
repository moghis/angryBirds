package com.example.admin.angrybirds.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.ImageView;

import com.example.admin.angrybirds.R;

/**
 * Created by moghis on 02/01/2018.
 */

public class Pig extends MovingObject {
    private ImageView pig;
    private Context context;
    private MediaPlayer sound;

    public Pig(Context context)
    {
        pig=new ImageView(context);
        pig.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pig, null));
        sound=MediaPlayer.create(context.getApplicationContext(), R.raw.pig_collision);
        this.context=context;
    }

    @Override
    public ImageView getImage() {
        return pig;
    }

    @Override
    public double getM() {
        return 5;
    }

    @Override
    public MediaPlayer getAudio() {
        return sound;
    }
}
