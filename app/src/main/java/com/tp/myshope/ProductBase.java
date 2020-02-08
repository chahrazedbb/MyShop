package com.tp.myshope;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME on 20/04/2017.
 */

public class ProductBase {
    MyBase mybase;
    SQLiteDatabase db;

    String[] Columns = {MyBase.PRODUCT_NAME,MyBase.PRODUCT_IMAGE,MyBase.PRODUCT_DESCRIPTION,MyBase.PRODUCT_PRICE,MyBase.PRODUCT_LONG_DESCRIPTION};

    public ProductBase(Context c) {
        mybase = new MyBase(c);
    }

    public void open() throws SQLException {
        db = mybase.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public void addMusic(Product p){
        ContentValues v = new ContentValues();

        v.put(mybase.PRODUCT_NAME,p.getProductName());
        v.put(mybase.PRODUCT_IMAGE,p.getProductImage());
        v.put(mybase.PRODUCT_DESCRIPTION,p.getDescription());
        v.put(mybase.PRODUCT_PRICE,p.getPrice());
        v.put(mybase.PRODUCT_LONG_DESCRIPTION,p.getLongDescription());

        db.insert(mybase.MUSIC_TABLE, null, v);

    }

    public void addImage(Product p){
        ContentValues v = new ContentValues();

        v.put(mybase.PRODUCT_NAME,p.getProductName());
        v.put(mybase.PRODUCT_IMAGE,p.getProductImage());
        v.put(mybase.PRODUCT_DESCRIPTION,p.getDescription());
        v.put(mybase.PRODUCT_PRICE,p.getPrice());
        v.put(mybase.PRODUCT_LONG_DESCRIPTION,p.getLongDescription());

        db.insert(mybase.IMAGE_TABLE, null, v);

    }

    public void addBook(Product p){
        ContentValues v = new ContentValues();

        v.put(mybase.PRODUCT_NAME,p.getProductName());
        v.put(mybase.PRODUCT_IMAGE,p.getProductImage());
        v.put(mybase.PRODUCT_DESCRIPTION,p.getDescription());
        v.put(mybase.PRODUCT_PRICE,p.getPrice());
        v.put(mybase.PRODUCT_LONG_DESCRIPTION,p.getLongDescription());

        db.insert(mybase.BOOK_TABLE, null, v);

    }

    public List<Product> getMusic(){
        List<Product> products = new ArrayList<Product>();

        Cursor cur = db.query(mybase.MUSIC_TABLE, Columns, null, null, null, null, null);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Product p = curVersMar(cur);
            products.add(p);
            cur.moveToNext();
        }
        cur.close();
        return products;
    }
    public List<Product> getImage(){
        List<Product> products = new ArrayList<Product>();

        Cursor cur = db.query(mybase.IMAGE_TABLE, Columns, null, null, null, null, null);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Product p = curVersMar(cur);
            products.add(p);
            cur.moveToNext();
        }
        cur.close();
        return products;
    }
    public List<Product> getBook(){
        List<Product> products = new ArrayList<Product>();

        Cursor cur = db.query(mybase.BOOK_TABLE, Columns, null, null, null, null, null);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Product p = curVersMar(cur);
            products.add(p);
            cur.moveToNext();
        }
        cur.close();
        return products;
    }

    public void deleteMusic(Product p){
        db.delete(MyBase.MUSIC_TABLE,MyBase.PRODUCT_NAME + " = '" + p.getProductName() + "'", null);
    }
    public void deleteImage(Product p){
        db.delete(MyBase.IMAGE_TABLE,MyBase.PRODUCT_NAME + " = '" + p.getProductName() + "'", null);
    }
    public void deleteBook(Product p){
        db.delete(MyBase.BOOK_TABLE,MyBase.PRODUCT_NAME + " = '" + p.getProductName() + "'", null);
    }

    public void deleteAllProducts(){
        mybase.onUpgrade(db,1,1);
    }


    private Product curVersMar(Cursor cur) {
        Product p = new Product();
        p.setProductName(cur.getString(0));
        p.setProductImage(cur.getBlob(1));
        p.setDescription(cur.getString(2));
        p.setPrice(cur.getDouble(3));
        p.setLongDescription(cur.getString(4));
        return p;
    }



}
