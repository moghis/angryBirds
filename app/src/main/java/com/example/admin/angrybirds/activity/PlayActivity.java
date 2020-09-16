package com.example.admin.angrybirds.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.admin.angrybirds.DataBaseManager;
import com.example.admin.angrybirds.maps.MyMap;
import com.example.admin.angrybirds.maps.map1;
import com.example.admin.angrybirds.Move;
import com.example.admin.angrybirds.R;
import com.example.admin.angrybirds.maps.map2;
import com.example.admin.angrybirds.maps.mapRandom;
import com.example.admin.angrybirds.model.Handle;
import com.example.admin.angrybirds.model.Bird;
import com.example.admin.angrybirds.model.MovingObject;
import com.example.admin.angrybirds.model.Objects;

import java.util.List;

public class PlayActivity extends AppCompatActivity {
    private static final String TAG = "dfdfe";
    private RelativeLayout root;
    private double _xDelta ,_yDelta,cos,sin,xPrimary,yPriymary;
    private static double x ,y;
    private int i;
    private Handle handle;
    private List<MovingObject> objects;
    private ImageView save;
    private DataBaseManager dataBaseManagerSave;
    //private DataBaseManager dataBaseManagerVideo;

    private static int birdCount,boxCount,pigCount;
    private static int birdCounts,pigCounts;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_play);
        root=findViewById(R.id.root);
        
        dataBaseManagerSave = new DataBaseManager(this,"save_db");
        //dataBaseManagerVideo = new DataBaseManager(this,"video_db");
        
        save = findViewById(R.id.save_game);
        final Intent intent=getIntent();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        handle=new Handle(this);
        handle.getImage().setY(size.y-handle.getHeight()-130);
        handle.getImage().setX(handle.getWidth()+150);
        x=handle.getImage().getX();
        y=handle.getImage().getY();
        root.addView(handle.getImage());

        if (intent.getIntExtra("type",1)==1)
        {
            objects= map1.CreateObjects(this);
            birdCounts=map1.countBird();
            pigCounts=map1.countPig();
        }

        else if (intent.getIntExtra("type",1)==2)
        {
            objects = dataBaseManagerSave.getObjects();
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).getVx()!=0||objects.get(i).getVy()!=0)
                {
                    Move move=new Move(PlayActivity.this);
                    move.throwMoveOb(objects.get(i).getImage().getX(),objects.get(i).getImage().getY(),objects.get(i),objects,dataBaseManagerSave.bird(),dataBaseManagerSave.pig());
                }
            }
        }

        else if (intent.getIntExtra("type",1)==3)
        {
            objects= MyMap.CreateObjects(this,intent.getIntArrayExtra("pigPos"),intent.getIntExtra("birdCount",1),intent.getIntExtra("boxPos1",1),intent.getIntExtra("boxPos2",1),intent.getIntExtra("boxPos3",1),intent.getIntExtra("boxPos4",1),intent.getIntExtra("boxPos5",1));
            birdCounts=MyMap.countBird();
            pigCounts=MyMap.countPig();
        }

        else if (intent.getIntExtra("type",1)==4)
        {
            objects= mapRandom.CreateObjects(this);
            birdCounts=mapRandom.countBird();
            pigCounts=mapRandom.countPig();
        }
        else if (intent.getIntExtra("type",1)==5)
        {
            objects= map2.CreateObjects(this);
            birdCounts=map2.countBird();
            pigCounts=map2.countPig();
        }

        Log.i(TAG, "onCreate: "+objects.size());
        for (i=0;i<objects.size();i++)
        {
            root.addView(objects.get(i).getImage());
            if (objects.get(i) instanceof Bird)
            {
                Bird bird = (Bird) objects.get(i);
                bird.getImage().setOnTouchListener(new choiceTouchListener(bird));
            }
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseManagerSave.deleteAll();
                if (intent.getIntExtra("type",1)==4)
                    dataBaseManagerSave.addObjects(objects,mapRandom.countBird(),mapRandom.countBox(),mapRandom.countPig());
                else if (intent.getIntExtra("type",1)==1)
                    dataBaseManagerSave.addObjects(objects,map1.countBird(),map1.countBox(),map1.countPig());
                else if (intent.getIntExtra("type",1)==3)
                    dataBaseManagerSave.addObjects(objects,MyMap.countBird(),MyMap.countBox(),MyMap.countPig());
                else if (intent.getIntExtra("type",1)==5)
                    dataBaseManagerSave.addObjects(objects,map2.countBird(),map2.countBox(),map2.countPig());
            }
        });
    }


    private final class choiceTouchListener implements View.OnTouchListener {
        private Bird bird;
        choiceTouchListener(Bird bird)
        {
            this.bird = bird;
        }
        public boolean onTouch(View v, MotionEvent event) {
            double X = event.getRawX();
            double Y = event.getRawY();
            cos=(((X-_xDelta)-x)/Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2)));
            sin=((y-(Y - _yDelta))/Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2)));
            if (Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2))==0)
            {
                sin=0;
                cos=0;
            }
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    _xDelta = X - x;
                    _yDelta = Y - y;
                    break;
                case MotionEvent.ACTION_UP:
                    Move move=new Move(PlayActivity.this);
                    bird.getImage().setEnabled(false);
                    bird.getAudio().start();
                    bird.setCollision(1);
                    if (Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2))>170)
                    {
                        xPrimary=170*cos+x;
                        yPriymary= -170*sin+y;
                        bird.setVx(Math.sqrt(handle.getK()/ bird.getM())*3.5*170*-cos);
                        bird.setVy(Math.sqrt(handle.getK()/ bird.getM())*3.5*170*-sin);
                        move.throwMoveOb(xPrimary,yPriymary,bird,objects,birdCounts,pigCounts);
                    }
                    else
                    {
                        xPrimary= Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2))*cos+x;
                        yPriymary= -Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2))*sin+y;
                        bird.setVx(Math.sqrt(handle.getK()/ bird.getM())*3.5*Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2))*-cos);
                        bird.setVy(Math.sqrt(handle.getK()/ bird.getM())*3.5*Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2))*-sin);
                        move.throwMoveOb(xPrimary,yPriymary,bird,objects,birdCounts,pigCounts);
                    }

                    //if (bird.getId()+1<=birdCount-1)
                        //setAnimation(objects.get(bird.getId()+1));
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (Math.sqrt(Math.pow(x-(X - _xDelta),2)+Math.pow(y-(Y - _yDelta),2))>=170)
                    {
                        X=170*cos+_xDelta+x;
                        Y=-170*sin+_yDelta+y;
                    }
                    bird.getImage().setX((float) (X - _xDelta));
                    bird.getImage().setY((float) (Y - _yDelta));
                    break;
            }
            return true;
        }
    }
    public static void setAnimation(final Objects object)
    {
        final ImageView bird=object.getImage();
        final AnimatorSet animSetXY = new AnimatorSet();
        ObjectAnimator Y = ObjectAnimator.ofFloat(bird,"translationY",bird.getY(), (float) y);
        ObjectAnimator X = ObjectAnimator.ofFloat(bird,"translationX", bird.getX(),(float) x);
        animSetXY.playTogether(X, Y);
        animSetXY.setDuration(1000);

        animSetXY.start();
        bird.setEnabled(true);
    }
}
