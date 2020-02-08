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

public class AddMusicToCart extends AppCompatActivity {
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
        e1.setText(MusicFragment.ptitle);
        e2.setText(String.valueOf(MusicFragment.pprice) + "$");
        e3.setText(MusicFragment.pdescription);
        e4.setText(MusicFragment.plongdescription);
        bt = BitmapFactory.decodeByteArray(MusicFragment.pimage, 0, MusicFragment.pimage.length);
        image.setImageBitmap(bt);
    }


    public void addtocart(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to bay this product ?");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Product p = new Product(MusicFragment.ptitle, MusicFragment.pimage, MusicFragment.pprice, MusicFragment.pdescription,MusicFragment.plongdescription);
                ProductBase data = new ProductBase(AddMusicToCart.this);
                try {
                    data.open();

                } catch (Exception e) {
                    Log.i("hi there !", "hi there !");
                }
                data.addMusic(p);
                Toast.makeText(AddMusicToCart.this, "Added :" + " " + p.getProductName() + " to Cart", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                AddMusicToCart.super.onBackPressed();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AddMusicToCart.super.onBackPressed();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}