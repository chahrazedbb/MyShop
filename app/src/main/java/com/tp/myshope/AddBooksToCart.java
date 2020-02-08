package com.tp.myshope;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HOME on 20/04/2017.
 */

public class AddBooksToCart extends AppCompatActivity {
    public static Bitmap bt = null;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.details_product);
        } else {
            setContentView(R.layout.details_product_two);
        }
        TextView e1 = (TextView) findViewById(R.id.name);
        TextView e2 = (TextView) findViewById(R.id.price);
        TextView e3 = (TextView) findViewById(R.id.description);
        TextView e4 = (TextView) findViewById(R.id.longdescription);
        image = (ImageView) findViewById(R.id.image);
        // addition
        e1.setText(BookFragment.ptitle);
        e2.setText(String.valueOf(BookFragment.pprice) + "$");
        e3.setText(BookFragment.pdescription);
        e4.setText(BookFragment.plongdescription);
        bt = BitmapFactory.decodeByteArray(BookFragment.pimage, 0, BookFragment.pimage.length);
        image.setImageBitmap(bt);
    }


    public void addtocart(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to bay this product ?");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Product p = new Product(BookFragment.ptitle, BookFragment.pimage, BookFragment.pprice,  BookFragment.pdescription, BookFragment.plongdescription);
                ProductBase data = new ProductBase(AddBooksToCart.this);
                try {
                    data.open();

                } catch (Exception e) {
                    Log.i("hi there !", "hi there !");
                }
                data.addBook(p);
                Toast.makeText(AddBooksToCart.this, "Added :" + " " + p.getProductName() + " to Cart", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                AddBooksToCart.super.onBackPressed();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AddBooksToCart.super.onBackPressed();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}