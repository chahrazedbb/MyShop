package com.tp.myshope;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HOME on 22/04/2017.
 */

public class PagerAdapterTwo extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterTwo(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MusicShoppingList tab1 = new MusicShoppingList();
                return tab1;
            case 1:
                BookShoppingList tab2 = new BookShoppingList();
                return tab2;
            case 2:
                ImageShoppingList tab3 = new ImageShoppingList();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}