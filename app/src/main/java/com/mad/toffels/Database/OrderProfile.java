package com.mad.toffels.Database;

import android.provider.BaseColumns;

public final class OrderProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private OrderProfile() {}

    /* Inner class that defines the table contents */
    public static class Profile implements BaseColumns {
        public static final String TABLE_NAME = "OrderInfo";
        public static final String COLUMN_1= "username";
        public static final String COLUMN_2= "code";
        public static final String COLUMN_3= "phoneNo";
        public static final String COLUMN_4= "email";
        public static final String COLUMN_5= "DateOrder";
        public static final String COLUMN_6= "TimeOrder";
    }
}
