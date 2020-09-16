package com.example.admin.angrybirds.maps;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.RelativeLayout;

import com.example.admin.angrybirds.R;
import com.example.admin.angrybirds.model.Box;
import com.example.admin.angrybirds.model.MovingObject;
import com.example.admin.angrybirds.model.Pig;
import com.example.admin.angrybirds.model.RedBird;

import java.util.ArrayList;
import java.util.List;



public class map2 {
    private static int heightBox;
    private static int widthBox;
    private static int widthRedBird;
    private static int heightRedBird;
    private static int widthPig;
    private static int heightPig;
    private static Point size;
    public static List<MovingObject> CreateObjects(Activity activity)
    {
        RelativeLayout root = activity.findViewById(R.id.root);
        int heightRedBirdPX, widthRedBirdPX;
        int heightBoxPX, widthBoxPX;
        int heightPigPX, widthPigPX;
        Display display = activity.getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        heightRedBirdPX=dpToPx(30,activity);
        widthRedBirdPX=dpToPx(30,activity);
        heightRedBird = size.y-heightRedBirdPX-130;

        heightBoxPX=dpToPx(40,activity);
        widthBoxPX=dpToPx(40,activity);
        heightBox = size.y-heightBoxPX-130;

        heightPigPX=dpToPx(30,activity);
        widthPigPX=dpToPx(30,activity);
        heightPig = size.y-heightPigPX-130;

        List<MovingObject> objects=new ArrayList<>();
        for (int i=1;i<=5;i++) {
            RedBird redBird = new RedBird(activity);
            redBird.setHeight(heightRedBirdPX);
            redBird.setWidth(widthRedBirdPX);
            switch (i) {
                case 1:
                    redBird.getImage().setX(302);
                    redBird.getImage().setY(size.y-379);
                    redBird.setId(0);
                    redBird.getImage().setEnabled(true);
                    break;
                case 2:
                    redBird.getImage().setX(200);
                    redBird.getImage().setY(heightRedBird);
                    redBird.setId(1);
                    redBird.setCollision(0);
                    redBird.getImage().setEnabled(false);
                    break;
                case 3:
                    redBird.getImage().setX(100);
                    redBird.getImage().setY(heightRedBird);
                    redBird.setId(2);
                    redBird.setCollision(0);
                    redBird.getImage().setEnabled(false);
                    break;
                case 4:
                    redBird.getImage().setX(0);
                    redBird.getImage().setY(heightRedBird);
                    redBird.setId(3);
                    redBird.setCollision(0);
                    redBird.getImage().setEnabled(false);
                    break;
                case 5:
                    redBird.getImage().setX(200);
                    redBird.getImage().setY(size.y-379);
                    redBird.setId(4);
                    redBird.setCollision(0);
                    redBird.getImage().setEnabled(false);
                    break;
            }
            objects.add(redBird);
        }
        for (int j=1;j<=12;j++)
        {
            Box box=new Box(activity);
            box.setHeight(heightBoxPX);
            box.setWidth(widthBoxPX);
            switch (j)
            {
                case 1:
                    box.getImage().setX(550);
                    box.getImage().setY(heightBox);
                    box.setCollision(1);
                    box.setId(5);
                    break;
                case 2:
                    box.getImage().setX(550);
                    box.getImage().setY(heightBox-heightBoxPX);
                    box.setCollision(1);
                    box.setId(6);
                    break;
                case 3:
                    box.getImage().setX(800);
                    box.getImage().setY(heightBox);
                    box.setCollision(1);
                    box.setId(7);
                    break;
                case 4:
                    box.getImage().setX(800);
                    box.getImage().setY(heightBox-heightBoxPX);
                    box.setCollision(1);
                    box.setId(8);
                    break;
                case 5:
                    box.getImage().setX(1050);
                    box.getImage().setY(heightBox);
                    box.setCollision(1);
                    box.setId(9);
                    break;
                case 6:
                    box.getImage().setX(1050);
                    box.getImage().setY(heightBox-heightBoxPX);
                    box.setCollision(1);
                    box.setId(10);
                    break;
                case 7:
                    box.getImage().setX(1050);
                    box.getImage().setY(heightBox-2*heightBoxPX);
                    box.setCollision(1);
                    box.setId(11);
                    break;
                case 8:
                    box.getImage().setX(1050);
                    box.getImage().setY(heightBox-3*heightBoxPX);
                    box.setCollision(1);
                    box.setId(12);
                    break;
                case 9:
                    box.getImage().setX(1300);
                    box.getImage().setY(heightBox);
                    box.setCollision(1);
                    box.setId(13);
                    break;
                case 10:
                    box.getImage().setX(1300);
                    box.getImage().setY(heightBox-heightBoxPX);
                    box.setCollision(1);
                    box.setId(14);
                    break;
                case 11:
                    box.getImage().setX(1300);
                    box.getImage().setY(heightBox-2*heightBoxPX);
                    box.setCollision(1);
                    box.setId(15);
                    break;
                case 12:
                    box.getImage().setX(1550);
                    box.getImage().setY(heightBox);
                    box.setCollision(1);
                    box.setId(16);
                    break;
            }

            objects.add(box);
        }


        for (int j=1;j<=5;j++)
        {
            Pig pig=new Pig(activity);
            pig.setHeight(heightPigPX);
            pig.setWidth(widthPigPX);
            switch (j)
            {
                case 1:
                    pig.getImage().setX(800);
                    pig.getImage().setY(heightPig-2*heightBoxPX);
                    pig.setCollision(1);
                    pig.setId(16);
                    break;
                case 2:
                    pig.getImage().setX(925);
                    pig.getImage().setY(heightPig);
                    pig.setCollision(1);
                    pig.setId(17);
                    break;
                case 3:
                    pig.getImage().setX(1175);
                    pig.getImage().setY(heightPig);
                    pig.setCollision(1);
                    pig.setId(18);
                    break;
                case 4:
                    pig.getImage().setX(1300);
                    pig.getImage().setY(heightPig-3*heightBoxPX);
                    pig.setCollision(1);
                    pig.setId(19);
                    break;
                case 5:
                    pig.getImage().setX(1425);
                    pig.getImage().setY(heightPig);
                    pig.setCollision(1);
                    pig.setId(20);
                    break;
            }

            objects.add(pig);
        }
        return objects;
    }
    public static int countBird()
    {
        return 5;
    }
    public static int countBox()
    {
        return 12;
    }
    public static int countPig()
    {
        return 5;
    }

    private static int dpToPx(int dp,Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
