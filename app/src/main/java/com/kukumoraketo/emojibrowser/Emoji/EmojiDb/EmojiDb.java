package com.kukumoraketo.emojibrowser.Emoji.EmojiDb;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.kukumoraketo.emojibrowser.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by zed on 29.4.2017.
 */

public class EmojiDb {

    private static final String TAG = "EmojiBrowser-EmojiDb";

    private static final String PREFS_NAME = "EmojiDbPreferences";
    private static final String IS_DB_FILLED_PREFS = "is_db_filled";

    private Context context;
    private EmojiDbHelper dbHelper;


    public EmojiDb(Context context){
        this.context = context;

        this.dbHelper = new EmojiDbHelper(this.context);

        checkDb();
    }

    /**
     * If the database is empty it fills it
     * @return true if database had to be filled
     */
    private boolean checkDb(){

        // Checks if database is already filled (from preferences)
        SharedPreferences preferences = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        boolean isDbFilled = preferences.getBoolean(IS_DB_FILLED_PREFS, false);

        if (isDbFilled)
            return true;

        // if not filled it fills it
        try {
            fillDb();
        }
        catch (Exception e){
            // TODO
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_DB_FILLED_PREFS, true);
        editor.commit();

        return false;
    }

    /**
     * Fills the Db with emoji data
     */
    private void fillDb() throws Exception{

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        try {
            String sql;
            SQLiteStatement statement;
            InputStream resourceStream;
            BufferedReader reader;

            String line;

            // inserts Category
            sql = this.context.getString(R.string.SQL_insert_category);
            statement = db.compileStatement(sql);

            resourceStream = this.context.getResources().openRawResource(R.raw.category);
            reader = new BufferedReader(new InputStreamReader(resourceStream));

            db.beginTransaction();
            while ((line = reader.readLine()) != null){
                JSONObject jsonObject = new JSONObject(line);

                statement.bindLong(1, jsonObject.getInt("id"));
                statement.bindString(2, jsonObject.getString("category"));

                statement.executeInsert();
                statement.clearBindings();
            }
            resourceStream.close();
            reader.close();

            db.setTransactionSuccessful();
            db.endTransaction();

            // inserts Keyword
            sql = this.context.getString(R.string.SQL_insert_keyword);
            statement = db.compileStatement(sql);

            resourceStream = this.context.getResources().openRawResource(R.raw.keyword);
            reader = new BufferedReader(new InputStreamReader(resourceStream));

            db.beginTransaction();
            while ((line = reader.readLine()) != null){
                JSONObject jsonObject = new JSONObject(line);

                statement.bindLong(1, jsonObject.getInt("id"));
                statement.bindString(2, jsonObject.getString("keyword"));

                statement.executeInsert();
                statement.clearBindings();
            }
            resourceStream.close();
            reader.close();

            db.setTransactionSuccessful();
            db.endTransaction();

            // inserts Emoji_Keyword
            sql = this.context.getString(R.string.SQL_insert_emoji_keyword);
            statement = db.compileStatement(sql);

            resourceStream = this.context.getResources().openRawResource(R.raw.emoji_keyword);
            reader = new BufferedReader(new InputStreamReader(resourceStream));

            db.beginTransaction();
            while ((line = reader.readLine()) != null){
                JSONObject jsonObject = new JSONObject(line);

                statement.bindLong(1, jsonObject.getInt("emoji_id"));
                statement.bindString(2, jsonObject.getString("keyword_id"));

                statement.executeInsert();
                statement.clearBindings();
            }
            resourceStream.close();
            reader.close();

            db.setTransactionSuccessful();
            db.endTransaction();

            // inserts Emoji
            sql = this.context.getString(R.string.SQL_insert_emoji);
            statement = db.compileStatement(sql);

            resourceStream = this.context.getResources().openRawResource(R.raw.emoji);
            reader = new BufferedReader(new InputStreamReader(resourceStream));

            db.beginTransaction();
            while ((line = reader.readLine()) != null){
                JSONObject jsonObject = new JSONObject(line);

                statement.bindLong(1, jsonObject.getInt("id"));
                statement.bindString(2, jsonObject.getString("code"));
                statement.bindString(3, jsonObject.getString("name"));
                statement.bindString(4, jsonObject.getString("short_name"));
                boolean hasTone = jsonObject.getBoolean("has_tone");
                statement.bindLong(5, (hasTone? 1 : 0));
                statement.bindLong(6, jsonObject.getInt("tone"));
                statement.bindLong(7, jsonObject.getInt("emoji_order"));
                statement.bindLong(8, jsonObject.getInt("category_id"));

                statement.executeInsert();
                statement.clearBindings();
            }
            resourceStream.close();
            reader.close();

            db.setTransactionSuccessful();
            db.endTransaction();

        }
        catch (Exception e){
            Log.v(TAG, "Error filling Database");
            throw new Exception("Error filling database",e.getCause());
        }
        finally {
            db.close();
        }

    }

}
