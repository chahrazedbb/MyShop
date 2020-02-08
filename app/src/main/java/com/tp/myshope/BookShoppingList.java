package com.tp.myshope;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
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

public class BookShoppingList extends Fragment {

    View view ;
    public static ListView listViewBook;
    public static List<Product> bookc_pro;
    public static ProductAdapter bookApdater;
    ProductBase data;
    ArcMenu arcMenuAndroid;
    public static int k ;
    public static int vlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.book_shopping_fragment, container, false);

        bookc_pro = new ArrayList<>(getBookList());
        bookApdater = new ProductAdapter(getActivity(), bookc_pro);
        listViewBook = (ListView) view.findViewById(R.id.list_view);
        listViewBook.setAdapter(bookApdater);
        registerForContextMenu(listViewBook);

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
                        bookc_pro.clear();
                        setBookEmpty();
                        update();
                        getTotal();
                        getCount();
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

        FloatingActionButton FAB3 = (FloatingActionButton) view.findViewById(R.id.fab_arc3);
        FAB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookApdater.sortByPrice();
                update();
            }
        });

        FloatingActionButton FAB4 = (FloatingActionButton) view.findViewById(R.id.fab_arc4);
        FAB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookApdater.sortByName();
                update();
            }
        });

        FloatingActionButton FAB5 = (FloatingActionButton) view.findViewById(R.id.fab_arc5);
        FAB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivityForResult(i, 0);
            }
        });

        arcMenuAndroid = (ArcMenu) view.findViewById(R.id.arcmenu1);
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
        vlist = v.getId() ;
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
                            Product t = bookc_pro.get(k);
                            bookc_pro.remove(t);
                            bookApdater = new ProductAdapter(getActivity(), bookc_pro) ;
                            listViewBook.setAdapter(bookApdater);
                            takeProductOffbook(t);
                            getTotal();
                            getCount();
                            Toast.makeText(getActivity(), "The book is removed from your shopping list"  , Toast.LENGTH_SHORT).show();
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

    private List<Product> getBookList()
    {
        List<Product> list = new ArrayList();
        data = new ProductBase(getActivity());
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }
        list = data.getBook() ;
        return list ;
    }

    private void takeProductOffbook(Product product)
    {
        data = new ProductBase(getActivity());

        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }

        data.deleteBook(product);
    }
    private void setBookEmpty()
    {
        List<Product> list1 = new ArrayList();
        data = new ProductBase(getActivity());
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hi there !", "hi there !");
        }
        int j = CountBooks() ;
        for(int i = 0 ; i < j ; i++)
        {
            list1 = data.getBook() ;
        }
        for(int i = 0 ; i < j ; i++)
        {
            data.deleteBook(list1.get(i));
        }
    }

    private int CountBooks()
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
        list = data.getBook() ;

        for(int j = 0 ; j < list.size() ; j++)
        {
            i = i + 1 ;
        }
        return i ;
    }

    private void getTotal()
    {
        double total = 0 ;

        for (int i = 0 ; i < getBookList().size() ; i++)
        {
            total = total + getBookList().get(i).getPrice();
        }
        update();
        TextView tot = (TextView)view.findViewById(R.id.tot);
        tot.setText("Total : " + String.valueOf(total) + " $");
    }
    public void getCount()
    {
        TextView b = (TextView)view.findViewById(R.id.cbook);
        b.setText("you have  " + String.valueOf(CountBooks()) + " book");
    }

    public void update()
    {
        bookApdater = new ProductAdapter(getActivity(), bookc_pro) ;
        listViewBook.setAdapter(bookApdater);
    }
}