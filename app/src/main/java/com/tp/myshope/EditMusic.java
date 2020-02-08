package com.tp.myshope;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by HOME on 20/04/2017.
 */

public class EditMusic extends AppCompatActivity {
    byte[] b ;
    public static final int RESULT_LOAD_IMAGE = 1 ;
    public static Bitmap bt = null;
    ImageView image  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.edit_layout);
        } else {
            setContentView(R.layout.edit_layout_two);
        }
        EditText e1 = (EditText)findViewById(R.id.name);
        EditText e2 = (EditText)findViewById(R.id.price);
        EditText e3 = (EditText)findViewById(R.id.description);
        EditText e4 = (EditText)findViewById(R.id.longdescription);

        image = (ImageView) findViewById(R.id.image);
        // addition
        e1.setText(MusicFragment.ptitle);
        e2.setText(String.valueOf(MusicFragment.pprice));
        e3.setText(MusicFragment.pdescription);
        e4.setText(MusicFragment.plongdescription);
        bt = BitmapFactory.decodeByteArray(MusicFragment.pimage, 0,MusicFragment.pimage.length);

        image.setImageBitmap(bt);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery , RESULT_LOAD_IMAGE);
            }
        });
        ;
    }


    public void edit(View v)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to save changes ?");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                EditText e1 = (EditText)findViewById(R.id.name);
                EditText e2 = (EditText)findViewById(R.id.price);
                EditText e3 = (EditText)findViewById(R.id.description);
                EditText e4 = (EditText)findViewById(R.id.longdescription);

                MusicFragment.image_details.get(MusicFragment.i).setProductName(String.valueOf(e1.getText()));
                MusicFragment.image_details.get(MusicFragment.i).setPrice(Double.parseDouble(String.valueOf(e2.getText())));
                MusicFragment.image_details.get(MusicFragment.i).setDescription(String.valueOf(e3.getText()));
                MusicFragment.image_details.get(MusicFragment.i).setLongDescription(String.valueOf(e4.getText()));

                if(b != null) {
                    MusicFragment.image_details.get(MusicFragment.i).setProductImage(b);
                }

                MusicFragment.myadapter = new ProductAdapter(EditMusic.this, MusicFragment.image_details) ;
                MusicFragment.listView.setAdapter(MusicFragment.myadapter);

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                EditMusic.super.onBackPressed();
            }
        });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditMusic.super.onBackPressed();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



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
                bt = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bt.compress(Bitmap.CompressFormat.PNG, 100, stream);
                b = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
