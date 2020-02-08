package com.tp.myshope;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sa90.materialarcmenu.ArcMenu;
import com.sa90.materialarcmenu.StateChangeListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME on 20/04/2017.
 */

public class ImageFragment extends Fragment {

    public static ListView listViewi ;
    public static List<Product> image_detailsi ;
    public static ProductAdapter myadapteri ;
    View viewv ;
    ArcMenu arcMenuAndroid;
    public  static String ptitlei ;
    public  static double ppricei ;
    public  static String pdescriptioni ;
    public  static byte[] pimagei ;
    public  static String plongdescriptioni ;
    public static int im ;
    public static int jm ;
    int k ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewv =  inflater.inflate(R.layout.image_fragment, container, false);
        image_detailsi = new ArrayList<>(getListData());
        myadapteri = new ProductAdapter(getActivity(), image_detailsi) ;
        listViewi  = (ListView)viewv.findViewById(R.id.list_view_two);
        listViewi.setAdapter(myadapteri);

        listViewi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listViewi.getItemAtPosition(position);
                Product p = (Product) o;
                ptitlei = p.getProductName();
                pdescriptioni = p.getDescription();
                ppricei = p.getPrice();
                pimagei = p.getProductImage() ;
                plongdescriptioni = p.getLongDescription() ;
                Intent i = new Intent(getActivity(),AddImagesToCart.class);
                startActivityForResult(i,0);
                update();
            }
        });


        FloatingActionButton FAB1 = (FloatingActionButton)viewv.findViewById(R.id.fab_arc_menu_1);
        FAB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),addImages.class);
                startActivityForResult(intent, 0);
                update();
            }
        });

        FloatingActionButton FAB2 = (FloatingActionButton)viewv.findViewById(R.id.fab_arc_menu_2);
        FAB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Are you sure, You wanted to delete everything ");
                    alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            image_detailsi.clear();
                            update();
                            Toast.makeText(getActivity().getApplicationContext(), "the list is set to empty", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
            }
        });

        FloatingActionButton FAB3 = (FloatingActionButton)viewv.findViewById(R.id.fab_arc_menu_3);
        FAB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),ShoppingList.class);
                startActivityForResult(intent, 0);
                update();
            }
        });

        FloatingActionButton FAB4 = (FloatingActionButton)viewv.findViewById(R.id.fab_arc_menu_4);
        FAB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapteri.sortByPrice();
                update();
            }
        });

        FloatingActionButton FAB5 = (FloatingActionButton)viewv.findViewById(R.id.fab_arc_menu_5);
        FAB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapteri.sortByName();
                update();
            }
        });


        arcMenuAndroid = (ArcMenu)viewv.findViewById(R.id.arcmenu_android_example_layout);
        arcMenuAndroid.setStateChangeListener(new StateChangeListener() {
            @Override
            public void onMenuOpened() {
                //something when menu is opened
            }
            @Override
            public void onMenuClosed() {
                // something when menu is closed
            }
        });

        registerForContextMenu(listViewi);

        return viewv;
    }


    private  List<Product> getListData() {

        List<Product> list = new ArrayList();
        byte[] b1  = DrawToByts(R.drawable.new_york);
        byte[] b2  = DrawToByts(R.drawable.pexels_photo);
        byte[] b3  = DrawToByts(R.drawable.bea);
        byte[] b4  = DrawToByts(R.drawable.moun);
        byte[] b5  = DrawToByts(R.drawable.flo);

        Product dvd1 = new Product("Sky Scaper",b1,0.5,"wallpaper for desktop","Great view of the New York City (NYC) skyline with a few high buildings and sun in the background , size : 5183 x 2444 pixels");
        Product dvd2 = new Product("Lights",b2,2.25,"wallpaper for desktop", " Man Stand on a Cliff Holding a Light , size : 5472 x 3648 pixels");
        Product dvd3 = new Product("Beach",b3,0.99,"wallpaper for desktop", " Green Palm Trees on Beach Shore Under Blue and White Sunny Cloudy Sky  , size : 3910 x 2607 pixels");
        Product dvd4 = new Product("Snow  Mountain",b4,1.29,"wallpaper for desktop"," Snow Covered Mountain Under Blue Sky and White Clouds , size :  13548 x 4716 pixels");
        Product dvd5 = new Product("Flower",b5,0.29,"wallpaper for desktop","Cherry Blossoms Photo in Tilt Shift , size : 4896 x 3060 pixels ");
        list.add(dvd1);
        list.add(dvd2);
        list.add(dvd3);
        list.add(dvd4);
        list.add(dvd5);

        return list ;
    }
    private byte[] DrawToByts(int drawable)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata ;
    }
    public void update()
    {
        myadapteri = new ProductAdapter(getActivity(), image_detailsi) ;
        listViewi.setAdapter(myadapteri);
    }

    @Override
    public void onCreateContextMenu(ContextMenu men, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(men, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.action , men);
    }


    /** This will be invoked when a menu item is selected */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (getUserVisibleHint()) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Product t = image_detailsi.get(info.position);
        switch(item.getItemId()){
            case R.id.cnt_mnu_edit:
                    im = info.position;
                    ptitlei = t.getProductName();
                    pdescriptioni = t.getDescription();
                    ppricei = t.getPrice();
                    pimagei = t.getProductImage();
                    plongdescriptioni = t.getLongDescription();
                    Intent intent = new Intent(getActivity(), EditImage.class);
                    startActivityForResult(intent, 0);
                    update();
                break;
            case R.id.cnt_mnu_delete:
                jm = info.position ;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure, You wanted to delete this music ? ");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Product t = image_detailsi.get(jm);
                        image_detailsi.remove(t);
                        myadapteri = new ProductAdapter(getActivity() , image_detailsi) ;
                        listViewi.setAdapter(myadapteri);
                        Toast.makeText(getActivity(), "The image is deleted"  , Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }
            return true;
        } else
            return false;
    }
}