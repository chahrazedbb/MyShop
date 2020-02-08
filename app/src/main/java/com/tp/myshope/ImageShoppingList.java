package com.tp.myshope;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sa90.materialarcmenu.ArcMenu;
import com.sa90.materialarcmenu.StateChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME on 22/04/2017.
 */

public class ImageShoppingList extends Fragment {
    View view ;
    public static ListView listViewImage;
    public static List<Product> image_pro;
    public static ProductAdapter imageApdater;
    ProductBase data;
    ArcMenu arcMenuAndroid;
    public static int k ;
    public static int vlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.image_shopping_fragment, container, false);

        image_pro = new ArrayList<>(getImageList());
        imageApdater = new ProductAdapter(getActivity(), image_pro);
        listViewImage = (ListView)view.findViewById(R.id.list_view);
        listViewImage.setAdapter(imageApdater);

        registerForContextMenu(listViewImage);

        getCount();
        getTotal();

        FloatingActionButton FAB2 = (FloatingActionButton)view.findViewById(R.id.fab_arc2);
        FAB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure, You want to remove all your products ?");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        image_pro.clear();
                        setImageEmpty();
                        update();
                        getTotal();
                        getCount();
                    }
                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        FloatingActionButton FAB3 = (FloatingActionButton)view.findViewById(R.id.fab_arc3);
        FAB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageApdater.sortByPrice();
                update();
            }
        });

        FloatingActionButton FAB4 = (FloatingActionButton)view.findViewById(R.id.fab_arc4);
        FAB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageApdater.sortByName();
                update();
            }
        });

        FloatingActionButton FAB5 = (FloatingActionButton)view.findViewById(R.id.fab_arc5);
        FAB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivityForResult(i,0);
            }
        });

        arcMenuAndroid = (ArcMenu)view.findViewById(R.id.arcmenu1);
        arcMenuAndroid.setStateChangeListener(new StateChangeListener() {
            @Override
            public void onMenuOpened() {

            }
            @Override
            public void onMenuClosed() {

            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.action2 , menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (getUserVisibleHint()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        k = info.position;
        switch(item.getItemId()){
            case R.id.str_remove:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure, You want to remove this product ?");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                            Product t = image_pro.get(k);
                            image_pro.remove(t);
                            imageApdater = new ProductAdapter(getActivity(), image_pro) ;
                            listViewImage.setAdapter(imageApdater);
                            takeProductOffimage(t);
                            getTotal();
                            getCount();
                            Toast.makeText(getActivity(), "The image is removed from your shopping list"  , Toast.LENGTH_SHORT).show();
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

    private List<Product> getImageList()
    {
        List<Product> list = new ArrayList();
        data = new ProductBase(getActivity());
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }
        list = data.getImage() ;
        return list ;
    }
    private void takeProductOffimage(Product product)
    {
        data = new ProductBase(getActivity());

        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }

        data.deleteImage(product);
    }

    private void setImageEmpty()
    {
        List<Product> list1 = new ArrayList();
        data = new ProductBase(getActivity());
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }
        int j = CountImages() ;
        for(int i = 0 ; i < j ; i++)
        {
            list1 = data.getImage() ;
        }
        for(int i = 0 ; i < j ; i++)
        {
            data.deleteImage(list1.get(i));
        }
    }
    private int CountImages()
    {
        update();
        int i = 0 ;
        List<Product> list = new ArrayList();
        data = new ProductBase(getActivity());
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }
        list = data.getImage() ;

        for(int j = 0 ; j < list.size() ; j++)
        {
            i = i + 1 ;
        }
        return i ;
    }

    private void getTotal()
    {
        double total = 0 ;

      for (int i = 0 ; i < getImageList().size() ; i++)
        {
            total = total + getImageList().get(i).getPrice();
        }
        update();
        TextView tot = (TextView)view.findViewById(R.id.tot);
        tot.setText("Total : " + String.valueOf(total) + " $");
    }
    public void getCount()
    {
        TextView i = (TextView)view.findViewById(R.id.cimage);
        i.setText("you have  " + String.valueOf(CountImages()) + " image");
    }

    public void update()
    {
        imageApdater = new ProductAdapter(getActivity(), image_pro) ;
        listViewImage.setAdapter(imageApdater);
    }
}