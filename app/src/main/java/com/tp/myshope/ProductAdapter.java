package com.tp.myshope;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by HOME on 31/03/2017.
 */

public class ProductAdapter extends BaseAdapter {

    private List<Product> ProductList ;
    private LayoutInflater layout ;
    private Context context ;


    public ProductAdapter(Context c,List<Product> ProductList)
    {
        this.context = c ;
        this.ProductList = ProductList ;
        layout = LayoutInflater.from(c);
    }

    public void sortByName() {
        Comparator<Product> comparator = new Comparator<Product>() {

            @Override
            public int compare(Product object1, Product object2) {
                return object1.getProductName().compareToIgnoreCase(object2.getProductName());
            }
        };
        Collections.sort(this.ProductList, comparator);
    }

    public void sortByPrice() {
        Comparator<Product> comparator = new Comparator<Product>() {

            @Override
            public int compare(Product object1, Product object2) {
                return Double.compare(object1.getPrice(),object2.getPrice());
            }
        };
        Collections.sort(this.ProductList, comparator);
    }

    @Override
    public int getCount() {
        return ProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return ProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            convertView = layout.inflate(R.layout.list_product_layout, null);

            holder = new ViewHolder();
            holder.ProductImageView = (ImageView)convertView.findViewById(R.id.product_image);
            holder.ProductNameView = (TextView)convertView.findViewById(R.id.name);
            holder.DescriptionView = (TextView)convertView.findViewById(R.id.description);
            holder.PriceView = (TextView)convertView.findViewById(R.id.price);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Product product = this.ProductList.get(position);
        holder.ProductNameView.setText(product.getProductName());
        holder.DescriptionView.setText( product.getDescription());
        holder.PriceView.setText("Price: " + product.getPrice() + " $");

        //int imageId = this.getImage(product.getProductImage());

        byte[] imageProduct = product.getProductImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageProduct, 0, imageProduct.length);
        holder.ProductImageView.setImageBitmap(bitmap);

        return convertView;
    }



    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
    }

    static class ViewHolder {
        ImageView ProductImageView;
        TextView ProductNameView ;
        TextView DescriptionView ;
        TextView PriceView ;
    }


}
