package com.kukumoraketo.emojibrowser.Emoji.EmojiDb;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiCategory;
import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiFull;
import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiLite;
import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiTone;
import com.kukumoraketo.emojibrowser.R;
import com.kukumoraketo.emojibrowser.Utils.StringUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 29.4.2017.
 */

public class EmojiDb {

    private static final String TAG = "EmojiBrowser-EmojiDb";

    private static final String PREFS_NAME = "EmojiDbPreferences";
    private static final String IS_DB_FILLED_PREFS = "is_db_filled";
    private static final String EMOJI_METADATA_VERSION_PREFS = "emoji_metadata_version";

    private static int LATEST_EMOJI_METADATA_VERSION = 1;

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

        if (isDbFilled) {
            int metadataVersion = preferences.getInt(EMOJI_METADATA_VERSION_PREFS, 0);
            if (metadataVersion == LATEST_EMOJI_METADATA_VERSION)
                return true;
            else
                clearDatabase();
        }

        // if not filled it fills it
        try {
            fillDb();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(IS_DB_FILLED_PREFS, true);
            editor.putInt(EMOJI_METADATA_VERSION_PREFS, LATEST_EMOJI_METADATA_VERSION);
            editor.apply();
        }
        catch (Exception e){
            // TODO
        }

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

            // inserts EmojiCategory
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

    public List<EmojiLite> getAllEmojiLite(){

        List<EmojiLite> r = new ArrayList<>();

        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery(this.context.getString(R.string.SQL_select_all_emojilite), null);

        while (c.moveToNext()){
            String unicode = c.getString(0);
            int hasToneint = c.getInt(1);
            boolean hasTone = ((hasToneint == 1)? true : false);
            int tone = c.getInt(2);
            int emojiOrder = c.getInt(3);
            EmojiCategory category = EmojiCategory.stringToCategory(c.getString(4));

            EmojiLite emoji = new EmojiLite(unicode, hasTone, tone, emojiOrder,category);

            r.add(emoji);

        }

        return r;
    }

    public List<String> getUnicodeByKeyword(String[] keywords){
        List<String> r = new ArrayList<>();

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append(this.context.getString(R.string.SQL_select_code_by_keywords_pt_first));
        sqlBuilder.append(this.context.getString(R.string.SQL_select_code_by_keyword_pt_1));
        DatabaseUtils.appendEscapedSQLString(sqlBuilder, keywords[0] + "%");
        for (int i = 1; i < keywords.length; i++){
            sqlBuilder.append(this.context.getString(R.string.SQL_select_code_by_keyword_pt_2));
            DatabaseUtils.appendEscapedSQLString(sqlBuilder, keywords[i] + "%");
        }
        sqlBuilder.append(this.context.getString(R.string.SQL_select_code_by_keyword_pt_last));

        String sql = sqlBuilder.toString();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            r.add(cursor.getString(0));
        }

        cursor.close();
        db.close();

        return r;
    }

    public EmojiFull getEmojiFull(String unicode){
        String emojiUnicode;
        boolean hasTone;
        EmojiTone tone;
        int emojiOrder;
        EmojiCategory category;
        String name;
        String shortName;
        List<String> keywords = new ArrayList<>();



        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String sql = this.context.getString(R.string.SQL_select_emojifull_by_code);
        String[] args =  {unicode};

        Cursor c = db.rawQuery(sql, args);

        c.moveToNext();

        emojiUnicode = c.getString(0);
        name = c.getString(1);
        shortName = c.getString(2);
        hasTone = (c.getInt(3) == 1) ? true : false;
        tone = EmojiTone.getTone(c.getInt(4));
        emojiOrder = c.getInt(5);
        category = EmojiCategory.stringToCategory(c.getString(6));
        keywords.add(c.getString(6));

        while (c.moveToNext()){
            keywords.add(c.getString(7));
        }

        EmojiFull r = new EmojiFull(emojiUnicode, hasTone, tone, emojiOrder, category, name, shortName, keywords);

        db.close();
        c.close();

        return r;

    }

    private void clearDatabase(){
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.execSQL(this.context.getString(R.string.SQL_delete_category));
        db.execSQL(this.context.getString(R.string.SQL_delete_emoji));
        db.execSQL(this.context.getString(R.string.SQL_delete_keyword));
        db.execSQL(this.context.getString(R.string.SQL_delete_emoji_keyword));

        db.close();
    }

}
