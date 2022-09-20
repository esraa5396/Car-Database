package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "cars_db";
    public static final int DB_VERSION = 1;
    public static final String CAR_TB_NAME = "car";
    public static final String CAR_CL_ID = "id";
    public static final String CAR_CL_MODEL = "model";
    public static final String CAR_CL_COLOR = "color";
    public static final String CAR_CL_DPL = "distancePerLetter";

    public MyDatabase(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //يتم اسندعائها عند انشاء الداتابيز
        sqLiteDatabase.execSQL("CREATE TABLE "+CAR_TB_NAME+" ("+CAR_CL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+ CAR_CL_MODEL+"  TEXT ,"+CAR_CL_COLOR+" TEXT ,"+CAR_CL_DPL+" REAL)");

    }//end onCreate

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + CAR_TB_NAME);
        onCreate(sqLiteDatabase);
    }//end onUpgrade

    //داله الاضافه
    public boolean insertCar(Car car)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CAR_CL_MODEL,car.getModel());
        values.put(CAR_CL_COLOR,car.getColor());
        values.put(CAR_CL_DPL,car.getDpl());
        long result= db.insert(CAR_TB_NAME,null,values);
        return result!=-1;

    }//end insertCar

    //داله التعديل
    public boolean updateCar(Car car)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CAR_CL_MODEL,car.getModel());
        values.put(CAR_CL_COLOR,car.getColor());
        values.put(CAR_CL_DPL,car.getDpl());
        String [] args={car.getId()+""};
        int result= db.update(CAR_TB_NAME,values,"id=?",args);
        return result > 0;

    }//end updateCar

    //ارجاع عدد الصفوف فى جدول معين
    public long getCarsCount(){
        SQLiteDatabase dp=getReadableDatabase();
        return DatabaseUtils.queryNumEntries(dp,CAR_TB_NAME);
    }//end getCarsCount

    //داله الحذف
    public boolean deleteCar(Car car)
    {
        SQLiteDatabase db= getWritableDatabase();
        String [] args={car.getId()+""};
        int result= db.delete(CAR_TB_NAME,"id=?",args);
        return result > 0;

    }//end updateCar

    //داله الاسترجاع
    public ArrayList<Car> getAllCars(){
        ArrayList<Car> cars = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +CAR_TB_NAME,null);
        //كود التعامل مع الكيرسر وتحويله الى مصفوفه من النوع كار
        // فحص هل الكيرسر يحتوى على بيانات ام لا
        if(cursor!= null && cursor.moveToFirst())
        {
            do
            {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(CAR_CL_ID));
                @SuppressLint("Range") String model = cursor.getString(cursor.getColumnIndex(CAR_CL_MODEL));
                @SuppressLint("Range") String color = cursor.getString(cursor.getColumnIndex(CAR_CL_COLOR));
                @SuppressLint("Range") double dpl = cursor.getDouble(cursor.getColumnIndex(CAR_CL_DPL));

                Car c = new Car(id,model,color,dpl);
                cars.add(c);

            }while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
    }

    //داله البحث
    public ArrayList<Car> getCars(String modelSearch){
        ArrayList<Car> cars = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +CAR_TB_NAME+" WHERE " +CAR_CL_MODEL+"=?",new String[]{modelSearch});
        //كود التعامل مع الكيرسر وتحويله الى مصفوفه من النوع كار
        // فحص هل الكيرسر يحتوى على بيانات ام لا
        if(cursor!= null && cursor.moveToFirst())
        {
            do
            {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(CAR_CL_ID));
                @SuppressLint("Range") String model = cursor.getString(cursor.getColumnIndex(CAR_CL_MODEL));
                @SuppressLint("Range") String color = cursor.getString(cursor.getColumnIndex(CAR_CL_COLOR));
                @SuppressLint("Range") double dpl = cursor.getDouble(cursor.getColumnIndex(CAR_CL_DPL));

                Car c = new Car(id,model,color,dpl);
                cars.add(c);

            }while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
    }
}
