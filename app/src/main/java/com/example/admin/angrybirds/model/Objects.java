package com.example.admin.angrybirds.model;

import android.media.MediaPlayer;
import android.widget.ImageView;

/**
 * Created by moghis on 02/01/2018.
 */

public abstract class Objects {
    private int height;
    private int width;
    private int collision=1;
    private int hide =1;


    public abstract ImageView getImage();

    public abstract double getM();

    public abstract MediaPlayer getAudio();

    public int getCollision() {
        return collision;
    }

    public void setCollision(int collision) {
        this.collision = collision;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }
}
