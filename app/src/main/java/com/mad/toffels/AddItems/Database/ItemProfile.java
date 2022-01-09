package com.mad.toffels.AddItems.Database;

import android.provider.BaseColumns;

public final class ItemProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ItemProfile() {}

    /* Inner class that defines the table contents */
    public static class Item implements BaseColumns {
        public static final String TABLE_NAME = "ItemInfo";
        public static final String COLUMN_1= "itemCode";
        public static final String COLUMN_2= "cakename";
        public static final String COLUMN_3= "weight";
        public static final String COLUMN_4= "sheap";
        public static final String COLUMN_5= "flavour";
        public static final String COLUMN_6= "price";

    }
}