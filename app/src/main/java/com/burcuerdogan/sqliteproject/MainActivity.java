package com.burcuerdogan.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);

            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");

            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('James', 50)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars', 60)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk', 55)");

            //Güncelleme / UPDATE
            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

            //Silme / DELETE FROM
            //database.execSQL("DELETE FROM musicians WHERE id = 2");

            Cursor cursor = database.rawQuery("SELECT * FROM musicians", null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE id=2", null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name='James'",null);

            //Filtreleme
            //s ile bitenleri getir -> %s
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'", null);
            //K ile başlayanları getir -> K%
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null);

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                System.out.println("ID: " + cursor.getInt(idIx));
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
            }

            cursor.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}