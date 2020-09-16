package com.example.admin.angrybirds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import com.example.admin.angrybirds.model.Box;
import com.example.admin.angrybirds.model.MovingObject;
import com.example.admin.angrybirds.model.Pig;
import com.example.admin.angrybirds.model.RedBird;

import java.util.ArrayList;
import java.util.List;



public class DataBaseManager extends SQLiteOpenHelper {

    int countbird,countbox,countpig;
    public static final int DB_VERSION=1;
    private static final String TAG = "DatabaseManager";
    public static final String TB_NAME="angry_bird_tb";
    public static final String COL_ID="col_id";
    public static final String COL_HEIGHT="col_height";
    public static final String COL_WIDTH="col_width";
    public static final String COL_BIRD_X="col_bird_x";
    public static final String COL_BIRD_Y="col_bird_y";
    public static final String COL_VX="col_vx";
    public static final String COL_VY="col_vy";
    public static final String COL_ENABLE="col_enable";
    public static final String COL_COLLISION="col_collision";
    public static final String COL_COUNTBIRD="col_BIRD";
    public static final String COL_COUNTBOX="col_BOX";
    public static final String COL_COUNTPIG="col_PIG";

    public static final String DB_COMMAND="CREATE TABLE IF NOT EXISTS "+TB_NAME+"("+
            COL_ID+" INTEGER,"+
            COL_HEIGHT+" INTEGER,"+
            COL_WIDTH+" INTEGER,"+
            COL_BIRD_X+" FLOAT,"+
            COL_BIRD_Y+" FLOAT,"+
            COL_VX+" DOUBLE,"+
            COL_VY+" DOUBLE,"+
            COL_ENABLE+" INTEGER,"+
            COL_COUNTBIRD+" INTEGER,"+
            COL_COUNTBOX+" INTEGER,"+
            COL_COUNTPIG+" INTEGER,"+
            COL_COLLISION+" INTEGER);";
    private Context context;


    public DataBaseManager(Context context,String name) {
        super(context, name, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(DB_COMMAND);
        }
        catch (SQLException e)
        {
            Log.e(TAG, "onCreate: "+e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addObject(MovingObject movingObject,int bird,int box,int pig)
    {
        ContentValues cv=new ContentValues();
        cv.put(COL_ID,movingObject.getId());
        cv.put(COL_HEIGHT,movingObject.getHeight());
        cv.put(COL_WIDTH,movingObject.getWidth());
        cv.put(COL_BIRD_X,movingObject.getImage().getX());
        cv.put(COL_BIRD_Y,movingObject.getImage().getY());
        cv.put(COL_VX,movingObject.getVx());
        cv.put(COL_VY,movingObject.getVy());
        cv.put(COL_ENABLE,movingObject.getHide());
        cv.put(COL_COUNTBOX,box);
        cv.put(COL_COUNTPIG,pig);
        cv.put(COL_COUNTBIRD,bird);
        cv.put(COL_COLLISION,movingObject.getCollision());
        SQLiteDatabase sql=this.getWritableDatabase();
        sql.insert(TB_NAME,null,cv);
    }

    public void addObjects(List<MovingObject> movingObjects,int bird,int box,int pig)
    {
        for (int i = 0; i < movingObjects.size(); i++) {
                addObject(movingObjects.get(i),bird,box,pig);
        }
    }

    public List<MovingObject> getObjects()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM "+TB_NAME,null);
        cursor.moveToFirst();
        countbird =cursor.getInt(8);
        countbox =cursor.getInt(9);
        countpig =cursor.getInt(10);
        List<MovingObject> objects=new ArrayList<>();
        if(cursor.getCount()>0)
        {
            while(cursor.getPosition()<countbird)
            {
                RedBird redBird=new RedBird(context);
                redBird.setId(cursor.getInt(0));
                redBird.setHeight(cursor.getInt(1));
                redBird.setWidth(cursor.getInt(2));
                redBird.getImage().setX(cursor.getFloat(3));
                redBird.getImage().setY(cursor.getFloat(4));
                redBird.setVx(cursor.getDouble(5));
                redBird.setVy(cursor.getDouble(6));
                if (cursor.getInt(7)==0)
                    redBird.getImage().setVisibility(View.INVISIBLE);
                redBird.setCollision(cursor.getInt(11));
                objects.add(redBird);
                cursor.moveToNext();
            }
            while(cursor.getPosition()<countbox+countbird)
            {
                Box box=new Box(context);
                box.setId(cursor.getInt(0));
                box.setHeight(cursor.getInt(1));
                box.setWidth(cursor.getInt(2));
                box.getImage().setX(cursor.getFloat(3));
                box.getImage().setY(cursor.getFloat(4));
                box.setVx(cursor.getDouble(5));
                box.setVy(cursor.getDouble(6));
                if (cursor.getInt(7)==0)
                    box.getImage().setVisibility(View.INVISIBLE);
                box.setCollision(cursor.getInt(11));
                objects.add(box);
                cursor.moveToNext();
            }

            while(cursor.getPosition()<countbox+countbird+countpig)
            {
                Pig pig=new Pig(context);
                pig.setId(cursor.getInt(0));
                pig.setHeight(cursor.getInt(1));
                pig.setWidth(cursor.getInt(2));
                pig.getImage().setX(cursor.getFloat(3));
                pig.getImage().setY(cursor.getFloat(4));
                pig.setVx(cursor.getDouble(5));
                pig.setVy(cursor.getDouble(6));
                if (cursor.getInt(7)==0)
                    pig.getImage().setVisibility(View.INVISIBLE);
                pig.setCollision(cursor.getInt(11));
                objects.add(pig);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return objects;
    }

    public int bird()
    {
        return countbird;
    }

    public int pig()
    {
        return countpig;
    }

    public int box()
    {
        return countbox;
    }

    public void deleteAll()
    {
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+ TB_NAME);
    }
}
