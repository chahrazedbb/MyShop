package com.tp.myshope;

/**
 * Created by HOME on 31/03/2017.
 */

public class Product {
    private String ProductName ;
    private byte[] ProductImage;
    private String Description;
    private double Price;
    private String LongDescription ;

    public Product() {}

    public Product(String ProductName,byte[] ProductImage,double Price,String Description,String LongDescription)
    {
        this.ProductName = ProductName ;
        this.Description = Description ;
        this.ProductImage = ProductImage ;
        this.Price = Price ;
        this.LongDescription = LongDescription ;
    }


    // product name
    public String getProductName()
    {
        return  ProductName ;
    }
    public void setProductName(String ProductName)
    {
        this.ProductName = ProductName ;
    }

    // product description
    public String getDescription()
    {
        return  Description ;
    }
    public void setDescription(String Description)
    {
        this.Description = Description ;
    }

    // product long description
    public String getLongDescription()
    {
        return  LongDescription ;
    }
    public void setLongDescription(String LongDescription)
    {
        this.LongDescription = LongDescription ;
    }

    // product image
    public byte[] getProductImage()
    {
        return  ProductImage ;
    }
    public void setProductImage(byte[] ProductImage)
    {
        this.ProductImage = ProductImage ;
    }

    // product price
    public double getPrice()
    {
        return  Price ;
    }
    public void setPrice(double Price)
    {
        this.Price = Price ;
    }
}
