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

public class MusicFragment extends Fragment {
    public static ListView listView ;
    public static List<Product> image_details ;
    public static ProductAdapter myadapter ;
    View view ;
    ArcMenu arcMenuAndroid;
    public  static String ptitle ;
    public  static double pprice ;
    public  static String pdescription ;
    public  static byte[] pimage ;
    public  static String plongdescription ;
    public static int i ;
    public static int j ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.music_fragment, container, false);
        image_details = new ArrayList<>(getListData());
        myadapter = new ProductAdapter(getActivity(), image_details) ;
        listView  = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Product p = (Product) o;
                ptitle = p.getProductName();
                pdescription = p.getDescription();
                pprice = p.getPrice();
                pimage = p.getProductImage() ;
                plongdescription = p.getLongDescription() ;
                Intent i = new Intent(getActivity(),AddMusicToCart.class);
                startActivityForResult(i,0);
                update();
            }
        });


        FloatingActionButton FAB1 = (FloatingActionButton)view.findViewById(R.id.fab_arc_menu_1);
        FAB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),addMusic.class);
                startActivityForResult(intent, 0);
                update();
            }
        });

        FloatingActionButton FAB2 = (FloatingActionButton)view.findViewById(R.id.fab_arc_menu_2);
        FAB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Are you sure, You wanted to delete everything ");
                    alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            image_details.clear();
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

        FloatingActionButton FAB3 = (FloatingActionButton)view.findViewById(R.id.fab_arc_menu_3);
        FAB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),ShoppingList.class);
                startActivityForResult(intent, 0);
                update();
            }
        });

        FloatingActionButton FAB4 = (FloatingActionButton)view.findViewById(R.id.fab_arc_menu_4);
        FAB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapter.sortByPrice();
                update();
            }
        });

        FloatingActionButton FAB5 = (FloatingActionButton)view.findViewById(R.id.fab_arc_menu_5);
        FAB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapter.sortByName();
                update();
            }
        });


        arcMenuAndroid = (ArcMenu)view.findViewById(R.id.arcmenu_android_example_layout);
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

        registerForContextMenu(listView);

        return view;
    }


    private  List<Product> getListData() {

        List<Product> list = new ArrayList();
        byte[] c1  = DrawToByts(R.drawable.say_yes);
        byte[] c2  = DrawToByts(R.drawable.red);
        byte[] c3  = DrawToByts(R.drawable.waking_up);
        byte[] c4  = DrawToByts(R.drawable.dreaming_out_loud);
        byte[] c5  = DrawToByts(R.drawable.soundrebel);
        byte[] c6  = DrawToByts(R.drawable.worrior);

        Product music1 = new Product("Speak Now",c1,8.39,"Audio CD Album by Taylor Swift","Speak Now is the third studio album by American country singer-songwriter Taylor Swift  Written entirely by Swift as the follow-up to Fearless, Speak Now expands on the country pop style of her previous work, and features lyrical themes including love, romance and heartbreak");
        Product music2= new Product("Red",c2,9.35,"Audio CD Album by Taylor Swift","Red is the fourth studio album by American singer-songwriter Taylor Swift The album title was inspired by the semi-toxic relationships that Swift experienced during the process of conceiving this album ");
        Product music3= new Product("Waking Up",c3,12.89,"Audio CD Album by OneRepublic","OneRepublic's second studio album, Waking Up ,  The album peaked at number 21 on the Billboard  and has sold over 660,000 copies in the US and over 800,000 total , Lead singer Ryan Tedder has stated in an interview that Jerrod Bettis, who was a former OneRepublic member, had come up with the idea for the album cover while in Seattle");
        Product music4= new Product("Dreaming Out Loud",c4,4.99,"Audio CD Album by OneRepublic","OneRepublic's debut album, Dreaming Out Loud placed the band in its Artists to Watch list, which featured ten artists that, according to the magazine : are bringing the future of music, today ");
        Product music5= new Product("Sound Of A Rebel",c5,11.89,"Audio CD Album by Outlandish","Sound of a Rebel is the fourth studio album by the Danish hip hop group Outlandish. It was released in Denmark on May 11, 2009. It is Outlandish's comeback album after four years of the band members working on solo projects.");
        Product music6= new Product("Worrior",c6,0.99," Single by Outlandish","  the fifth studio album by Denmark-based band Outlandish. It was released on 28 May 2012 by Sony Music. The album received DEN Gold certification ");
        list.add(music1);
        list.add(music2);
        list.add(music3);
        list.add(music4);
        list.add(music5);
        list.add(music6);
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
        myadapter = new ProductAdapter(getActivity(), image_details) ;
        listView.setAdapter(myadapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.action , menu);

    }


    /** This will be invoked when a menu item is selected */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (getUserVisibleHint()) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Product t = image_details.get(info.position);
        switch(item.getItemId()){
            case R.id.cnt_mnu_edit:
                i = info.position;
                ptitle = t.getProductName();
                pdescription = t.getDescription();
                pprice = t.getPrice();
                pimage = t.getProductImage() ;
                plongdescription = t.getLongDescription() ;
                Intent intent = new Intent(getActivity(),EditMusic.class);
                startActivityForResult(intent, 0);
                update();
                break;
            case R.id.cnt_mnu_delete:
                j = info.position ;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure, You wanted to delete this music ? ");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Product t = image_details.get(j);
                        image_details.remove(t);
                        myadapter = new ProductAdapter(getActivity() , image_details) ;
                        listView.setAdapter(myadapter);
                        Toast.makeText(getActivity(), "The music is deleted"  , Toast.LENGTH_SHORT).show();
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