package com.manoj.understanddb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 2);
    }

    // this is called the first time a database is accesed. There should be code to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement="CREATE TABLE "+ CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    // this is called if the database version number changes.It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(CustomerModel customerModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());

        long insert=db.insert(CUSTOMER_TABLE,null,cv);
        if(insert== -1){
            return  false;
        }else {
            return true;
        }

    }
    // for select data
    public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnList = new ArrayList<>();

        //get data from database;
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            // loop through cursor (result set) and create new customer objects. put them into the return list.
            do{
                int customerID = cursor.getInt(0);// coloumn index 0
                String customerName=cursor.getString(1);// coloumn index 1
                int customerAge=cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true: false;//search google Ternary Operator in java

                CustomerModel newCustomer = new CustomerModel(customerID,customerName,customerAge,customerActive);
                returnList.add(newCustomer);


            }while (cursor.moveToNext());

        }else{
            // failure. do not add anything to the list.
        }
        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return  returnList;




    }
}
