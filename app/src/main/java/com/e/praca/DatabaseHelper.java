package com.e.praca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "td_data";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LABEL = "labels";
    private static final String TABLE_TASK = "tasks";
    private static final String TABLE_LABELS_TASKS = "labels_and_tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LABELSID = "labels_id";
    private static final String KEY_TASKSID = "tasks_id";

    private static final String CREATE_TABLE_TASK =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_TASK+"("+KEY_ID+" INTEGER PRIMARY KEY," + KEY_NAME+" TEXT);";
    private static final String CREATE_TABLE_LABEL =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_LABEL+"("+KEY_ID+" INTEGER PRIMARY KEY," + KEY_NAME+" TEXT,"+ KEY_LATITUDE+" NUMBER(30,27),"+KEY_LONGITUDE+" NUMBER(30,27) );";
    private static final String CREATE_TABLE_LABELS_TASKS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_LABELS_TASKS + "(" + KEY_LABELSID + " INTEGER NOT NULL," + KEY_TASKSID +
                    " INTEGER NOT NULL, PRIMARY KEY ("+KEY_LABELSID+","+KEY_TASKSID+"), FOREIGN KEY("+
                    KEY_LABELSID+") REFERENCES "+TABLE_LABEL+"("+KEY_ID+"), FOREIGN KEY("+
                    KEY_TASKSID+") REFERENCES "+TABLE_TASK+"("+KEY_ID+"));";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_LABEL);
        sqLiteDatabase.execSQL(CREATE_TABLE_TASK);
        sqLiteDatabase.execSQL(CREATE_TABLE_LABELS_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_TASK+";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_LABEL+";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_LABELS_TASKS+";");
        onCreate(sqLiteDatabase);
    }

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table", CREATE_TABLE_TASK);
        Log.d("table", CREATE_TABLE_LABEL);
        Log.d("table", CREATE_TABLE_LABELS_TASKS);
    }


    public boolean insertLabel(String name, long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_LONGITUDE, 0);
        contentValues.put(KEY_LATITUDE, 0);
        long result = db.insert(TABLE_LABEL, null, contentValues);
        return result != -1;
    }

    public boolean insertLabel(String name, long id, double longitude, double latitude)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_LONGITUDE, longitude);
        contentValues.put(KEY_LATITUDE, latitude);
        long result = db.insert(TABLE_LABEL, null, contentValues);
        return result != -1;
    }

    public Cursor viewData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+TABLE_LABEL;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void addLabelArraytoDatabase(ArrayList<Label> labelArrayList)
    {
        for (Label o:labelArrayList)
        {
            insertLabel(o.getName(),o.getLabelid());
        }
    }

    public boolean insertTask(String name, long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_ID, id);
        long result = db.insert(TABLE_TASK, null, contentValues);
        return result != -1;
    }


    public void addTaskArraytoDatabase(ArrayList<Task> taskArrayList)
    {
        for (Task o:taskArrayList)
        {
            insertLabel(o.getName(),o.getTaskId());
        }
    }


    public boolean insertLabelsTasks(long labelId, long taskId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_LABELSID, labelId);
        contentValues.put(KEY_TASKSID, taskId);
        long result = db.insert(TABLE_LABELS_TASKS, null, contentValues);
        return result != -1;
    }

    public void addTasksLabelsArraytoDatabase(ArrayList<LabelTask> labelTaskArrayList)
    {
        for (LabelTask o:labelTaskArrayList)
        {
            insertLabelsTasks(o.getLabelId(),o.getTaskId());
        }
    }

}
