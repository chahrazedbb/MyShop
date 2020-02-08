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

public class MusicShoppingList extends Fragment {

    View view ;
    public static ListView listViewMusic;
    public static List<Product> music_pro;
    public static ProductAdapter musicApdater;
    ProductBase data;
    ArcMenu arcMenuAndroid;
    public static int k ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.music_shopping_fragment, container, false);


        music_pro = new ArrayList<>(getMusicList());
        musicApdater = new ProductAdapter(getActivity(), music_pro);
        listViewMusic = (ListView)view.findViewById(R.id.list_view);
        listViewMusic.setAdapter(musicApdater);
        registerForContextMenu(listViewMusic);

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
                        music_pro.clear();
                        setMusicEmpty();
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
                musicApdater.sortByPrice();
                update();
            }
        });

        FloatingActionButton FAB4 = (FloatingActionButton)view.findViewById(R.id.fab_arc4);
        FAB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicApdater.sortByName();
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
                            Product t = music_pro.get(k);
                            music_pro.remove(t);
                            musicApdater = new ProductAdapter(getActivity(), music_pro) ;
                            listViewMusic.setAdapter(musicApdater);
                            takeProductOffmusic(t);
                            getTotal();
                            getCount();
                            Toast.makeText(getActivity(), "The music is removed from your shopping list"  , Toast.LENGTH_SHORT).show();
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

    private List<Product> getMusicList()
    {
        List<Product> list = new ArrayList();
        data = new ProductBase(getActivity());
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }
        list = data.getMusic() ;
        return list ;
    }
    private void takeProductOffmusic(Product product)
    {
        data = new ProductBase(getActivity());

        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }

        data.deleteMusic(product);
    }

    private void setMusicEmpty()
    {
        List<Product> list1 = new ArrayList();
        data = new ProductBase(getActivity());
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }
        int j = CountMusic() ;
        for(int i = 0 ; i < j ; i++)
        {
            list1 = data.getMusic() ;
        }
        for(int i = 0 ; i < j ; i++)
        {
            data.deleteMusic(list1.get(i));
        }
    }

    private int CountMusic()
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
        list = data.getMusic() ;

        for(int j = 0 ; j < list.size() ; j++)
        {
            i = i + 1 ;
        }
        return i ;
    }

    private void getTotal()
    {
        double total = 0 ;
        for (int i = 0 ; i < getMusicList().size() ; i++)
        {
            total = total + getMusicList().get(i).getPrice();
        }
        update();
        TextView tot = (TextView)view.findViewById(R.id.tot);
        tot.setText("Total : " + String.valueOf(total) + " $");
    }
    public void getCount()
    {
        TextView m = (TextView)view.findViewById(R.id.cmusic);
        m.setText("you have " + String.valueOf(CountMusic()) + " music product");
    }

    public void update()
    {
        musicApdater = new ProductAdapter(getActivity(), music_pro) ;
        listViewMusic.setAdapter(musicApdater);
    }


}
