package com.example.admin.angrybirds.model;

import android.content.Context;
import android.media.MediaPlayer;
import androidx.core.content.res.ResourcesCompat;

import android.widget.ImageView;

import com.example.admin.angrybirds.R;

/**
 * Created by moghis on 12/12/2017.
 */

public class Handle extends fixedObject {
    private ImageView handle;

    public Handle(Context context)
    {
        handle=new ImageView(context);
        handle.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.handle, null));
    }


    public int getHeight() {
        return 249;
    }

    public int getWidth() {
        return 152;
    }

    @Override
    public ImageView getImage() {
        return handle;
    }


    @Override
    public double getM() {
        return 10;
    }

    @Override
    public MediaPlayer getAudio() {
        return null;
    }

    public int getK()
    {
        return 50;
    }

    /*public MediaPlayer getAudio() {
        return MediaPlayer.create(context.getApplicationContext(), R.raw.red_bird_flying);
    }*/
}
