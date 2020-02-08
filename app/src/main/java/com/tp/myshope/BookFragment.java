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


public class BookFragment extends Fragment {

    public static ListView listViewb ;
    public static List<Product> image_details ;
    public static ProductAdapter myadapter ;
    View view ;
    ArcMenu arcMenuAndroid;
    public  static String ptitle ;
    public  static double pprice ;
    public  static String pdescription ;
    public  static byte[] pimage ;
    public static int i ;
    public static int j ;
    public  static String plongdescription ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.book_fragment, container, false);
        image_details = new ArrayList<>(getListData());
        myadapter = new ProductAdapter(getActivity(), image_details) ;
        listViewb  = (ListView)view.findViewById(R.id.list_view);
        listViewb.setAdapter(myadapter);

        listViewb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listViewb.getItemAtPosition(position);
                Product p = (Product) o;
                ptitle = p.getProductName();
                pdescription = p.getDescription();
                pprice = p.getPrice();
                pimage = p.getProductImage() ;
                plongdescription = p.getLongDescription() ;
                Intent i = new Intent(getActivity(),AddBooksToCart.class);
                startActivityForResult(i,0);
                update();
            }
        });

        registerForContextMenu(listViewb);

        FloatingActionButton FAB1 = (FloatingActionButton)view.findViewById(R.id.fab_arc_menu_1);
        FAB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),addBooks.class);
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

        return view;
    }

    private  List<Product> getListData() {

        List<Product> list = new ArrayList();
        byte[] a1  = DrawToByts(R.drawable.s);
        byte[] a2  = DrawToByts(R.drawable.theydoitwithmirrors);
        byte[] a3  = DrawToByts(R.drawable.pride_and_prejudice);
        byte[] a4  = DrawToByts(R.drawable.m);
        byte[] a5  = DrawToByts(R.drawable.p);

        Product book1 = new Product("moving finger",a1,0.99," detective fiction novel by Agatha Christie "," The Burtons, brother and sister, arrive in a small village, soon receiving an anonymous letter accusing them of being lovers, not siblings. They are not the only ones in the village to receive such vile letters. A prominent resident is found dead with one such letter found next to her. This novel features the elderly detective Miss Marple in a relatively minor role, a little old lady sleuth who doesn't seem to do much ; She enters the story after the police have failed to solve the crime in the final quarter of the book, and in a handful of scenes");
        Product book2= new Product("They Do It with Mirrors",a2,6.58,"detective fiction novel by Agatha Christie ","Miss Marple senses danger when she visits a friend living in a Victorian mansion which doubles as a rehabilitation centre for delinquents. Her fears are confirmed when a youth fires a revolver at the administrator, Lewis Serrocold. Neither is injured. But a mysterious visitor, Mr Gilbrandsen, is less fortunate – shot dead simultaneously in another part of the building.\n Pure coincidence? Miss Marple thinks not, and vows to discover the real reason for Mr Gilbrandsen’s visit.");
        Product book3= new Product("pride and prejedus",a3,8.46,"Romance noval by Jane Auston"," It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.” So begins Pride and Prejudice, Jane Austen’s witty comedy of manners—one of the most popular novels of all time—that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues. Renowned literary critic and historian George Saintsbury in 1894 declared it the “most perfect, the most characteristic, the most eminently quintessential of its author’s works,” and Eudora Welty in the twentieth century described it as “irresistible and as nearly flawless as any fiction could be . penguinrandomhouse.com");
        Product book4= new Product("sense and sensibility",a4,8.46,"Romance noval by Jane Auston","The more I know of the world, the more am I convinced that I shall never see a man whom I can really love. I require so much!  . Marianne Dashwood wears her heart on her sleeve, and when she falls in love with the dashing but unsuitable John Willoughby she ignores her sister Elinor's warning that her impulsive behaviour leaves her open to gossip and innuendo. Meanwhile Elinor, always sensitive to social convention, is struggling to conceal her own romantic disappointment, even from those closest to her. Through their parallel experience of love—and its threatened loss—the sisters learn that sense must mix with sensibility if they are to find personal happiness in a society where status and money govern the rules of love");
        Product book5= new Product("Mansfield Park",a5,6.61,"Romance novel by Jane Auston","'We have all been more or less to blame ...every one of us, excepting Fannyn  ... Taken from the poverty of her parents' home, Fanny Price is brought up with her rich cousins at Mansfield Park, acutely aware of her humble rank and with only her cousin Edmund as an ally. When Fanny's uncle is absent in Antigua, Mary Crawford and her brother Henry arrive in the neighbourhood, bringing with them London glamour and a reckless taste for flirtation. As her female cousins vie for Henry's attention, and even Edmund falls for Mary's dazzling charms, only Fanny remains doubtful about the Crawfords' influence and finds herself more isolated than ever. A subtle examination of social position and moral integrity, Mansfield Park is one of Jane Austen's most profound works. ");
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);

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
        listViewb.setAdapter(myadapter);
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
                Intent intent = new Intent(getActivity(),EditBook.class);
                startActivityForResult(intent, 0);
                update();
                break;
            case R.id.cnt_mnu_delete:
                j = info.position ;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure, You wanted to delete this book ? ");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Product t = image_details.get(j);
                        image_details.remove(t);
                        myadapter = new ProductAdapter(getActivity() , image_details) ;
                        listViewb.setAdapter(myadapter);
                        Toast.makeText(getActivity(), "The book is deleted"  , Toast.LENGTH_SHORT).show();
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
