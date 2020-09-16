package com.example.admin.angrybirds.model;

import android.content.Context;
import android.media.MediaPlayer;
import androidx.core.content.res.ResourcesCompat;
import android.widget.ImageView;
import com.example.admin.angrybirds.R;

/**
 * Created by moghis on 10/12/2017.
 */

public class Box extends MovingObject {
    private ImageView box;
    private Context context;
    public Box(Context context)
    {
        box=new ImageView(context);
        box.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.box, null));
        this.context=context;
    }

    @Override
    public double getM() {
        return 5;
    }

    @Override
    public ImageView getImage() {
        return box;
    }

    @Override
    public MediaPlayer getAudio() {
        return null;
    }

    /*@Override
    public int getHeight() {
        return 90;
    }

    @Override
    public int getWidth() {
        return 126;
    }*/
}
