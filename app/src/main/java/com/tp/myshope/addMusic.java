package com.tp.myshope;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by HOME on 20/04/2017.
 */

public class addMusic extends AppCompatActivity {

    ImageView image ;
    byte[] b ;
    public static final int RESULT_LOAD_IMAGE = 1 ;
    public static int j;
    public double db = 0;
    public static EditText e1;
    public static EditText e2;
    public static EditText e3 ;
    public static EditText e4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.add_layout);
        } else {
            setContentView(R.layout.add_layout_two);
        }
        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery , RESULT_LOAD_IMAGE);
            }
        });
        ;
    }


    public void add(View v)
    {
         e1 = (EditText)findViewById(R.id.name);
         e2 = (EditText)findViewById(R.id.price);
         e3 = (EditText)findViewById(R.id.description);
        e4 = (EditText)findViewById(R.id.longdescription);
        if (b == null){
            Toast.makeText(getApplicationContext(), "You should add an image", Toast.LENGTH_LONG).show();
        }else if(e1.getText().equals(null)||e2.getText().equals(null)||e3.getText().equals(null))
        {
            Toast.makeText(getApplicationContext(), "You should fill all the fields ", Toast.LENGTH_LONG).show();
        }
        else{

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure, You wanted to add this product ?");
            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    String st = String.valueOf(e2.getText());
                    if(e2.getText().equals(null)) {
                        db = Double.parseDouble(st);
                    }
                    Product dvd = new Product(String.valueOf(e1.getText()),b,db,String.valueOf(e3.getText()),String.valueOf(e4.getText()));
                    MusicFragment.image_details.add(dvd);
                    MusicFragment.myadapter = new ProductAdapter(addMusic.this, MusicFragment.image_details) ;
                    MusicFragment.listView.setAdapter(MusicFragment.myadapter);

                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    addMusic.super.onBackPressed();

                    Toast.makeText(getApplicationContext(), "the product has been added", Toast.LENGTH_LONG).show();
                }
            });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    addMusic.super.onBackPressed();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                b = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
