package com.tp.myshope;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HOME on 20/04/2017.
 */

public class MyBase extends SQLiteOpenHelper {

    public static final String MUSIC_TABLE = "music";
    public static final String IMAGE_TABLE = "image";
    public static final String BOOK_TABLE = "book";

    public static final String COL_ID = "id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_IMAGE = "image";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_LONG_DESCRIPTION = "longdescription";

    private static final int D_VERSION = 1;

    private static final String DB_NAME = "myproducts.db";

    private static final String DB_CREATION_MUSIC =
            "create table "+ MUSIC_TABLE + "("
                    + COL_ID + " integer primary key autoincrement, "
                    + PRODUCT_NAME + " text, "
                    + PRODUCT_IMAGE + "  BLOB, "
                    + PRODUCT_DESCRIPTION + " text, "
                    + PRODUCT_PRICE + " REAL,"
                    +PRODUCT_LONG_DESCRIPTION+" text );"
            ;

    private static final String DB_CREATION_IMAGE =
            "create table "+ IMAGE_TABLE + "("
                    + COL_ID + " integer primary key autoincrement, "
                    + PRODUCT_NAME + " text, "
                    + PRODUCT_IMAGE + "  BLOB, "
                    + PRODUCT_DESCRIPTION + " text, "
                    + PRODUCT_PRICE + " REAL,"
                    +PRODUCT_LONG_DESCRIPTION+" text );"
            ;

    private static final String DB_CREATION_BOOK =
            "create table "+ BOOK_TABLE + "("
                    + COL_ID + " integer primary key autoincrement, "
                    + PRODUCT_NAME + " text, "
                    + PRODUCT_IMAGE + "  BLOB, "
                    + PRODUCT_DESCRIPTION + " text, "
                    + PRODUCT_PRICE + " REAL,"
                    +PRODUCT_LONG_DESCRIPTION+" text );"
            ;

    public MyBase(Context c) {
        super(c, DB_NAME, null, D_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bdd) {

        bdd.execSQL(DB_CREATION_MUSIC);
        bdd.execSQL(DB_CREATION_IMAGE);
        bdd.execSQL(DB_CREATION_BOOK);


    }

    @Override
    public void onUpgrade(SQLiteDatabase bdd, int ancVersion, int nouvVersion) {
        bdd.execSQL("DROP TABLE IF EXISTS" + MUSIC_TABLE);
        bdd.execSQL("DROP TABLE IF EXISTS" + IMAGE_TABLE);
        bdd.execSQL("DROP TABLE IF EXISTS" + BOOK_TABLE);

        onCreate(bdd);
    }


}
