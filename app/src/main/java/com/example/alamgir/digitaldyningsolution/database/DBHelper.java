package com.example.alamgir.digitaldyningsolution.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alamgir.digitaldyningsolution.model.Cost;
import com.example.alamgir.digitaldyningsolution.model.Person;

import java.util.ArrayList;

/**
 * Created by Alamgir on 3/22/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public  static  final String DB_NAME="dainig.db";
    public static  final  int DB_VERSION=3;

// person table
    public static final String PERSON_TABLE="person";
    public static final String COLUNM_ID="id";
    public static final String COLUNM_PERSON_NAME="personName";
    public static final String COLUNM_PERSON_MEAL="personMeal";
    public static final String COLUNM_PERSON_MONEY="personMoney";
    public static final String PERSON_TABLE_SQL="CREATE TABLE " +PERSON_TABLE+"("+COLUNM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUNM_PERSON_NAME+" TEXT, "+COLUNM_PERSON_MEAL+" REAL, "+COLUNM_PERSON_MONEY+" REAL)";

    // cost table

    public static final String COST_TABLE="cost";
    public static final String COST_COLUNM_ID="costId";
    public static final String COLUNM_TOTAL_COST="totalCost";
    public static final String COLUNM_MEAL_RATE="mealRate";

    public static final String COST_TABLE_SQL="CREATE TABLE " +COST_TABLE+"("+COST_COLUNM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUNM_TOTAL_COST+" REAL, "+COLUNM_MEAL_RATE+" REAL)";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PERSON_TABLE_SQL);
        db.execSQL(COST_TABLE_SQL);
        Log.e("CREATE TABLE",PERSON_TABLE_SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //insert person data
    public  long insertPerson(Person person)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues  values=new ContentValues();
        values.put(COLUNM_PERSON_NAME,person.getPersonName());
        values.put(COLUNM_PERSON_MEAL,person.getPersionMeal());
        values.put(COLUNM_PERSON_MONEY,person.getPersonMoney());
        long insert=db.insert(PERSON_TABLE,null,values);
        return insert;
    }
    // update person data
    public long updatePersonData(Person person)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUNM_PERSON_NAME,person.getPersonName());
        values.put(COLUNM_PERSON_MEAL,person.getPersionMeal());
        values.put(COLUNM_PERSON_MONEY,person.getPersonMoney());
        long update=db.update(PERSON_TABLE,values,"id=?",new String[]{String.valueOf(person.getId())});
        return update;
    }
    // get person data
    public ArrayList<Person> personInfo()
    {
        Person person=null;
        ArrayList<Person>personlist=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(PERSON_TABLE,null,null,null,null,null,null);
        if (cursor!=null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            for (int i=0;i<cursor.getCount();i++)
            {
                int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUNM_ID)));
                String name=cursor.getString(cursor.getColumnIndex(COLUNM_PERSON_NAME));
                double meal=Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUNM_PERSON_MEAL)));
                double money=Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUNM_PERSON_MONEY)));
                person=new Person(id,name,meal,money);
                personlist.add(person);
                cursor.moveToNext();

            }
            cursor.close();
        }

        return personlist;
    }
    // insert cost data
    public  long insertCost(Cost cost)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues  values=new ContentValues();
        values.put(COLUNM_TOTAL_COST,cost.getTotalCost());

        long insert=db.insert(COST_TABLE,null,values);
        return insert;
    }
    // update cost data
    public long updateCostData(Cost cost)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUNM_TOTAL_COST,cost.getTotalCost());
        long update=db.update(COST_TABLE,values,"id=?",new String[]{String.valueOf(cost.getId())});
        return update;
    }
   // retrive cost data
    public Cost getTotalcost()
    {
        Cost cost=null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(COST_TABLE,null,null,null,null,null,null);
        if (cursor!=null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            for (int i=0;i<cursor.getCount();i++)
            {
                int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(COST_COLUNM_ID)));
                double totalCost=Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUNM_TOTAL_COST)));

                cost=new Cost(id,totalCost);
                cursor.moveToNext();

            }
            cursor.close();
        }

        return cost ;
    }


}
