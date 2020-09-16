package com.example.admin.angrybirds.model;

import android.media.MediaPlayer;
import android.widget.ImageView;

/**
 * Created by moghis on 19/12/2017.
 */

public abstract class MovingObject extends Objects{
    private double vx;
    private double vy;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

}
