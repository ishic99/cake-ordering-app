package com.mad.toffels.ShoppingCart.DataBASE;

import android.provider.BaseColumns;

public final class ShopingProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ShopingProfile() {}

    /* Inner class that defines the table contents */
    public static class Shop implements BaseColumns {
        public static final String TABLE_NAME = "ShopInfo";
        public static final String COLUMN_1= "qty";
        public static final String COLUMN_2= "address";
        public static final String COLUMN_3= "total";



    }
}