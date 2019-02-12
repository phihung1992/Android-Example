package com.hung.demosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by HUNG on 24/5/2017.
 */

public class DBManager {
    private static final String DB_PATH = Environment
            .getDataDirectory() + "/data/com.hung.demosqlite/database";
    private static final String DB_NAME = "db_class.sqlite";
    public static final String TABLE_STUDENT = "student";
    private static final String SQL_QUERRY = "SELECT * FROM " + TABLE_STUDENT;
    private SQLiteDatabase mSQLiteDB;
    private Context mContext;

    public DBManager(Context context) {
        mContext = context;
        copyDatabase();
    }

    private void copyDatabase() {
        new File(DB_PATH).mkdir();
        try {
            File file = new File(DB_PATH + "/" + DB_NAME);
            if (file.exists()) {
                return;
            }
            InputStream input = mContext.getAssets().open(DB_NAME);
            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);

            int len;
            byte buff[] = new byte[1024];
            while ((len = input.read(buff)) > 0) {
                output.write(buff, 0, len);
            }

            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDB() {
        if ((mSQLiteDB == null) || !mSQLiteDB.isOpen()) {
            mSQLiteDB = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME,
                    null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDB() {
        if (mSQLiteDB != null || mSQLiteDB.isOpen()) {
            mSQLiteDB.close();
        }
    }

    public ArrayList<Student> getListStudent() {
        openDB();

        Cursor c = mSQLiteDB.rawQuery(SQL_QUERRY, null);
        if (c == null) {
            return null;
        }

        int indexName = c.getColumnIndex("name");
        int indexAge = c.getColumnIndex("age");
        int indexGender = c.getColumnIndex("gender");

        String name, gender;
        int age;
        c.moveToFirst();

        ArrayList<Student> listStudent = new ArrayList<>();
        while (!c.isAfterLast()) {
            name = c.getString(indexName);
            gender = c.getString(indexGender);
            age = c.getInt(indexAge);

            listStudent.add(new Student(name, age, gender));
            c.moveToNext();
        }
        c.close();
        closeDB();

        return listStudent;
    }

    public boolean insert(String tableName, String[] columns, String[] dataColumns) {
        openDB();

        ContentValues values = new ContentValues();
        for (int i = 0; i < columns.length; ++i) {
            values.put(columns[i], dataColumns[i]);
        }
        long result = mSQLiteDB.insert(tableName, null, values);

        closeDB();
        return result > -1;
    }

    public boolean delete(String tableName, String whereClause, String[] whereArgs) {
        openDB();
        long result = mSQLiteDB.delete(tableName, whereClause, whereArgs);

        closeDB();
        return result > 0;
    }

    public boolean update(String tableName, String[] columns, String[] dataColumns,
                          String whereClause, String[] whereAgrs) {
        openDB();
        ContentValues values = new ContentValues();
        for (int i = 0; i < columns.length; ++i) {
            values.put(columns[i], dataColumns[i]);
        }
        long result = mSQLiteDB.update(tableName, values, whereClause, whereAgrs);
        closeDB();
        return result > 0;
    }
}
