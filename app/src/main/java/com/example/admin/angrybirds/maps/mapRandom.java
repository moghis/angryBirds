package com.example.admin.angrybirds.maps;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import com.example.admin.angrybirds.model.Box;
import com.example.admin.angrybirds.model.MovingObject;
import com.example.admin.angrybirds.model.Pig;
import com.example.admin.angrybirds.model.RedBird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;




public class mapRandom {
    private static int countBird,countBox,countPig;
    public static List<MovingObject> CreateObjects(Activity activity)
    {
        int heightRedBirdPX, widthRedBirdPX;
        int heightBoxPX, widthBoxPX;
        int heightPigPX, widthPigPX;
        int pos1=0,pos2=0,pos3=0,pos4=0,pos5=0;

        Random random=new Random();

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        heightRedBirdPX=dpToPx(30,activity);
        widthRedBirdPX=dpToPx(30,activity);
        int heightRedBird = size.y - heightRedBirdPX - 130;

        heightBoxPX=dpToPx(40,activity);
        widthBoxPX=dpToPx(40,activity);
        int heightBox = size.y - heightBoxPX - 130;

        heightPigPX=dpToPx(30,activity);
        widthPigPX=dpToPx(30,activity);
        int heightPig = size.y - heightPigPX - 130;

        countBird = random.nextInt(7-2)+2;
        countPig = random.nextInt(countBird-1)+1;
        countBox = random.nextInt(16-10)+10;

        List<MovingObject> objects=new ArrayList<>();
        for (int i=1;i<=countBird;i++) {
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
                case 6:
                    redBird.getImage().setX(100);
                    redBird.getImage().setY(size.y-379);
                    redBird.setId(5);
                    redBird.setCollision(0);
                    redBird.getImage().setEnabled(false);
                    break;
            }
            objects.add(redBird);
        }

        int id=countBird;

        for (int i=1;i<=countBox;i++)
        {
            int position=random.nextInt(5);
            Box box=new Box(activity);
            box.setHeight(heightBoxPX);
            box.setWidth(widthBoxPX);
            switch (position)
            {
                case 0:
                    box.getImage().setX(550);
                    box.getImage().setY(heightBox -pos1*heightBoxPX);
                    box.setCollision(1);
                    box.setId(id++);
                    pos1++;
                    break;
                case 1:
                    box.getImage().setX(800);
                    box.getImage().setY(heightBox -pos2*heightBoxPX);
                    box.setCollision(1);
                    box.setId(id++);
                    pos2++;
                    break;
                case 2:
                    box.getImage().setX(1050);
                    box.getImage().setY(heightBox -pos3*heightBoxPX);
                    box.setCollision(1);
                    box.setId(id++);
                    pos3++;
                    break;
                case 3:
                    box.getImage().setX(1300);
                    box.getImage().setY(heightBox -pos4*heightBoxPX);
                    box.setCollision(1);
                    box.setId(id++);
                    pos4++;
                    break;
                case 4:
                    box.getImage().setX(1550);
                    box.getImage().setY(heightBox -pos5*heightBoxPX);
                    box.setCollision(1);
                    box.setId(id++);
                    pos5++;
                    break;
            }
            objects.add(box);
        }

        //int last_Position=-1;
        int[] position={0,1,2,3,4,5,6,7,8};
        shuffleArray(position);

        for (int i=1;i<=countPig;i++)
        {
            Pig pig=new Pig(activity);
            Log.i(TAG, "CreateObjects: "+position[i]+"/"+countPig);
            pig.setHeight(heightPigPX);
            pig.setWidth(widthPigPX);

            switch (position[i])
            {
                case 0:
                    pig.getImage().setX(550);
                    pig.getImage().setY(heightPig -pos1*heightBoxPX);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 1:
                    pig.getImage().setX(675);
                    pig.getImage().setY(heightPig);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 2:
                    pig.getImage().setX(800);
                    pig.getImage().setY(heightPig -pos2*heightBoxPX);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 3:
                    pig.getImage().setX(925);
                    pig.getImage().setY(heightPig);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 4:
                    pig.getImage().setX(1050);
                    pig.getImage().setY(heightPig -pos3*heightBoxPX);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 5:
                    pig.getImage().setX(1175);
                    pig.getImage().setY(heightPig);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 6:
                    pig.getImage().setX(1300);
                    pig.getImage().setY(heightPig -pos4*heightBoxPX);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 7:
                    pig.getImage().setX(1425);
                    pig.getImage().setY(heightPig);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
                case 8:
                    pig.getImage().setX(1550);
                    pig.getImage().setY(heightPig -pos5*heightBoxPX);
                    pig.setCollision(1);
                    pig.setId(id++);
                    break;
            }

            objects.add(pig);
        }
        return objects;
    }
    public static int countBird()
    {
        return countBird;
    }
    public static int countBox()
    {
        return countBox;
    }
    public static int countPig()
    {
        return countPig;
    }

    private static int dpToPx(int dp,Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private static void shuffleArray(int[] ar)
    {
        Random random = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = random.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
