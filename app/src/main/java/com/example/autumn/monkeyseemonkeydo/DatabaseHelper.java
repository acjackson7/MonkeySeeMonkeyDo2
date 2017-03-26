package com.example.autumn.monkeyseemonkeydo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Autumn on 3/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "data/data/MonkeySeeMonkeyDo/databases/";
    private static String DB_NAME = "myDBName";
    private SQLiteDatabase dict;
    private final Context myContext;

    public DatabaseHelper(Context context){
        super(context, DB_NAME,null,1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDatabase();

        if(dbExist){

        }
        else{
            this.getReadableDatabase();

            try{
                copyDatabase();
            }
            catch(IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch(SQLiteException e){

        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDatabase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFilename = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFilename);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    public void openDatabase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
        dict = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close(){
        if(dict != null){
                dict.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public String getVideo(String target){
        String myQuery;
        String result = "";

        openDatabase();

        try {
            myQuery = "SELECT link FROM word_link WHERE word = " + target;
            Cursor cursor = dict.rawQuery(myQuery,null);
            if(cursor != null){
                cursor.moveToFirst();
                result = cursor.getString(cursor.getColumnIndex("link"));
            }

        }
        finally{

        }
        return result;
    }
}
