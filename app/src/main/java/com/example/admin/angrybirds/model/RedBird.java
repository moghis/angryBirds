package com.example.admin.angrybirds.model;

import android.content.Context;
import android.media.MediaPlayer;
import androidx.core.content.res.ResourcesCompat;

import android.widget.ImageView;

import com.example.admin.angrybirds.R;

/**
 * Created by moghis on 10/12/2017.
 */

public class RedBird extends Bird {
    private Context context;
    private ImageView bird;
    private MediaPlayer sound;
    public RedBird(Context context)
    {
        bird=new ImageView(context);
        bird.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.red_bird_icon, null));
        sound=MediaPlayer.create(context.getApplicationContext(), R.raw.red_bird_flying);
        this.context=context;
    }

    @Override
    public double getM() {
        return 3;
    }

    @Override
    public ImageView getImage() {
        return bird;
    }

    @Override
    public MediaPlayer getAudio() {
        return sound;
    }
}
