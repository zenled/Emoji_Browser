package com.kukumoraketo.emojibrowser.Emoji.EmojiDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zed on 29.4.2017.
 */

public class EmojiDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Emoji.db";


    public EmojiDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates Database
        db.execSQL("CREATE TABLE `Category` ( `id` INTEGER NOT NULL UNIQUE, `category` TEXT NOT NULL, PRIMARY KEY(`id`) )");
        db.execSQL("CREATE TABLE \"Emoji\" ( `id` INTEGER NOT NULL UNIQUE, `code` TEXT NOT NULL UNIQUE, `name` TEXT NOT NULL, `short_name` TEXT, `has_tone` INTEGER NOT NULL, `tone` INTEGER, `emoji_order` INTEGER NOT NULL UNIQUE, `category_id` INTEGER NOT NULL, PRIMARY KEY(`id`) )");
        db.execSQL("CREATE TABLE `Keyword` ( `id` INTEGER NOT NULL UNIQUE, `keyword` TEXT NOT NULL, PRIMARY KEY(`id`) )");
        db.execSQL("CREATE TABLE `Emoji_Keyword` ( `emoji_id` INTEGER NOT NULL, `keyword_id` INTEGER NOT NULL, PRIMARY KEY(`emoji_id`,`keyword_id`) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
