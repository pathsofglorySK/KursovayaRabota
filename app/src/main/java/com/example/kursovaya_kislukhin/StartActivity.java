package com.example.kursovaya_kislukhin;

import static com.example.kursovaya_kislukhin.DBHelper.TABLE_CONTACTS;
import static com.example.kursovaya_kislukhin.DBHelper.DATABASE_NAME;
import static com.example.kursovaya_kislukhin.DBHelper.DATABASE_VERSION;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;
    private EditText enterPassword;
    private EditText enterLogin;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this, TABLE_CONTACTS, DATABASE_VERSION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        enterLogin = (EditText) findViewById(R.id.login);
        enterPassword = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null , null, null);

                String login = enterLogin.getText().toString();
                String password = enterPassword.getText().toString();

                if (cursor.moveToFirst()){

                    int idLogin = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                    int idPassword = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);

                    do {
                        if (login.equals(cursor.getString(idLogin)) && password.equals(cursor.getString(idPassword))){
                            openActivity_main();
                            break;
                        }
                    } while (cursor.moveToNext());
                }

                cursor.close();
                dbHelper.close();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null , null, null);

                String login = enterLogin.getText().toString();
                String password = enterPassword.getText().toString();

                if(!cursor.moveToFirst()){
                    contentValues.put(DBHelper.KEY_LOGIN, "admin");
                    contentValues.put(DBHelper.KEY_PASSWORD, "default");
                    database.insert(TABLE_CONTACTS, null, contentValues);
                }

                if (cursor.moveToFirst()){

                    int idLogin = cursor.getColumnIndex(DBHelper.KEY_LOGIN);

                    do {
                        if (!login.equals(cursor.getString(idLogin))){
                            contentValues.put(DBHelper.KEY_LOGIN, login);
                            contentValues.put(DBHelper.KEY_PASSWORD, password);
                            database.insert(TABLE_CONTACTS, null, contentValues);
                        } else
                            break;
                    } while (cursor.moveToNext());

                }

                cursor.close();
                dbHelper.close();
            }
        });
    }
    public void openActivity_main(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}