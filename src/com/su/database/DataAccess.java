package com.su.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月16日 下午8:56:18
 */
public class DataAccess {

    private static final String OPEN_ID_KEY = "u.open.id";
    private static final String APP_ID_KEY = "u.app.id";
    private static final String ACCESS_TOKEN_ID_KEY = "u.access.token";

    private static SQLite sqLite;

    public static void init(Context context) {
        DataAccess.sqLite = new SQLite(context);
    }

    public static void saveSecurity(String openId, String appId, String accessToken) {
        if (openId == null && appId == null && accessToken == null) {
            return;
        }
        SQLiteDatabase database = getWritableDatabase();
        if (openId != null) {
            String _openId = getOpenId();
            if (_openId == null) {
                database.execSQL("INSERT INTO SECURITY VALUES(?, ?)", new Object[] {OPEN_ID_KEY, openId});
            } else {
                ContentValues cv = new ContentValues();
                cv.put("value", openId);
                database.update("SECURITY", cv, "name = ?", new String[] { OPEN_ID_KEY });
            }
        }
        if (appId != null) {
            String _appId = getAppId();
            if (_appId == null) {
                database.execSQL("INSERT INTO SECURITY VALUES(?, ?)", new Object[] {APP_ID_KEY, appId});
            } else {
                ContentValues cv = new ContentValues();
                cv.put("value", appId);
                database.update("SECURITY", cv, "name = ?", new String[] { APP_ID_KEY });
            }
        }
        if (accessToken != null) {
            String _accessToken = getAccessToken();
            if (_accessToken == null) {
                database.execSQL("INSERT INTO SECURITY VALUES(?, ?)", new Object[] {ACCESS_TOKEN_ID_KEY, accessToken});
            } else {
                ContentValues cv = new ContentValues();
                cv.put("value", accessToken);
                database.update("SECURITY", cv, "name = ?", new String[] { ACCESS_TOKEN_ID_KEY });
            }
        }
    }

    public static String getOpenId() {
        String openId = null;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT value FROM SECURITY WHERE name = ?", new String[] {OPEN_ID_KEY});
        while (cursor.moveToNext()) {
            openId = cursor.getString(cursor.getColumnIndex("value"));
            Log.v("JGYT", "Query Saved openId => " + openId);
        }
        cursor.close();
        return openId;
    }

    public static String getAppId() {
        String appId = null;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT value FROM SECURITY WHERE name = ?", new String[] {APP_ID_KEY});
        while (cursor.moveToNext()) {
            appId = cursor.getString(cursor.getColumnIndex("value"));
            Log.v("JGYT", "Query Saved appId => " + appId);
        }
        cursor.close();
        return appId;
    }

    public static String getAccessToken() {
        String accessToken = null;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT value FROM SECURITY WHERE name = ?", new String[] {ACCESS_TOKEN_ID_KEY});
        while (cursor.moveToNext()) {
            accessToken = cursor.getString(cursor.getColumnIndex("value"));
            Log.v("JGYT", "Query Saved accessToken => " + accessToken);
        }
        cursor.close();
        return accessToken;
    }

    public static SQLiteDatabase getReadableDatabase() {
        return sqLite.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase() {
        return sqLite.getWritableDatabase();
    }
}
