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

import static android.content.ContentValues.TAG;



public class MyMap {
    private static int countBird,countBox,countPig;

        public static List<MovingObject> CreateObjects(Activity activity,int[] position,int birdCount,int boxPos1,int boxPos2,int boxPos3,int boxPos4,int boxPos5)
        {
            int heightRedBirdPX, widthRedBirdPX;
            int heightBoxPX, widthBoxPX;
            int heightPigPX, widthPigPX;
            int pos1=0,pos2=0,pos3=0,pos4=0,pos5=0;
            countBox=boxPos1+boxPos2+boxPos3+boxPos4+boxPos5;
            countBird=birdCount;
            countPig=0;

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


            List<MovingObject> objects=new ArrayList<>();
            for (int i=1;i<=birdCount;i++) {
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

            int id=birdCount;

            for (int i = 0; i < boxPos1; i++) {
                Box box=new Box(activity);
                box.setHeight(heightBoxPX);
                box.setWidth(widthBoxPX);
                box.getImage().setX(550);
                box.getImage().setY(heightBox -pos1*heightBoxPX);
                box.setCollision(1);
                box.setId(id++);
                pos1++;
                objects.add(box);
            }

            for (int i = 0; i < boxPos2; i++) {
                Box box=new Box(activity);
                box.setHeight(heightBoxPX);
                box.setWidth(widthBoxPX);
                box.getImage().setX(800);
                box.getImage().setY(heightBox -pos2*heightBoxPX);
                box.setCollision(1);
                box.setId(id++);
                pos2++;
                objects.add(box);
            }

            for (int i = 0; i < boxPos3; i++) {
                Box box=new Box(activity);
                box.setHeight(heightBoxPX);
                box.setWidth(widthBoxPX);
                box.getImage().setX(1050);
                box.getImage().setY(heightBox -pos3*heightBoxPX);
                box.setCollision(1);
                box.setId(id++);
                pos3++;
                objects.add(box);
            }

            for (int i = 0; i < boxPos4; i++) {
                Box box=new Box(activity);
                box.setHeight(heightBoxPX);
                box.setWidth(widthBoxPX);
                box.getImage().setX(1300);
                box.getImage().setY(heightBox -pos4*heightBoxPX);
                box.setCollision(1);
                box.setId(id++);
                pos4++;
                objects.add(box);
            }

            for (int i = 0; i < boxPos5; i++) {
                Box box=new Box(activity);
                box.setHeight(heightBoxPX);
                box.setWidth(widthBoxPX);
                box.getImage().setX(1550);
                box.getImage().setY(heightBox -pos5*heightBoxPX);
                box.setCollision(1);
                box.setId(id++);
                pos5++;
                objects.add(box);
            }

            for (int value : position)
                switch (value) {
                    case 1:
                        Pig pig = new Pig(activity);
                        pig.setHeight(heightPigPX);
                        pig.setWidth(widthPigPX);
                        pig.getImage().setX(550);
                        pig.getImage().setY(heightPig - pos1 * heightBoxPX);
                        pig.setCollision(1);
                        pig.setId(id++);
                        countPig++;
                        objects.add(pig);
                        break;
                    case 2:
                        Pig pig2 = new Pig(activity);
                        pig2.setHeight(heightPigPX);
                        pig2.setWidth(widthPigPX);
                        pig2.getImage().setX(675);
                        pig2.getImage().setY(heightPig);
                        pig2.setCollision(1);
                        pig2.setId(id++);
                        countPig++;
                        objects.add(pig2);
                        break;
                    case 3:
                        Pig pig3 = new Pig(activity);
                        pig3.setHeight(heightPigPX);
                        pig3.setWidth(widthPigPX);
                        pig3.getImage().setX(800);
                        pig3.getImage().setY(heightPig - pos2 * heightBoxPX);
                        pig3.setCollision(1);
                        pig3.setId(id++);
                        countPig++;
                        objects.add(pig3);
                        break;
                    case 4:
                        Pig pig4 = new Pig(activity);
                        pig4.setHeight(heightPigPX);
                        pig4.setWidth(widthPigPX);
                        pig4.getImage().setX(925);
                        pig4.getImage().setY(heightPig);
                        pig4.setCollision(1);
                        pig4.setId(id++);
                        countPig++;
                        objects.add(pig4);
                        break;
                    case 5:
                        Pig pig5 = new Pig(activity);
                        pig5.setHeight(heightPigPX);
                        pig5.setWidth(widthPigPX);
                        pig5.getImage().setX(1050);
                        pig5.getImage().setY(heightPig - pos3 * heightBoxPX);
                        pig5.setCollision(1);
                        pig5.setId(id++);
                        countPig++;
                        objects.add(pig5);
                        break;
                    case 6:
                        Pig pig6 = new Pig(activity);
                        pig6.setHeight(heightPigPX);
                        pig6.setWidth(widthPigPX);
                        pig6.getImage().setX(1175);
                        pig6.getImage().setY(heightPig);
                        pig6.setCollision(1);
                        pig6.setId(id++);
                        countPig++;
                        objects.add(pig6);
                        break;
                    case 7:
                        Pig pig7 = new Pig(activity);
                        pig7.setHeight(heightPigPX);
                        pig7.setWidth(widthPigPX);
                        pig7.getImage().setX(1300);
                        pig7.getImage().setY(heightPig - pos4 * heightBoxPX);
                        pig7.setCollision(1);
                        pig7.setId(id++);
                        countPig++;
                        objects.add(pig7);
                        break;
                    case 8:
                        Pig pig8 = new Pig(activity);
                        pig8.setHeight(heightPigPX);
                        pig8.setWidth(widthPigPX);
                        pig8.getImage().setX(1425);
                        pig8.getImage().setY(heightPig);
                        pig8.setCollision(1);
                        pig8.setId(id++);
                        countPig++;
                        objects.add(pig8);
                        break;
                    case 9:
                        Pig pig9 = new Pig(activity);
                        pig9.setHeight(heightPigPX);
                        pig9.setWidth(widthPigPX);
                        pig9.getImage().setX(1550);
                        pig9.getImage().setY(heightPig - pos5 * heightBoxPX);
                        pig9.setCollision(1);
                        pig9.setId(id++);
                        countPig++;
                        objects.add(pig9);
                        break;

                }
            Log.i(TAG, "CreateObjects: "+countBird+"/"+countBox+"/"+countPig);
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
    }
