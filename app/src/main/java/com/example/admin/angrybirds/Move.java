package com.example.admin.angrybirds;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.angrybirds.activity.MenuActivity;
import com.example.admin.angrybirds.activity.PlayActivity;
import com.example.admin.angrybirds.model.Bird;
import com.example.admin.angrybirds.model.Box;
import com.example.admin.angrybirds.model.MovingObject;
import com.example.admin.angrybirds.model.Pig;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;


public class Move{
    private boolean finish=false;
    private Display display;
    private Point size;
    private int a,b,n,m;
    private Move move;
    private int i,j;
    private double Vx,Vy,e=0.6,k=0.6,X,Y;
    private int width,height;
    private Timer timerThrow;
    private Timer pigThrow;
    private Activity activity;
    private List<MovingObject> objects;
    private ImageView obImage;
    private float obImageX,obImageY;
    private int obImageHeight,obImageWidth,countBird,countpig;
    private static int pigcount=0;
    Intent intent;
    public Move(Activity activity)
    {
        this.activity=activity;
    }
    public void throwMoveOb(final double x, final double y, final MovingObject movingObject, final List<MovingObject> objects, final int countBird, final int countpig)
    {
        display = activity.getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        if (movingObject.getId()==0)
            pigcount=0;

        this.countBird=countBird;
        this.countpig=countpig;
        width = size.x - movingObject.getWidth();
        height = size.y - movingObject.getHeight()-130;
        this.objects =objects;
        //Log.i(TAG, "throwMoveOb: "+ PlayActivity.pxToDp(movingObject.getImage().getHeight(),activity) +"/"+ PlayActivity.pxToDp(movingObject.getImage().getWidth(),activity) );
        i=j=0;
        X=x;
        Y=y;
        Log.i(TAG, "throwMoveOb: "+countpig+"/"+pigcount);
        Vx= movingObject.getVx();
        Vy= movingObject.getVy();
        timerThrow = new Timer();
        timerThrow.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                        j--;
                        if (Vx<10&&Vx>-10)
                            movingObject.setVx(0);
                            //if (movingObject.getVy()>0&&movingObject.getVy()<15)
                            //    movingObject.setVy(0);
                        else if (!finish)
                            movingObject.setVy(Vy-2500*i*0.01);
                        movingObject.getImage().setX((float) (Vx*i*0.01+X));
                        movingObject.getImage().setY((float) (Y+(0.5*2500*j*j*0.0001)+(Vy*j*0.01)));
                        collisionToWall(movingObject);
                        collisionToObject(movingObject);
                        movingObject.getImage().setX((float) (Vx*i*0.01+X));
                        movingObject.getImage().setY((float) (Y+(0.5*2500*j*j*0.0001)+(Vy*j*0.01)));
                        if (movingObject.getVx()==0&&(finish||movingObject.getVy()<40&&movingObject.getVy()>-40))
                        {
                            movingObject.setVy(0);
                            timerThrow.cancel();
                            timerThrow.purge();
                            if (movingObject.getId()+1==countBird&&pigcount!=countpig)
                            {
                                Toast.makeText(activity,"lose",Toast.LENGTH_LONG).show();
                                activity.finish();
                                intent=new Intent(activity,MenuActivity.class);
                                activity.startActivity(intent);
                            }
                            if (pigcount==countpig&&(movingObject.getId()+1==countBird||movingObject.getId()+1<countBird))
                            {
                                Toast.makeText(activity,"win",Toast.LENGTH_LONG).show();
                                activity.finish();
                                intent=new Intent(activity,MenuActivity.class);
                                activity.startActivity(intent);
                            }
                            else if (movingObject.getId()+1<countBird)
                            {
                                movingObject.getImage().setVisibility(View.INVISIBLE);
                                movingObject.setHide(0);
                                movingObject.setCollision(0);
                                PlayActivity.setAnimation(objects.get(movingObject.getId()+1));
                            }
                        }
                        else
                            finish=false;
                    }
                });
            }
        },0,30);
    }

    private void collisionToWall(MovingObject movingObject)
    {
        if (isCollisionToWallY(movingObject))
        {
            i=j=0;
            //Log.i(TAG, "collisionX: 3");
            if (movingObject instanceof Pig &&(movingObject.getVy()>200||movingObject.getVy()<-200))
            {
                objects.get(m).setCollision(0);
                throwPig(movingObject,size);
                //move=new Move(activity);
                //move.throwPig(movingObject,size);
            }
            Vy=-movingObject.getVy()*e;
            Vx= movingObject.getVx()*k;
            movingObject.setVx(Vx);
            movingObject.setVy(Vy);
            finish=true;
        }
        if (isCollisionToWallX(movingObject))
        {
            i=j=0;
            //Log.i(TAG, "collisionX: 4");
            Vx=-movingObject.getVx()*e;
            Vy= movingObject.getVy()*k;
            movingObject.setVx(Vx);
            movingObject.setVy(Vy);
        }
    }

    private boolean isCollisionToWallX(MovingObject movingObject)
    {
        ImageView obImage=movingObject.getImage();
        if (obImage.getX()>=width)
        {
            X=width;
            Y=obImage.getY();
            return true;
        }
        if (obImage.getX()<=0)
        {
            X=0;
            Y=obImage.getY();
            return true;
        }
        return false;
    }

    private boolean isCollisionToWallY(MovingObject movingObject)
    {
        ImageView obImage=movingObject.getImage();
        if (obImage.getY()>=height)
        {
            X=obImage.getX();
            Y=height;
            return true;
        }
        if (obImage.getY()<=0)
        {
            X=obImage.getX();
            Y=0;
            return true;
        }
        return false;
    }

    private void collisionToObject(MovingObject movingObject)
    {
        obImage=movingObject.getImage();
        obImageX=obImage.getX();
        obImageY=obImage.getY();
        obImageHeight=movingObject.getHeight();
        obImageWidth=movingObject.getWidth();
        //Log.i(TAG, "collisionToObjecteeeeeeee: "+movingObject.getHeight()+"/"+obImage.getHeight());
        for (a=0;a<objects.size();a++)
        {
            if (obImageY>=objects.get(a).getImage().getY()-5*obImageHeight/6&&obImageY<=(objects.get(a).getImage().getY()-obImageHeight/6+objects.get(a).getHeight())&&obImageX>(objects.get(a).getImage().getX()-obImageWidth)&&obImageX<objects.get(a).getImage().getX()&&objects.get(a).getCollision()==1)
            {
                if (objects.get(a) instanceof Box||objects.get(a) instanceof Bird)
                {
                    X=objects.get(a).getImage().getX()-obImageWidth;
                    Y=obImageY;
                    //Log.i(TAG, "collisionX: 1/"+objects.get(a).getImage().getX()+"/"+X+"/"+obImage.getWidth());
                    collisionX(movingObject,objects.get(a));
                    return ;
                }
                else if (objects.get(a).getVx()==0&&objects.get(a).getVy()==0)
                {
                    objects.get(a).setCollision(0);
                    throwPig(objects.get(a),size);
                    //move=new Move(activity);
                    //move.throwPig(objects.get(a),size);
                    return ;
                }
            }
        }
        for (b=0;b<objects.size();b++)
            if (obImageY>=objects.get(b).getImage().getY()-5*obImageHeight/6&&obImageY<=(objects.get(b).getImage().getY()-obImageHeight/6+objects.get(b).getHeight())&&obImageX<(objects.get(b).getImage().getX()+objects.get(b).getWidth())&&obImageX>objects.get(b).getImage().getX()&&objects.get(b).getCollision()==1)
            {
                if (objects.get(b) instanceof Box||objects.get(b) instanceof Bird)
                {
                    X=objects.get(b).getImage().getX()+objects.get(b).getWidth();
                    Y=obImageY;
                    //Log.i(TAG, "collisionX: 111/"+obImage.getX()+"/"+objects.get(b).getImage().getX()+"/"+objects.get(b).getWidth());
                    collisionX(movingObject,objects.get(b));
                    return ;
                }
                else if (objects.get(b).getVx()==0&&objects.get(b).getVy()==0)
                {
                    objects.get(b).setCollision(0);
                    throwPig(objects.get(b),size);
                    //move=new Move(activity);
                    //move.throwPig(objects.get(b),size);
                    return ;
                }
            }
        for (m=0;m<objects.size();m++)
            if (obImageX>=(objects.get(m).getImage().getX()-5*obImageWidth/6)&&obImageX<=(objects.get(m).getImage().getX()-obImageWidth/6+objects.get(m).getWidth())&&obImageY>(objects.get(m).getImage().getY()-(obImageHeight))&&obImageY<objects.get(m).getImage().getY()&&objects.get(m).getCollision()==1)
            {
                if (objects.get(m) instanceof Box||objects.get(m) instanceof Bird)
                {
                    X=obImageX;
                    Y=objects.get(m).getImage().getY()-obImageHeight;
                    finish=true;
                    collisionY(movingObject,objects.get(m));
                    return ;
                }
                else if (objects.get(m).getVx()==0&&objects.get(m).getVy()==0)
                {
                    objects.get(m).setCollision(0);
                    throwPig(objects.get(m),size);
                    //move=new Move(activity);
                    //move.throwPig(objects.get(m),size);
                    return ;
                }
            }
        for (n=0;n<objects.size();n++)
            if (obImageX>=(objects.get(n).getImage().getX()-5*obImageWidth/6)&&obImageX<=(objects.get(n).getImage().getX()-obImageWidth/6+objects.get(n).getWidth())&&obImageY<(objects.get(n).getImage().getY()+(objects.get(n).getHeight()))&&obImageY>objects.get(n).getImage().getY()&&objects.get(n).getCollision()==1)
            {
                X=obImageX;
                Y=objects.get(n).getImage().getY()+objects.get(n).getHeight();
                finish=true;
                collisionY(movingObject,objects.get(n));
                return ;
            }
    }

    private void collisionY(MovingObject movingObject,MovingObject moveOb)
    {
        double VX,VY;
        //Log.i(TAG, "collisionX: 2");
        i=1;
        j=-1;
        VX=movingObject.getVx();
        VY=movingObject.getVy();
        Vy=-movingObject.getVy()*0.7;
        Vx= movingObject.getVx()*0.7;
        if (movingObject instanceof Pig && (Vy>200||Vy<-200))
        {
            movingObject.setCollision(0);
            throwPig(movingObject,size);
            //move=new Move(activity);
            //move.throwPig(movingObject,size);
        }
        else
        {
            //movingObject.getImage().setX((float) (Vx*0.01+X));
            //movingObject.getImage().setY((float) (Y+(0.5*2500*j*j*0.0001)+(Vy*j*0.01)));
            movingObject.setVx(Vx);
            movingObject.setVy(Vy);
            if (moveOb.getVx()==0)
            {
                moveOb.setVx(VX*0.6);
                moveOb.setVy(VY*0.6);
                if (moveOb instanceof Pig && (VY*0.6>200||VY*0.6<-200))
                {
                    moveOb.setCollision(0);
                    throwPig(moveOb,size);
                    //move=new Move(activity);
                    //move.throwPig(moveOb,size);
                }
                else if (VY!=0&&VX!=0)
                {
                    move=new Move(activity);
                    move.throwMoveOb(moveOb.getImage().getX(),moveOb.getImage().getY(),moveOb,objects,countBird,countpig);
                }
            }
        }
    }

    private void collisionX(MovingObject movingObject,MovingObject moveOb)
    {
        double VX,VY;
        i=1;
        j=-1;
        VX=movingObject.getVx();
        VY=movingObject.getVy();
        Vx=-movingObject.getVx()*0.7;
        Vy= movingObject.getVy()*0.7;
        //movingObject.getImage().setX((float) (Vx*0.01+X));
        //movingObject.getImage().setY((float) (Y+(0.5*2500*j*j*0.0001)+(Vy*j*0.01)));
        movingObject.setVx(Vx);
        movingObject.setVy(Vy);
        if (moveOb.getVx()==0)
        {
            moveOb.setVx(VX*0.6);
            moveOb.setVy(VY*0.6);
            if (VX!=0&&VY!=0)
            {
                move=new Move(activity);
                move.throwMoveOb(moveOb.getImage().getX(),moveOb.getImage().getY(),moveOb,objects,countBird,countpig);
            }
        }
    }

    private void throwPig(final MovingObject pig, final Point size)
    {
        final float primaryPosY=pig.getImage().getY();
        pigcount++;
        pig.setVy(10);
        pig.setVx(0);
        pig.getAudio().start();
        pigThrow=new Timer();
        pigThrow.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (pig.getImage().getY()>=primaryPosY-100&&pig.getVy()>0)
                        {
                            pig.getImage().setY(pig.getImage().getY()-15);
                            pig.setVy(10);
                            pig.setVx(0);
                        }
                        else
                        {
                            pig.getImage().setY(pig.getImage().getY()+15);
                            pig.setVy(-10);
                            pig.setVx(0);
                        }
                        if (pig.getImage().getY()>size.y)
                        {
                            pigThrow.purge();
                            pigThrow.cancel();
                            pig.setVx(0);
                            pig.setVy(0);
                            pig.getImage().setVisibility(View.INVISIBLE);
                            pig.setHide(0);
                        }
                    }
                });
            }
        },0,20);
    }
}

