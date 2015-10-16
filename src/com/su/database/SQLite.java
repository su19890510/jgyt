package com.su.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月16日 下午8:56:40
 */
public class SQLite extends SQLiteOpenHelper {
    
    private static final int DEFAULT_VERSION = 100;
    
    private static final String DATABASE_NAME = "jgyt.db";

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DEFAULT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS SECURITY(name VARCHAR(64) NOT NULL, value VARCHAR(256) NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}